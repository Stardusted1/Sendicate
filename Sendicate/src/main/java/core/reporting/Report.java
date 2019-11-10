package core.reporting;

import core.users.User;

import java.util.Date;

public class Report {
    protected User creator;
    protected String body;
    protected String topic;
    protected Date dateOfCreation;

    public Report(User creator, String body, String topic) {
        this.creator = creator;
        this.body = body;
        this.topic = topic;
        this.dateOfCreation = new Date();
    }

    public User getCreator() {
        return creator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }




}
