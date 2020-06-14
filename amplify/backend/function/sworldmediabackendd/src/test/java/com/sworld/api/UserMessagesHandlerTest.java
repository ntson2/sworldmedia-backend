package com.sworld.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.sworld.models.Response;
import com.sworld.models.UserMessageRequest;
import com.sworld.service.DynamoDbClientService;
import com.sworld.service.FeedbackEmailService;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.AssertJUnit.assertEquals;

public class UserMessagesHandlerTest {

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
    public void test() {
        when(emailService.sendEmail(any())).thenReturn(new SendEmailResult());
        when(dynamoDB.getTable(any())).thenReturn(table);
        when(table.putItem(any(PutItemSpec.class))).thenReturn(new PutItemOutcome(null));

        UserMessageRequest input = UserMessageRequest.builder()
                .email("son@gmail.com")
                .fullName("son ngo")
                .language("en")
                .message("test message")
                .phone(1234).build();

        Response response = handler.handleRequest(input, null);

        assertEquals("success", response.getBody());
    }
}
