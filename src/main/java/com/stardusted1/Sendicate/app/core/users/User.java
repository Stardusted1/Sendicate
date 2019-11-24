package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.service.Variables;
import org.stringtemplate.v4.ST;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class User {
    @Id
    protected String id;
    @Column(name = "token")
    protected String token;
    @Column(name = "isBanned")
    protected boolean isBanned;
    @Column(name = "name", unique = true)
    protected String name;
    @Column(name = "dateOfRegistration")
    protected Date dateOfRegistration;
    @Column(name = "password")
    protected String password;
	@Column(name = "locale")
	protected UserLocale locale;
    @Column(name = "pictureUrl")
    protected String pictureUrl;

    public abstract String getRole();

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

	public User() {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
