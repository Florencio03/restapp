package com.RESTapp.restapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//Java POJO class
@Entity
@Table(name="session")
public class Session {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="id")
    private int id;

    private LocalDateTime createdAt;

    private String moodSummary;

    private String dominantEmotion;

    private String markingStatus;

    public Session() {}

    public Session(LocalDateTime createdAt, String moodSummary,
                   String dominantEmotion, String markingStatus) {
        this.createdAt = createdAt;
        this.moodSummary = moodSummary;
        this.dominantEmotion = dominantEmotion;
        this.markingStatus = markingStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMoodSummary() {
        return moodSummary;
    }

    public void setMoodSummary(String moodSummary) {
        this.moodSummary = moodSummary;
    }

    public String getDominantEmotion() {
        return dominantEmotion;
    }

    public void setDominantEmotion(String dominantEmotion) {
        this.dominantEmotion = dominantEmotion;
    }

    public String getMarkingStatus() {
        return markingStatus;
    }

    public void setMarkingStatus(String markingStatus) {
        this.markingStatus = markingStatus;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", moodSummary='" + moodSummary + '\'' +
                ", dominantEmotion='" + dominantEmotion + '\'' +
                ", markingStatus='" + markingStatus + '\'' +
                '}';
    }
}
