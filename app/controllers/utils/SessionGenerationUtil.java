package controllers.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.UUID;

public class SessionGenerationUtil {
    public static final String salt = "a(I&!*a>_";

    public static String generate(String userId) {
        return DigestUtils.md5Hex(salt + userId + (new Date()).getTime());
    }
}
