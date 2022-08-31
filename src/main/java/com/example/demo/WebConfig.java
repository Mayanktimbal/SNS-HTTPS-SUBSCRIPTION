package com.example.demo;
import static org.springframework.cloud.aws.messaging.endpoint.config.NotificationHandlerMethodArgumentResolverConfigurationUtils.getNotificationHandlerMethodArgumentResolver;

import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.context.annotation.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass("org.springframework.web.servlet.config.annotation.WebMvcConfigurer")
public class WebConfig extends WebMvcConfigurerAdapter {

   
    private AmazonSNS amazonSns;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	this.amazonSns= AmazonSNSClientBuilder.standard()
    			        .withRegion("us-east-1")
    			        .withCredentials(
    			            new AWSStaticCredentialsProvider(new BasicAWSCredentials("AccessKeyID", "AccessKey")))  // add your credintials
    			        .build();
    			  
        argumentResolvers.add(getNotificationHandlerMethodArgumentResolver(this.amazonSns));
    }
}