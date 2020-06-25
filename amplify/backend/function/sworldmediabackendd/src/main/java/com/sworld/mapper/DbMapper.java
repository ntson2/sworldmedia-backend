package com.sworld.mapper;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.sworld.models.LoginType;
import com.sworld.models.UserMessageInput;
import com.sworld.models.UserProfile;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class DbMapper {

    public PutItemSpec toUserMessageDb(UserMessageInput input) {
        return new PutItemSpec().withItem(new Item()
                .with("id", UUID.randomUUID())
                .with("fullname", input.getFullName())
                .with("email", input.getEmail())
                .with("phone", input.getPhone())
                .with("message", input.getMessage()));
    }

    public PutItemSpec toUserProfileDb(UserProfile input) {
        return new PutItemSpec().withItem(new Item()
                .with("loginType", input.getLoginType().toString())
                .with("id", input.getId())
                .with("email", input.getEmail())
                .with("name", input.getName())
                .with("internalId", input.getInternalId().toString())
                .with("firstLogin", input.getFirstLogin())
                .with("lastLogin", input.getLastLogin()));
    }

    public UserProfile toUserProfile(Item item) {
        return UserProfile.builder()
                .email(item.getString("email"))
                .loginType(LoginType.valueOf(item.getString("loginType")))
                .id(item.getString("id"))
                .name(item.getString("name"))
                .internalId(UUID.fromString(item.getString("internalId")))
                .firstLogin(item.getLong("firstLogin"))
                .lastLogin(item.getLong("lastLogin"))
                .build();
    }
}
