package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
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
	@GetMapping("{id}")
	public Supply getSupply( @PathVariable String id){
		return supplyRepository.findById(Long.valueOf(id)).get();
	}


}
