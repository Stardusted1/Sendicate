package com.stardusted1.Sendicate.app.core.users;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class NewUser extends Customer {
    @Column(name = "email",unique = true)
    protected String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getRole() {
        return "USER";
    }
}
