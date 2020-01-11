package com.stardusted1.Sendicate.app.rest.cargo;

import com.stardusted1.Sendicate.app.core.cargo.Package;
import com.stardusted1.Sendicate.app.core.cargo.*;
import com.stardusted1.Sendicate.app.core.cargo.condition.*;
import com.stardusted1.Sendicate.app.core.repositories.*;
import com.stardusted1.Sendicate.app.core.users.Customer;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "api/supply")
public class SupplyController {
	@Autowired
	System system;

	@Autowired
	SupplyRepository supplyRepository;

	@Autowired
	ReceiverRepository receiverRepository;

	@Autowired
	DeliverymanRepository deliverymanRepository;

	@Autowired
	ProviderRepository providerRepository;

	@Autowired
	TransmitterRepository transmitterRepository;

	@Autowired
	FramesRepository framesRepository;

	@Autowired
	PackageRepository packageRepository;


	@PostMapping("/new_supply/{provider}/{token}")
	public boolean newSupply(@RequestBody String request,
							 @PathVariable Provider provider,
							 @PathVariable String token) throws Exception {
		if (!provider.getToken().equals(token)) {
			return false;
        }
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(request);
        Supply supply = new Supply();
        try {
            supply.setName((String) map.get("name"));
            supply.setDateBegins(new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("dateFrom")));
            supply.setDateEnds(new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("dateTo")));
            supply.setDeliverymanId((String) map.get("deliveryman"));
            supply.setReceiverId((String) map.get("receiver"));
            supply.setProviderId(provider.getId());
            ArrayList<LinkedHashMap> packages = (ArrayList) map.get("packages");
            int numberOfPackages = packages.size();
            for (int i = 0; i < numberOfPackages; i++) {
                LinkedHashMap hashMap = packages.get(i);
                Package pack = new Package();
                pack.setSupplyId(supply.getId());
                pack.setPayload((String) hashMap.get("payload"));
                ArrayList transmitters = (ArrayList) hashMap.get("transmitters");
                for (int j = 0; j < transmitters.size(); j++) {
                    Transmitter transmitter = transmitterRepository.findById(Long.parseLong((String) (transmitters.get(j)))).get();
                    transmitter.setCurrentPackageId(pack.getId());
                    transmitter.setCurrentSupplyId(supply.getId());
                    transmitterRepository.save(transmitter);
                    pack.addTransmitter(transmitter);
                    transmitterRepository.save(transmitter);
                }
                ArrayList conditions = (ArrayList) hashMap.get("conditions");
                for (int j = 0; j < conditions.size(); j++) {
                    LinkedHashMap linkedHash = (LinkedHashMap) conditions.get(j);
                    String type = (String) linkedHash.get("type");
                    Condition condition = null;
                    if (type.equals("t")) {
                        condition = new TemperatureCondition();
                    }
                    else if (type.equals("h")) {
                        condition = new HumidityCondition();
                    }
                    else if (type.equals("l")) {
                        condition = new IlluminationCondition();
                    }
                    else if (type.equals("a")) {
                        condition = new AccelerationCondition();
                    }
                    float maxval = 0;
                    float minval = 0;
                    float prefered = 0;

                    try {
                        maxval = Float.parseFloat((String) linkedHash.get("maxval"));
                    }catch (NumberFormatException e){
                        maxval = 0;
                    }
                    try {
                        minval = Float.parseFloat((String) linkedHash.get("minval"));
                    }catch (NumberFormatException e){
                        minval = 0;
                    }
                    try {
                        prefered = Float.parseFloat((String) linkedHash.get("pref"));
                    }catch (NumberFormatException e){
                        prefered = 0;
                    }
                    condition.setMax(maxval);
                    condition.setMin(minval);
                    condition.setPreferred(prefered);
                    pack.addCondition(condition);
                }
                supply.addPackage(pack);
                packageRepository.save(pack);
            }
            supply.setConditionNormal();
            supply.setStatus(SupplyStatus.PENDING,system);
            supplyRepository.save(supply);
        }
        catch (Exception e){
            return false;
        }

		return true;
	}

	@GetMapping("{id}/get")
	public Iterable<Supply> getSupplies(@PathVariable("id") String id) {
		String userclass = SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName();
		LinkedList<SupplyStatus> statuses = new LinkedList<>();
		statuses.add(SupplyStatus.DELIVERING);
		statuses.add(SupplyStatus.PENDING);
		statuses.add(SupplyStatus.DELIVERED);
		return getSupplies(id, userclass, statuses);
	}

	@GetMapping("{user}/get_old/{token}")
	public Iterable<Supply> getOldSupplies(
			@PathVariable(name = "user") String userId,
			@PathVariable String token
	){
		String userclass = SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName();
		LinkedList<SupplyStatus> statuses = new LinkedList<>();
		statuses.add(SupplyStatus.UNDELIVERED);
		statuses.add(SupplyStatus.TAKEN);
		return getSupplies(userId, userclass, statuses);
	}

	@PostMapping("{supplyId}/{id}/{token}")
	public Supply takeSupply(@PathVariable(name = "supplyId") Supply supply,
							 @PathVariable(name = "id")Receiver receiver,
							 @PathVariable(name = "token") String token){
		if(receiver.getToken().equals(token)){
			supply.setStatus(SupplyStatus.TAKEN,system);
			supplyRepository.save(supply);
			return supply;
		}
		return null;
	}

	private Iterable<Supply> getSupplies(String id, String userclass, LinkedList<SupplyStatus> statuses) {
		statuses.add(SupplyStatus.TAKEN);

		LinkedList result = new LinkedList();
		if (userclass.equals("Provider")) {
			for(SupplyStatus status: statuses){
				ArrayList supplies = (ArrayList) supplyRepository.findAllByStatusEqualsAndProviderIdEquals(status,id);
				supplies.forEach(result::add);
			}
			return result;
		} else if (userclass.equals("Deliveryman")) {
			for(SupplyStatus status: statuses){
				ArrayList supplies = (ArrayList) supplyRepository.findAllByStatusEqualsAndDeliverymanIdEquals(status,id);
				supplies.forEach(result::add);
			}
			return result;
		} else if (userclass.equals("Optional")) {
			for(SupplyStatus status: statuses){
				ArrayList supplies = (ArrayList)supplyRepository.findAllByStatusEqualsAndReceiverIdEquals(status,id);
				supplies.forEach(result::add);
			}
			return result;
		}
		return null;
	}



	@GetMapping("{id}")
	public Supply getSupply(@PathVariable String id) {
		return supplyRepository.findById(Long.valueOf(id)).get();
	}

	@GetMapping("/create/{n}")
	public void createSupply(@PathVariable String n) throws Exception {
		int num = Integer.parseInt(n);
		int ns = 0;
		for (int i = 0; i < num; i++) {
			Supply supply = new Supply();
//			supply.setId(i+2);
			supply.setDeliverymanId("110590149339175264431");
			supply.setReceiverId("110819605488960914493");
			supply.setProviderId("107512805782972441034");
			supply.setStatus(SupplyStatus.PENDING, system);
			supply.setName(getNewName());
			Date date = new Date();
			date.setTime(123123);
			supply.setDateBegins(date);
			supply.setDateEnds(new Date());
			supply.receiverApprove(system);
			supply.deliverymanApprove(system);
			supply.setConditionNormal();
			for (int j = 0; j < 5; j++) {
				Package aPackage = new Package();
				aPackage.setId(ns++);
				TemperatureCondition condition = new TemperatureCondition();
				condition.setMax(2);
				aPackage.addCondition(condition);
				aPackage.setSupplyId(supply.getId());
				aPackage.setPayload(getNewName());
//				packageRepository.save(aPackage);
				Transmitter transmitter = new Transmitter();
				transmitter.setCurrentPackageId(aPackage.getId());
				transmitter.setCurrentSupplyId(supply.getId());
				aPackage.addTransmitter(transmitter);
				transmitterRepository.save(transmitter);
				var rand = new SecureRandom();
				for (int k = 0; k < 20; k++) {
					Frame frame = new Frame();
					frame.setTemperature(rand.nextFloat());
					frame.setPackageId(aPackage.getId());
					frame.setTransmitterId(transmitter.getId());
					aPackage.getHistory().add(frame);
					transmitter.ReceiveDataFrame(frame);
					framesRepository.save(frame);
				}
				supply.addPackage(aPackage);
				packageRepository.save(aPackage);
			}
			supplyRepository.save(supply);
		}
	}

	@PatchMapping("/{idSupply}/delivered/{userToken}/{userId}")
	Supply deliveredSupply(@PathVariable Supply idSupply, @PathVariable String userToken, @PathVariable String userId) {
		Customer customer;
		try {
			customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			customer = (Customer) ((Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get();
		}
		if (customer.getToken().equals(userToken)) {
			idSupply.setStatus(SupplyStatus.DELIVERED, system);
			supplyRepository.save(idSupply);
		}
		return idSupply;
	}

	@PatchMapping("/{supply}/accepted/{token}/{id}/{userType}")
	Supply acceptSupply(@PathVariable Supply supply, @PathVariable String token, @PathVariable String id, @PathVariable String userType) {
		Customer customer;
		try {
			customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			customer = (Customer) ((Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get();
		}
		if (customer.getToken().equals(token)) {
			if (userType.toLowerCase().equals("deliveryman")) {
				supply.deliverymanApprove(system);
			} else if (userType.toLowerCase().equals("receiver")) {
				supply.receiverApprove(system);
			}
			supplyRepository.save(supply);
		}
		return supply;
	}

	String getNewName() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 15;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}


}
