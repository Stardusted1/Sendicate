package core.users;

import service.Variables;

import java.util.Date;

public class User {
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


    protected long id;
    protected String name;
    protected Date dateOfRegistration;
    protected String login;
    protected String password;

    public User(String name, String login,String password){
        this.id = Variables.AddNewUser();
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateOfRegistration = new Date();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
