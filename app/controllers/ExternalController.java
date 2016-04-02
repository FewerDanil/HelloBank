package controllers;

import akka.dispatch.Futures;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.utils.*;
import dao.UserCredentialsDao;
import dao.UserDao;
import exceptions.*;
import models.User;
import models.UserCredentials;
import play.db.jpa.JPA;
import play.libs.Akka;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scala.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ExternalController extends Controller {
    public static final String jpaDB = "default";

    public static F.Promise<Result> createUser() {
        Http.RequestBody body = request().body();

        Future<Result> f = Futures.future(() -> {
            EntityManager em = null;
            EntityTransaction tx = null;
            Status status;

            try {
                em = JPA.em(jpaDB);
                tx = em.getTransaction();
                String email, password, displayName;

                try {
                    JsonNode json = body.asJson();

                    email = json.get("email").asText();
                    password = json.get("password").asText();
                    displayName = json.get("display_name").asText();
                } catch (Exception e) {
                    throw new WrongJsonFormatException();
                }

                tx.begin();

                if(UserCredentialsDao.getUserCredentialsByEmail(em, email) != null) {
                    throw new EmailExistsException();
                }

                if(UserDao.getUserByDisplayName(em, displayName) != null) {
                    throw new DisplayNameExistsException();
                }

                if(!PasswordUtil.checkPassword(password)) {
                    throw new PasswordTooEasyException();
                }

                if(!EmailUtil.checkEmail(email)) {
                    throw new EmailNotValidException();
                }

                User user = new User(
                        UserIdGenerationUtil.generate(),
                        displayName,
                        false);

                UserCredentials userCredentials = new UserCredentials(
                        user,
                        email,
                        PasswordUtil.encrypt(password),
                        SessionGenerationUtil.generate(user.getUserId()));

                em.persist(user);
                em.persist(userCredentials);

                tx.commit();

                status = ok(
                        "{" +
                        "\"status\":200" +
                        "}");
            } catch (Exception e) {
                status = ExceptionConvertingUtil.convert(e);
            } finally {
                if (em != null) {
                    em.close();
                }

                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            }

            return status.as("application/json");
        }, Akka.system().dispatcher());

        return F.Promise.wrap(f);
    }
}
