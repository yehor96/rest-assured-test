package com.yehor.api.models.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RegisteredUserModel {
    private String name;
    private String job;
    private String id;
    private ZonedDateTime createdAt;
}
