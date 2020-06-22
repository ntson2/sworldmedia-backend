package com.sworld.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

/**
 * https://docs.aws.amazon.com/apigateway/latest/developerguide/set-up-lambda-proxy-integrations.html#api-gateway-simple-proxy-for-lambda-input-format
 */
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LambdaRequest {

    String resource;
    String path;
    String httpMethod;
    Map headers;
    Map multiValueHeaders;
    Map queryStringParameters;
    Map multiValueQueryStringParameters;
    Map pathParameters;
    Map stageVariables;
    Map requestContext;
    String body;
    boolean isBase64Encoded;
}
