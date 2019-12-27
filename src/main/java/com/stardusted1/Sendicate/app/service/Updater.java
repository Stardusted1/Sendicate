package com.stardusted1.Sendicate.app.service;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Updater {
    /**
     * Creates daily task to update Contracts and client's accounts
     */
    public void start(System system) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                UpdateSupplies(system);
                timer.schedule(this, getTomorrowDate());
            }
        };
        timer.schedule(task, getTomorrowDate());
    }

    private Date getTomorrowDate(){
        LocalDate date = LocalDate.now().plusDays(1);
        return Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private void UpdateSupplies(System system){
        Date now = new Date();
        ArrayList<Supply> supplies = (ArrayList<Supply>) system.supplyRepository.findAllByStatusEquals(SupplyStatus.DELIVERING);
        for(Supply supply: supplies){
            if(supply.getDateEnds().before(now)){
                supply.setStatus(SupplyStatus.UNDELIVERED,system);
            }
        }

    }
}
