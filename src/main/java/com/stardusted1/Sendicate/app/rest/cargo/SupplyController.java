package com.stardusted1.Sendicate.app.rest.cargo;

import com.stardusted1.Sendicate.app.core.cargo.Package;
import com.stardusted1.Sendicate.app.core.cargo.*;
import com.stardusted1.Sendicate.app.core.cargo.condition.TemperatureCondition;
import com.stardusted1.Sendicate.app.core.repositories.*;
import com.stardusted1.Sendicate.app.core.users.Customer;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.stardusted1.Sendicate.app.service.System.parseBody;

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


    @PostMapping("/new_supply")
    public boolean newSupply(@RequestBody String request) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(request);
        return true;
    }

    @GetMapping("{id}/get")
    public Iterable<Supply> getSupplies(@PathVariable("id") String id) {
        String userclass = SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName();
        if (userclass.equals("Provider")) {
            return supplyRepository.findAllByProviderIdEquals(id);
        } else if (userclass.equals("Deliveryman")) {
            return supplyRepository.findAllByDeliverymanIdEquals(id);
        } else if (userclass.equals("Optional")) {
            return supplyRepository.findAllByReceiverIdEquals(id);
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
            supply.setStatus(SupplyStatus.PENDING, system);
            supply.setName(getNewName());
            supply.setDeliverymanId("110590149339175264431");
            supply.setReceiverId("110819605488960914493");
            supply.setProviderId("107512805782972441034");
            Date date = new Date();
            date.setTime(123123);
            supply.setDateBegins(date);
            supply.setDateEnds(new Date());
            supply.receiverApprove();
            supply.deliverymanApprove();
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
                supply.deliverymanApprove();
            } else if (userType.toLowerCase().equals("receiver")) {
                supply.receiverApprove();
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
