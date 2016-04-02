package dao;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDao {
    public static User getUserByUserId(EntityManager em, String userId) {
        return em.find(User.class, userId);
    }

    public static User getUserByDisplayName(EntityManager em, String displayName) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.displayName = :displayName")
                .setParameter("displayName", displayName);

        try {
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
