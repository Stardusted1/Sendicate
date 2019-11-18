package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.service.Variables;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;
    @Column(name = "token")
    protected String token;
    @Column(name = "isBanned")
    protected boolean isBanned;
    @Column(name = "name")
    protected String name;
    @Column(name = "dateOfRegistration")
    protected Date dateOfRegistration;
    @Column(name = "login")
    protected String login;
    @Column(name = "password")
    protected String password;
	@Column(name = "locale")
	protected UserLocale locale;

	public User() {

	}

    public User(String name, String login, String password) {
        this.id = Variables.AddNewUser();
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateOfRegistration = new Date();
        this.token = "";
        this.isBanned = false;
        this.locale = UserLocale.ENG;

    }

	public UserLocale getLocale() {
		return locale;
	}

	public void setLocale(UserLocale locale) {
		this.locale = locale;
	}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void Ban() {
        isBanned = true;
    }

    public void UnBan() {
        isBanned = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
