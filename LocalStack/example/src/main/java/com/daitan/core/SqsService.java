package com.daitan.core;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.net.URI;
import java.util.List;

public class SqsService {

    private SqsClient client;

    public SqsService(URI endpoint) {
        client = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider( () -> AwsBasicCredentials.create(
                "foobar",
                "foobar"))
                .endpointOverride(endpoint)
                .build();
    }

    public List<Message> receiveMessage(String queueUrl) {
        return client.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(10)
                .build())
                .messages();
    }

    public CreateQueueResponse createQueue(String queueName) {
        return client.createQueue(CreateQueueRequest.builder()
                .queueName(queueName)
                .build());
    }

    public SendMessageResponse sendMessage(String queueUrl, String msg) {
        return client.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(msg)
                .build());
    }

    public void deleteMessage (String queueUrl, Message message) {
        client.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(message.receiptHandle())
                .build());
    }
}

