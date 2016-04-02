package controllers.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.UUID;

public class PasswordUtil {
    public static final String salt = "a0sdaHT@_";

    public static String encrypt(String password) {
        return DigestUtils.md5Hex(salt + password);
    }

    public static boolean checkPassword(String password) {
        return password.length() >= 6;
    }
}
