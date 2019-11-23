package com.stardusted1.Sendicate.app.service;

import com.stardusted1.Sendicate.app.core.repositories.*;
import com.stardusted1.Sendicate.app.core.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
//@Scope("singleton")
public class System {

	// TODO: 22.11.2019 update supply status every dayy

    protected static SecureRandom random = new SecureRandom();

    @Autowired
    public EmailNotifier emailNotifier;
    @Autowired
    public ProviderRepository providerRepository;
    @Autowired
    public DeliverymanRepository deliverymanRepository;
    @Autowired
    public ReceiverRepository receiverRepository;
    @Autowired
    public AdministratorRepository administratorRepository;
    @Autowired
    public SupplyRepository supplyRepository;
    @Autowired
    public PackageRepository packageRepository;
    @Autowired
    public TransmitterRepository transmitterRepository;
    @Autowired
    public FramesRepository framesRepository;
    @Autowired
    public NewUserRepository newUserRepository;

    public static synchronized String generateToken(String type) {
        long longToken = Math.abs(random.nextLong());
        return type + "_" + Long.toString(longToken, 18);
    }

    public static synchronized String generatePassword() {
        long longToken = Math.abs(random.nextLong());
        return Long.toString(longToken, 8);
    }

    public Locale getLocale(UserLocale userLocale) {
        if (userLocale.equals(UserLocale.RUS)) {
            return Locale.forLanguageTag("Ru");
        }
        if (userLocale.equals(UserLocale.UKR)) {
            return Locale.forLanguageTag("Ukr");
        }
        if (userLocale.equals(UserLocale.ENG)) {
            return Locale.ENGLISH;
        }
        return Locale.ENGLISH;

    }

    public UserLocale getUserLocale(String userLocale){
        if (userLocale.toLowerCase().equals("ru")) {
            return UserLocale.RUS;
        }
        if (userLocale.toLowerCase().equals("ukr")) {
            return UserLocale.UKR;
        }
        if (userLocale.toLowerCase().equals("en")) {
            return UserLocale.ENG;
        }
        return UserLocale.ENG;
    }

    public String AuthenticateUser(String login, String pass) {
        return generateToken("User");
    }

    public boolean renewPassword(String name) {
        Optional<Provider> provider = providerRepository.findFirstByName(name);

        if (provider.isPresent()) {
            Provider nProvider = provider.get();
            ResourceBundle rb = ResourceBundle.getBundle("Messages", getLocale(nProvider.getLocale()));
            emailNotifier.sendMail(
                    nProvider.getEmails().getFirst(),
                    rb.getString("System.newpassword.topic"),
                    String.format(rb.getString("System.newpassword.message"), generatePassword()));
            return true;
        }
        Optional<Deliveryman> deliveryman = deliverymanRepository.findFirstByName(name);
        if (deliveryman.isPresent()) {
            Deliveryman nProvider = deliveryman.get();
            ResourceBundle rb = ResourceBundle.getBundle("Messages", getLocale(nProvider.getLocale()));
            emailNotifier.sendMail(
                    nProvider.getEmails().getFirst(),
                    rb.getString("System.newpassword.topic"),
                    String.format(rb.getString("System.newpassword.message"), generatePassword()));
            return true;
        }
        Optional<Receiver> receiver = receiverRepository.findFirstByName(name);
        if (receiver.isPresent()) {
            Receiver nProvider = receiver.get();
            ResourceBundle rb = ResourceBundle.getBundle("Messages", getLocale(nProvider.getLocale()));
            emailNotifier.sendMail(
                    nProvider.getEmail(),
                    rb.getString("System.newpassword.topic"),
                    String.format(rb.getString("System.newpassword.message"), generatePassword()));
            return true;
        }
        Optional<Administrator> administrator = administratorRepository.findFirstByName(name);
        if (administrator.isPresent()) {
            Administrator nProvider = administrator.get();
            ResourceBundle rb = ResourceBundle.getBundle("Messages", getLocale(nProvider.getLocale()));
            emailNotifier.sendMail(
                    nProvider.getEmail(),
                    rb.getString("System.newpassword.topic"),
                    String.format(rb.getString("System.newpassword.message"), generatePassword()));
            return true;
        }
        return false;
    }
}
