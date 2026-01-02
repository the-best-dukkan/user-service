package com.tbd.user_service.util;

import com.tbd.user_service.constant.Constant;
import com.tbd.user_service.exception.UserSubNotFoundInHeaderException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class Util {

    private Util() {
    }

    public static String extractUserSubFromRequest(HttpServletRequest request) {
        String userSub = request.getHeader(Constant.X_USER_ID);

        if (StringUtils.isBlank(userSub)) {
            throw new UserSubNotFoundInHeaderException("error.header.x_user_id.notfound");
        }

        return userSub;
    }
}
