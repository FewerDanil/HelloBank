package controllers;

import akka.dispatch.Futures;
import com.fasterxml.jackson.databind.JsonNode;
import models.Test;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
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

    public static F.Promise<Result> test() {
        Http.RequestBody body = request().body();

        Future<Result> f = Futures.future(() -> {
            EntityManager em = null;
            EntityTransaction tx = null;
            Result result;

            try {
                em = JPA.em(jpaDB);
                tx = em.getTransaction();
                String str;

                try {
                    JsonNode json = body.asJson();

                    str = json.get("string").asText();
                } catch (Exception e) {
                    throw new Exception("json error");
                }

                tx.begin();

                em.persist(new Test(str));
                result = ok("ok");

                tx.commit();
            } catch (Exception e) {
                result = ok(e.getMessage());
            } finally {
                if (em != null) {
                    em.close();
                }

                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            }

            return result;
        }, Akka.system().dispatcher());

        return F.Promise.wrap(f);
    }
}
