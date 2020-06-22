package com.sworld.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sworld.models.LambdaRequest;
import com.sworld.models.LambdaResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AppHandler implements RequestHandler<LambdaRequest, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest lambdaRequest, Context context) {
        log.info("Receiving request {}", lambdaRequest);

        try {
            switch (lambdaRequest.getPath()) {
                case "/contact":
                    return UserMessagesHandler.handleRequest(lambdaRequest);
                default:
                    throw new UnsupportedOperationException();
            }
        } catch (IOException e) {
            log.error("IOException while serving request", e);
            throw new RuntimeException();
        }
    }
}
