package com.example.demo;

import java.util.logging.Logger;

import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Test-Topic")
public class MayankController {
	private static final  Logger LOGGER = Logger.getLogger("MayankController");
	    @NotificationSubscriptionMapping
	    public void confirmUnsubscribeMessage(
	      NotificationStatus notificationStatus) {
	        notificationStatus.confirmSubscription();
	        LOGGER.info("subscription confirmed");
	    }
	 
	    @NotificationMessageMapping
	    public void receiveNotification(@NotificationMessage String message, 
	      @NotificationSubject String subject) {
	        // handle message
	    	
	    	LOGGER.info("Message Recieved" + message);
	    }

	    @NotificationUnsubscribeConfirmationMapping
	    public void confirmSubscriptionMessage(
	      NotificationStatus notificationStatus) {
	         notificationStatus.confirmSubscription();
	     	LOGGER.info("unsubscribe done");
	    }   
	    
	}

