package com.sworld.mapper;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.sworld.models.UserMessageInput;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class DbMapper {

    public PutItemSpec toUserMessageDb(UserMessageInput request) {
        return new PutItemSpec().withItem(new Item().with("id", UUID.randomUUID().toString())
                .with("fullname", request.getFullName())
                .with("email", request.getEmail())
                .with("phone", request.getPhone())
                .with("message", request.getMessage()));
    }
}
