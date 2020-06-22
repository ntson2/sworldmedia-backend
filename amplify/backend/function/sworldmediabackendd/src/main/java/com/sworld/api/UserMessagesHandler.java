package com.sworld.api;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.sworld.models.LambdaRequest;
import com.sworld.models.LambdaResponse;
import com.sworld.service.DynamoDbClientService;
import com.sworld.service.FeedbackEmailService;
import com.sworld.models.UserMessageInput;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class UserMessagesHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static LambdaResponse handleRequest(LambdaRequest request) throws IOException {

        UserMessageInput input = objectMapper.readValue(request.getBody(), UserMessageInput.class);

        log.info("UserMessagesHandler getting Request {}, context {}", input);

        DynamoDbClientService.persist(input);

        log.info("Finish putting item");

        FeedbackEmailService.send(input);

        return LambdaResponse.builder().isBase64Encoded(true).statusCode(200)
                .body(input.toString()).headers(getHeader()).build();
    }

    private static Map getHeader() {
        return Collections.singletonMap("Access-Control-Allow-Origin", "*");
    }
}
