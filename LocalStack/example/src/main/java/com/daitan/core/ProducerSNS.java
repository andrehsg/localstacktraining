package com.daitan.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;

import java.net.URI;

public class ProducerSNS {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(final String[] args) throws Exception {

        SnsService sns = new SnsService(new URI("http://localhost:4575"));
        String topicArn = sns.createTopic("myTopic").topicArn();
        System.out.println("Topic ARN: " + topicArn);
        SubscribeResponse subscribeResponse = sns.subscribeToReceiveMessages(topicArn,
            "http://localhost:4576/queue/teste");
        System.out.println("SubscriptResponse: " + subscribeResponse.subscriptionArn());
        PublishResponse messageResponse = sns.sendMessage(args[0], topicArn);
        System.out.println("MessageResponse: " + messageResponse);
        System.out.println("Message sent");

        HttpPost request = new HttpPost("http://localhost:9179/api/newMessage");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            System.out.println(response.getStatusLine().toString());
        }

    }

}