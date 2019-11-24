package com.stardusted1.Sendicate.app.core.users;

import com.stardusted1.Sendicate.app.service.Authority;
import com.stardusted1.Sendicate.app.service.Variables;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.stringtemplate.v4.ST;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

@MappedSuperclass
public abstract class User implements UserDetails {
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
    @Column(name = "authorities")
    protected ArrayList<GrantedAuthority> authorities = new ArrayList<>();

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

    @Override
    public abstract Collection<? extends GrantedAuthority> getAuthorities();

    public  Collection<? extends GrantedAuthority> setAdminAuthority(){
        authorities.clear();
        authorities.add(new Authority("ADMIN"));
        return authorities;
    }

    public  Collection<? extends GrantedAuthority> setCustomerAuthority(){
        authorities.clear();
        authorities.add(new Authority("CUSTOMER"));
        return authorities;
    }

    public  Collection<? extends GrantedAuthority> setUserAuthority(){
        authorities.clear();
        authorities.add(new Authority("USER"));
        return authorities;
    }



    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isBanned;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
