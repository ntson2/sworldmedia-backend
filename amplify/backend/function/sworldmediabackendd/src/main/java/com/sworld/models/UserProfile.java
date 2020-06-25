package com.sworld.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserProfile {

    @NonNull
    LoginType loginType;

    @NonNull
    String id;

    @NonNull
    String email;

    @NonNull
    String name;

    UUID internalId;

    long lastLogin;
    long firstLogin;
}
