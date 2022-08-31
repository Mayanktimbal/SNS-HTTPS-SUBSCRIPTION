package com.example.demo;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

@RestController
public class NewController {
	 private AmazonSNS amazonSns;
	private static final  Logger LOGGER = Logger.getLogger("NewController");
	@RequestMapping(value = "/pingtosubscribe", method = RequestMethod.GET)
	public String Subscribe()
	{
		LOGGER.info("Hit the ping endpoint");
	
	this.amazonSns= AmazonSNSClientBuilder.standard()
    .withRegion("us-east-1")
    .withCredentials(
        new AWSStaticCredentialsProvider(new BasicAWSCredentials("KeyID", "SecretKey")))
    .build();
	
    SubscribeRequest request = new SubscribeRequest("TOPIC-ARN", "https", "URL_Which want to subscribe");

    SubscribeResult result = amazonSns.subscribe(request);
        System.out.println("Subscription ARN is " + result.getSubscriptionArn() + "\n\n Status is " + result.getSdkHttpMetadata().getHttpStatusCode());

		return "success";
		
	}
}
