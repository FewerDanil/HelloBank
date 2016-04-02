package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_credentials")
public class UserCredentials implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true)
    private String email;

    @Column
    private String hash;

    @Column
    private String session;

    public UserCredentials() {

    }

    public UserCredentials(User user, String email, String hash, String session) {
        this.user = user;
        this.email = email;
        this.hash = hash;
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
