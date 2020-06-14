package com.sworld.api;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sworld.Constants;
import com.sworld.mapper.DbMapper;
import com.sworld.models.Response;
import com.sworld.service.DynamoDbClientService;
import com.sworld.service.FeedbackEmailService;
import com.sworld.models.UserMessageRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class UserMessagesHandler implements RequestHandler<UserMessageRequest, Response> {

    @Override
    public Response handleRequest(UserMessageRequest input, Context context) {
        log.info("Getting Request {}, context {}", input, context);

        DynamoDbClientService.persist(input);

        log.info("Finish putting item");

        FeedbackEmailService.send(input);

        return Response.builder().isBase64Encoded(true).statusCode(200)
                .body(input.toString()).headers(getHeader()).build();
    }

    private Map getHeader() {
        return Collections.singletonMap("Access-Control-Allow-Origin", "*");
    }
}
