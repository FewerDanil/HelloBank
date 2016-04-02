package controllers.utils;

import exceptions.*;
import play.mvc.Results.Status;

import static play.mvc.Results.status;

public class ExceptionConvertingUtil {
    public static Status convert(Exception e) {
        Status status;

        if (e instanceof WrongJsonFormatException) {
            status = status(
                    501,
                    "{" +
                    "\"status\":501," +
                    "\"message\":\"Wrong JSON format\"" +
                    "}");
        } else if (e instanceof DisplayNameExistsException) {
            status = status(
                    502,
                    "{" +
                    "\"status\":502," +
                    "\"message\":\"Display name already exists\"" +
                    "}");
        } else if (e instanceof EmailExistsException) {
            status = status(
                    503,
                    "{" +
                    "\"status\":503," +
                    "\"message\":\"Email already exists\"" +
                    "}");
        } else if (e instanceof PasswordTooEasyException) {
            status = status(
                    504,
                    "{" +
                    "\"status\":504," +
                    "\"message\":\"Password is too easy\"" +
                    "}");
        } else if (e instanceof EmailNotValidException) {
            status = status(
                    505,
                    "{" +
                    "\"status\":505," +
                    "\"message\":\"Email isn't valid\"" +
                    "}");
        } else {
            status = status(
                    500,
                    "{" +
                    "\"status\":500," +
                    "\"message\":\"" + e.toString() + "\"" +
                    "}");
        }

        return status.as("application/json");
    }
}
