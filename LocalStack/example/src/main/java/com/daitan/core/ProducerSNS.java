package com.daitan.core;

import java.net.URI;

public class ProducerSNS {

    public static void main(final String[] args) throws Exception {

        SnsService sns = new SnsService(new URI("http://localhost:4575"));
        String topicArn = sns.createTopic().topicArn();
        sns.subscribeToReceiveMessages(topicArn);
        sns.sendMessage(args[0], topicArn);
        System.out.println("Message sent");

    }

}