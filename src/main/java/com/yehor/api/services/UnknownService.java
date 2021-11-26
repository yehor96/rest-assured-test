package com.yehor.api.services;

import com.yehor.api.models.unknown.ResourcePageModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UnknownService extends BaseService {

    private static final String URI = "/api/unknown";

    public static ResourcePageModel getResources() {
        return doGet(URI, 200, ResourcePageModel.class);
    }
}
