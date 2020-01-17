package com.daitan.core;

import lombok.extern.java.Log;
import software.amazon.awssdk.services.sqs.model.Message;

import java.net.URI;
import java.util.List;

@Log
public final class ConsumerSQS {

    private ConsumerSQS() {

    }

    public static void consume() throws Exception {

        SqsService sqs = new SqsService(new URI("http://localhost:4576"));
        String queueUrl = "http://localhost:4576/queue/teste";
        sqs.createQueue("teste");
        log.info("receiving messages...");
        List<Message> messages = sqs.receiveMessage(queueUrl);
        messages.stream().forEach(
            item -> {
                log.info(item.body());
                sqs.deleteMessage(queueUrl, item);
            });
    }
}
