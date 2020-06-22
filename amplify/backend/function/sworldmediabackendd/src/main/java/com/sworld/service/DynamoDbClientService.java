package com.sworld.service;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.sworld.Constants;
import com.sworld.mapper.DbMapper;
import com.sworld.models.UserMessageInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamoDbClientService {

    public static PutItemOutcome persist(UserMessageInput input) {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();

        client.setRegion(Region.getRegion(Regions.US_EAST_1));

        DynamoDB dynamoDb = new DynamoDB(client);
        log.info("DB is initialized");

        PutItemSpec item = DbMapper.toUserMessageDb(input);

        log.info("Putting item {}", item);
        return dynamoDb.getTable(Constants.USER_MESSAGES_TABLE).putItem(item);
    }
}
