package com.stardusted1.Sendicate.app.rest.cargo;

import antlr.collections.List;
import com.stardusted1.Sendicate.app.core.cargo.Transmitter;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;

@RestController    // This means that this class is a Controller
@RequestMapping(path="api/transmitter")
public class TransmitterController {
    @Autowired
    System system;

    @GetMapping("/{transmitter}")
    boolean isAvailable(@PathVariable Transmitter transmitter){
        if(transmitter.getCurrentSupplyId()==-1){
            return true;
        }
        return false;
    }

    @GetMapping("/tt/")
    LinkedList<Transmitter> createNew(){
        LinkedList<Transmitter> transmitters = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Transmitter transmitter1 = new Transmitter();
            transmitter1.setCurrentSupplyId(-1);
            transmitter1.setCurrentPackageId(-1);
            transmitter1.setToken(System.generateToken("Transmitter"));
            transmitter1.setDateOfRegistration(new Date());
            system.transmitterRepository.save(transmitter1);
            transmitters.add(transmitter1);
        }
        return transmitters;
    }
}
