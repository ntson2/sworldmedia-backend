package com.sworld.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sworld.models.LambdaRequest;
import com.sworld.models.LambdaResponse;
import com.sworld.models.UserProfile;
import com.sworld.service.DynamoDbClientService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static LambdaResponse handleRequest(LambdaRequest request) throws IOException {

        UserProfile userProfile = objectMapper.readValue(request.getBody(), UserProfile.class);

        log.info("User {} is logging in", userProfile);

        UserProfile putResult = DynamoDbClientService.getUserProfile(userProfile);

        return LambdaResponse.builder().isBase64Encoded(true).statusCode(200)
                .body(objectMapper.writeValueAsString(putResult)).build();
    }
}