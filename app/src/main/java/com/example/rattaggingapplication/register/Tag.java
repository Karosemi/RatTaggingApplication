package com.example.rattaggingapplication.register;

public class Tag {
    private String filename;
    private String description;
    private int emotions;
    private int eventId;


    public String getFlename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getEmotions() {
        return emotions;
    }
    public void setEmotions(int emotions) {
        this.emotions= emotions;
    }
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
