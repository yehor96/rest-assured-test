package com.yehor.api.services;

import com.yehor.api.models.users.RegisterUserModel;
import com.yehor.api.models.users.RegisteredUserModel;
import com.yehor.api.models.users.UserModel;
import com.yehor.api.models.users.UserPageModel;
import lombok.experimental.UtilityClass;

import static java.lang.String.format;

@UtilityClass
public class UsersService extends BaseService {

    private static final String URI = "/api/users";
    private static final String PAGE_PARAM = "?page=%d";

    public static UserPageModel getUserPage(int page) {
        String requestUri = URI + format(PAGE_PARAM, page);
        return doGet(requestUri, 200, UserPageModel.class);
    }

    public static UserModel getUserWithId(int id) {
        String requestUri = URI + "/" + id;
        return doGet(requestUri, 200, UserModel.class);
    }

    public static String getNotExistingUserWithId(int id) {
        String requestUri = URI + "/" + id;
        return doGet(requestUri, 404, String.class);
    }

    public static RegisteredUserModel registerUser(RegisterUserModel requestBody) {
        return doPost(URI, requestBody, 201, RegisteredUserModel.class);
    }

    public static String deleteUser(int id) {
        String requestUri = URI + "/" + id;
        return doDelete(requestUri, 204, String.class);
    }
}
