package com.daitan.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

public class ProducerSQS {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(final String[] args) throws Exception {

        SqsService sqs = new SqsService(new URI("http://localhost:4576"));
        String queueUrl = "http://localhost:4576/queue/teste";
        sqs.createQueue("teste");
        sqs.sendMessage(queueUrl, args[0]);
        System.out.println("Message sent");

        HttpPost request = new HttpPost("http://localhost:9179/api/newMessage");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            System.out.println(response.getStatusLine().toString());
        }
    }

}



