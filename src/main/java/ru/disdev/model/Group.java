package ru.disdev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DisDev on 24.07.2016.
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String universityName;
    private String elderUserId;

    public Group() {}

    public Group(String name, String universityName, String elderUserId) {
        this.name = name;
        this.universityName = universityName;
        this.elderUserId = elderUserId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getElderUserId() {
        return elderUserId;
    }

    public void setElderUserId(String elderUserId) {
        this.elderUserId = elderUserId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
