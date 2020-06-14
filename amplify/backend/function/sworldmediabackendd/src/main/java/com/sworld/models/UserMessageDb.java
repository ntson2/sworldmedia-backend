package com.sworld.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.sworld.Constants;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@DynamoDBTable(tableName= Constants.USER_MESSAGES_TABLE)
public class UserMessageDb {

    @DynamoDBHashKey(attributeName="id")
    String id;

    @DynamoDBAttribute(attributeName="fullname")
    String fullName;

    @DynamoDBAttribute(attributeName="email")
    String email;

    @DynamoDBAttribute(attributeName="phone")
    int phone;

    @DynamoDBAttribute(attributeName="message")
    String message;

    @DynamoDBAttribute(attributeName="language")
    String language;
}
