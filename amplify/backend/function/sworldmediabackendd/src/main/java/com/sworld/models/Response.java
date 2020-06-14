package com.sworld.models;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Response {

    boolean isBase64Encoded;
    int statusCode;
    String body;
    Object headers;
}
