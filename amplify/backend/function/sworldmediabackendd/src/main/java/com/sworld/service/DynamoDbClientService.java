package com.sworld.service;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.sworld.Constants;
import com.sworld.mapper.DbMapper;
import com.sworld.models.UserMessageInput;
import com.sworld.models.UserProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class DynamoDbClientService {

    public static PutItemOutcome persist(UserMessageInput input) {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();

        DynamoDB dynamoDb = new DynamoDB(client);
        log.info("DB is initialized");

        PutItemSpec item = DbMapper.toUserMessageDb(input);

        log.info("Putting item {}", item);
        return dynamoDb.getTable(Constants.USER_MESSAGES_TABLE).putItem(item);
    }

    public static UserProfile getUserProfile(UserProfile input) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

        DynamoDB dynamoDb = new DynamoDB(client);

        Table table = dynamoDb.getTable(Constants.USER_PROFILE_TABLE);

        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.addComponent("loginType", input.getLoginType().toString());
        primaryKey.addComponent("id", input.getId());
        Item item = table.getItem(primaryKey);

        if (item == null) {
            input.setInternalId(UUID.randomUUID());
            input.setFirstLogin(System.currentTimeMillis());
            input.setFirstLogin(System.currentTimeMillis());

            PutItemSpec dbRecord = DbMapper.toUserProfileDb(input);
            table.putItem(dbRecord);
            return input;
        } else {
            AttributeUpdate attributeUpdate = new AttributeUpdate("lastLogin");
            attributeUpdate.put(System.currentTimeMillis());

            table.updateItem(primaryKey, attributeUpdate);

            return DbMapper.toUserProfile(item);
        }
    }
}
