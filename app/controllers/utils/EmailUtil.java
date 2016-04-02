package controllers.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailUtil {
    public static boolean checkEmail (String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
