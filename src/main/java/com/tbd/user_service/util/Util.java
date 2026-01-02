package com.tbd.user_service.util;

import com.tbd.user_service.constant.Constant;
import com.tbd.user_service.exception.UserSubNotFoundInHeaderException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

public class Util {

    private Util() {
    }

    public static String extractUserSubFromRequest(HttpServletRequest request, MessageSource messageSource) {
        String userSub = request.getHeader(Constant.X_USER_ID);

        if (StringUtils.isBlank(userSub)) {
            throw new UserSubNotFoundInHeaderException(Translator.translate(messageSource, "error.header.x_user_id.notfound"));
        }

        return userSub;
    }
}
