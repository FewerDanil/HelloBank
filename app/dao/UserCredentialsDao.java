package dao;

import models.UserCredentials;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserCredentialsDao {
    public static UserCredentials getUserCredentialsByEmail(EntityManager em, String email) {
        Query q = em.createQuery("SELECT u FROM UserCredentials u WHERE u.email = :email")
                .setParameter("email", email);

        try {
            return (UserCredentials) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
