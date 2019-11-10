package core.users;

import core.reporting.Report;
import core.reporting.Reportable;
import service.Variables;

import java.util.Date;
import java.util.LinkedList;

public class User implements Reportable {
    protected long id;
    protected String token;

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

    protected boolean isBanned;
    protected String name;
    protected Date dateOfRegistration;
    protected String login;
    protected String password;

    public User(String name, String login, String password) {
        this.id = Variables.AddNewUser();
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateOfRegistration = new Date();
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

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public LinkedList<Report> GetReports() {
        return Reports;
    }

    @Override
    public boolean AddReport(Report report) {
        try {
            Reports.add(report);
            return true;
        } catch (Exception E) {
            return false;
        }
    }

    @Override
    public boolean DeleteReport(Report report) {
        try {
            return Reports.remove(report);
        } catch (Exception E) {
            return false;
        }

    }
}
