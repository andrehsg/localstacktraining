package com.daitan.core;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

import java.net.URI;

public class SnsService {

    private SnsClient client;

    public SnsService(URI endpoint) {
        client = SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider( () -> AwsBasicCredentials.create(
                "foobar",
                "foobar"))
                .endpointOverride(endpoint)
                .build();
    }

    public CreateTopicResponse createTopic (String myTopic) {

        // Create an Amazon SNS topic.
        CreateTopicResponse createTopicResponse = client.createTopic(CreateTopicRequest.builder()
                .name(myTopic)
                .build());
        return createTopicResponse;

    }

    public PublishResponse sendMessage (String message, String topicArn) {

        final PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();
        final PublishResponse publishResponse = client.publish(publishRequest);
        return publishResponse;
    }

    public SubscribeResponse subscribeToReceiveMessages (String topicArn, String endpoint) {

        SubscribeRequest req = SubscribeRequest.builder()
                .topicArn(topicArn)
                .endpoint(endpoint)
                .protocol("sqs")
                .returnSubscriptionArn(true)
                .build();

        return client.subscribe(req);
    }

}

