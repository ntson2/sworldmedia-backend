package com.sworld.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sworld.models.LambdaRequest;
import com.sworld.models.LambdaResponse;
import com.sworld.models.UserMessageInput;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.AssertJUnit.assertEquals;

public class UserMessagesHandlerTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AmazonSimpleEmailService emailService;
    @Mock
    private DynamoDB dynamoDB;
    @Mock
    private Table table;

    private UserMessagesHandler handler;

    @BeforeClass
    public void setup() {
        initMocks(this);
        handler = new UserMessagesHandler();
    }

    @Test
    public void test() throws JsonProcessingException, IOException {
        when(emailService.sendEmail(any())).thenReturn(new SendEmailResult());
        when(dynamoDB.getTable(any())).thenReturn(table);
        when(table.putItem(any(PutItemSpec.class))).thenReturn(new PutItemOutcome(null));

        UserMessageInput input = UserMessageInput.builder()
                .email("son@gmail.com")
                .fullName("son ngo")
                .language("en")
                .message("test message")
                .phone(1234).build();
        LambdaRequest request = LambdaRequest.builder()
                .body(objectMapper.writeValueAsString(input))
                .build();

        LambdaResponse response = handler.handleRequest(request);

        assertEquals("success", response.getBody());
    }
}
