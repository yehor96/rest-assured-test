package com.yehor.api.models.users;

import com.yehor.api.models.SupportModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserModel {
    private UserDataModel data;
    private SupportModel support;
}
