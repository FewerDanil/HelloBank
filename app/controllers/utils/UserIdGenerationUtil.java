package controllers.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.UUID;

public class UserIdGenerationUtil {
    public static final String salt = "01a/.d^_";

    public static String generate() {
        return DigestUtils.md5Hex(salt + UUID.randomUUID().toString() + (new Date()).getTime());
    }
}
