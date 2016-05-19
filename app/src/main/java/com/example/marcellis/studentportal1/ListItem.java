package com.example.marcellis.studentportal1;

/**
 * Created by Marcellis on 10-12-2015.
 */
public class ListItem {
    private String url;
    private String title;

    public ListItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}