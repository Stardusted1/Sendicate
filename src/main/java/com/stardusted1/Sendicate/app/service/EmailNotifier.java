package com.stardusted1.Sendicate.app.service;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.core.users.UserLocale;
import javassist.compiler.ast.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
public class EmailNotifier {

	private JavaMailSender javaMailSender;

	private static String emailMessageTemplateRU = "Ув., %s, Ваш груз был испорчен\n" +
			"Пожалуйста, свяжитесь с ответственными за это\n" +
			"Название груза - %s" +
			"Время - %s";
	private static String emailMessageTopicRU = "🟥Произошло проишестви при доставке гуза";
	private static String emailMessageTemplateUA = "Шановні, %s, Ваш вантаж було зіпсовано\n" +
			"Будь ласка, зв'яжіться з відповідальними за це\n" +
			"Назва вантажу - %s\n" +
			"Час - %s";
	private static String emailMessageTopicUA = "🟥Трапилася подія при доставці вантажу";
	private static String emailMessageTemplateENG = "Dear% s, Your cargo was damaged\n" +
			"Please contact those responsible for this\n" +
			"Cargo Name - %s\n" +
			"Time - %s";
	private static String emailMessageTopicENG = "🟥An event occurred while delivering the cargo";

	public EmailNotifier(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(String toEmail, String subject, String message) {

		var mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);

		javaMailSender.send(mailMessage);
	}
	public void notifyUsersSupplyBadCondition(Deliveryman deliveryman, Provider provider, Receiver receiver, Supply supply){
		String deliverymanMessage = String.format(GetMessageByLocale(
				deliveryman.getLocale()),
				deliveryman.getName(),
				supply.getName(),
				new Date());
		String providerMessage = String.format(GetMessageByLocale(
				provider.getLocale()),
				provider.getName(),
				supply.getName(),
				new Date()
		) ;
		String receiverMessage = String.format(GetMessageByLocale(
				receiver.getLocale()),
				receiver.getName(),
				supply.getName(),
				new Date()
		);

		String deliverymanTopic = GetTopicByLocale(deliveryman.getLocale());
		String providerTopic = GetTopicByLocale(provider.getLocale());
		String receiverTopic = GetTopicByLocale(receiver.getLocale());

		sendMail(provider.getEmail(),providerTopic,providerMessage);
		sendMail(deliveryman.getEmail(),deliverymanTopic,deliverymanMessage);
		sendMail(receiver.getEmail(),receiverTopic,receiverMessage);

	}

	public void notifyUsersAboutNewSupply(Deliveryman deliveryman, Receiver receiver){
		sendMail(deliveryman.getEmail(),
				// TODO: 22.11.2019 message locale in resources
				"You got new supply tp deliver",
				"Check your active supplies");
		sendMail(receiver.getEmail(),
				"You got new supply to receive",
				"Check your active supplies");
	}

	private String GetMessageByLocale(UserLocale locale){
		if(locale.equals(UserLocale.ENG)){
			return emailMessageTemplateENG;
		}else if(locale.equals(UserLocale.RUS)){
			return emailMessageTemplateRU;
		}else if(locale.equals(UserLocale.UKR)){
			return emailMessageTemplateUA;
		}
		return "";
	}

	private String GetTopicByLocale(UserLocale locale){
		if(locale.equals(UserLocale.ENG)){
			return emailMessageTopicENG;
		}else if(locale.equals(UserLocale.RUS)){
			return emailMessageTopicRU;
		}else if(locale.equals(UserLocale.UKR)){
			return emailMessageTopicUA;
		}
		return "";
	}


}