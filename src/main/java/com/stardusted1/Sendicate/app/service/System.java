package com.stardusted1.Sendicate.app.service;

import com.stardusted1.Sendicate.app.core.repositories.AdministratorRepository;
import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.repositories.ReceiverRepository;
import com.stardusted1.Sendicate.app.core.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Quota;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
@Component
public class System {
	protected static SecureRandom random = new SecureRandom();

	@Autowired
	static EmailNotifier emailNotifier;
	@Autowired
	static ProviderRepository  providerRepository;
	@Autowired
	static DeliverymanRepository deliverymanRepository;
	@Autowired
	static ReceiverRepository receiverRepository;
	@Autowired
	static AdministratorRepository administratorRepository;

	public static synchronized String generateToken(String type) {
		long longToken = Math.abs(random.nextLong());
		return type + "_" + Long.toString(longToken, 18);
	}
	public static synchronized String generatePassword() {
		long longToken = Math.abs(random.nextLong());
		return Long.toString(longToken, 8);
	}
	public static boolean renewPassword(String name){
		Optional<Provider> provider = providerRepository.findFirstByName(name);

		if(provider.isPresent()){
			Provider nProvider = provider.get();
			ResourceBundle rb = ResourceBundle.getBundle("Messages",getLocale(nProvider.getLocale()));
			emailNotifier.sendMail(
					nProvider.getEmails().getFirst(),
					rb.getString("System.newpassword.topic"),
					String.format(rb.getString("System.newpassword.message"), generatePassword()));
			return true;
		}
		Optional<Deliveryman> deliveryman = deliverymanRepository.findFirstByName(name);
		if(deliveryman.isPresent()){
			Deliveryman nProvider = deliveryman.get();
			ResourceBundle rb = ResourceBundle.getBundle("Messages",getLocale(nProvider.getLocale()));
			emailNotifier.sendMail(
					nProvider.getEmails().getFirst(),
					rb.getString("System.newpassword.topic"),
					String.format(rb.getString("System.newpassword.message"), generatePassword()));
			return true;
		}
		Optional<Receiver> receiver = receiverRepository.findFirstByName(name);
		if(receiver.isPresent()){
			Receiver nProvider = receiver.get();
			ResourceBundle rb = ResourceBundle.getBundle("Messages",getLocale(nProvider.getLocale()));
			emailNotifier.sendMail(
					nProvider.getEmail(),
					rb.getString("System.newpassword.topic"),
					String.format(rb.getString("System.newpassword.message"), generatePassword()));
			return true;
		}
		Optional<Administrator> administrator = administratorRepository.findFirstByName(name);
		if(administrator.isPresent()){
			Administrator nProvider = administrator.get();
			ResourceBundle rb = ResourceBundle.getBundle("Messages",getLocale(nProvider.getLocale()));
			emailNotifier.sendMail(
					nProvider.getEmail(),
					rb.getString("System.newpassword.topic"),
					String.format(rb.getString("System.newpassword.message"), generatePassword()));
			return true;
		}
		return false;
	}
	public static Locale getLocale(UserLocale userLocale){
		if(userLocale.equals(UserLocale.RUS)){
			return Locale.forLanguageTag("Ru");
		}
		if(userLocale.equals(UserLocale.UKR)){
			return Locale.forLanguageTag("Ukr");
		}
		if(userLocale.equals(UserLocale.ENG)){
			return Locale.ENGLISH;
		}
		return Locale.ENGLISH;

	}

	public static String AuthentificateUser(String login, String pass) {
		return generateToken("User");
	}
}
