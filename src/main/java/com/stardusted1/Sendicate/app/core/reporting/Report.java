package com.stardusted1.Sendicate.app.core.reporting;


import com.stardusted1.Sendicate.app.core.users.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long Id;
    protected long creatorId;
    protected long reposId;
    protected String body;
    protected String topic;
    protected Date dateOfCreation;

    public long getId() {
        return Id;
    }

    public void setReposId(long reposId) {
        this.reposId = reposId;
    }

    public long getReposId() {
        return reposId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public Report(long creator,  String body, String topic) {
        this.creatorId = creator;
        this.body = body;
        this.topic = topic;
        this.dateOfCreation = new Date();
    }

    public long getCreator() {
        return creatorId;
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
