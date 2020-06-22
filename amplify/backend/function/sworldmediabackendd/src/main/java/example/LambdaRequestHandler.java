/* Amplify Params - DO NOT EDIT
	ENV
	REGION
Amplify Params - DO NOT EDIT */

package example;

import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sworld.models.LambdaResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class LambdaRequestHandler implements RequestHandler<RequestClass, LambdaResponse>{

    public LambdaResponse handleRequest(RequestClass request, Context context) {
        String greetingString = String.format("Hello %s %s!", request.firstName, request.lastName);

        log.info("LambdaRequestHandler: {}", greetingString);
        return LambdaResponse.builder().isBase64Encoded(true).statusCode(200)
                .body(greetingString).headers(getHeader()).build();
    }

    private Map getHeader() {
        return Collections.singletonMap("Access-Control-Allow-Origin", "*");
    }
}
