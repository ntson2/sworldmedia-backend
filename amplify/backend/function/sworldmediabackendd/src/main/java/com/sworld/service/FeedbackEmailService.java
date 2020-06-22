package com.sworld.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.sworld.models.Language;
import com.sworld.models.UserMessageInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedbackEmailService {

    static final String FROM = "contact@s-worldmedia.com";

    static final String SUBJECT_VN = "Chúng tôi đã nhận lời nhắn của bạn";
    static final String SUBJECT_EN = "We have received your message";

    private static String prepareBody(UserMessageInput feebback) {
        return feebback.toString();
    }

    private static String getSubject(String language) {
        return Language.vn.toString().equals(language) ? SUBJECT_VN : SUBJECT_EN;
    }

    public static SendEmailResult send(UserMessageInput feebback) {

        try {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.defaultClient();

            log.info("Email client is initialized");

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(FROM))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content().withCharset("UTF-8")
                                            .withData(prepareBody(feebback))))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(getSubject(feebback.getLanguage()))))
                    .withSource(FROM);

            SendEmailResult result = client.sendEmail(request);
            log.info("Email was successfully sent with content {}", feebback);
            return result;
        } catch (Exception e) {
            log.error("Error sending email {}", feebback, e);
        }

        return null;
    }
}
