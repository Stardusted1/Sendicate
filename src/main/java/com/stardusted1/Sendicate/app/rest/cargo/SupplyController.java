package com.stardusted1.Sendicate.app.rest.cargo;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="api/supply")
public class SupplyController {
	@Autowired
	SupplyRepository supplyRepository;

	@PostMapping
	public Supply newSupply(
			@RequestParam String name
	) {
		Supply supply = new Supply();
		supply.setName(name);
		supplyRepository.save(supply);
		return  supply;
	}

	@GetMapping("{id}/get")
	public Iterable<Supply> getSupplies(@PathVariable("id") String id){
		String userclass = SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName();
		if(userclass.equals("Provider")){
			return supplyRepository.findAllByProviderIdEquals(id);
		}else if(userclass.equals("Deliveryman")){
			return supplyRepository.findAllByDeliverymanIdEquals(id);
		}else if(userclass.equals("Optional")){
			return supplyRepository.findAllByReceiverIdEquals(id);
		}
		return null;
	}
	@GetMapping("{id}")
	public Supply getSupply( @PathVariable String id){
		return supplyRepository.findById(Long.valueOf(id)).get();
	}


}
