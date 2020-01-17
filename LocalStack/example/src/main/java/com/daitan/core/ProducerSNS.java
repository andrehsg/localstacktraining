package com.daitan.core;

import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;

import java.net.URI;

public class ProducerSNS {

    public static void main(final String[] args) throws Exception {

        SnsService sns = new SnsService(new URI("http://localhost:4575"));
        String topicArn = sns.createTopic().topicArn();
        System.out.println("Topic ARN: " + topicArn);
        SubscribeRequest subscribeRequest = sns.subscribeToReceiveMessages(topicArn);
        System.out.println("SubscriptResponse: " + subscribeRequest);
        PublishResponse messageResponse = sns.sendMessage(args[0], topicArn);
        System.out.println("MessageResponse: " + messageResponse);
        System.out.println("Message sent");

    }

}