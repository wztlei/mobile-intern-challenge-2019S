package com.wztlei.mobiledeveloperchallenge;

public class CustomCollection {
    private int id;
    //private String handle;
    private String title;
    private String updated_at;
    private String body_html;
    private String published_at;
    //private String template_suffix;
    //private String published_scope;
    //private String admin_graphql_api_id;

    public CustomCollection() {}

    public CustomCollection(int id, String title, String updated_at, String body_html, String published_at) {
        this.id = id;
        this.title = title;
        this.updated_at = updated_at;
        this.body_html = body_html;
        this.published_at = published_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getBodyHtml() {
        return body_html;
    }

    public void setBodyHtml(String body_html) {
        this.body_html = body_html;
    }

    public String getPublishedAt() {
        return published_at;
    }

    public void setPublishedAt(String published_at) {
        this.published_at = published_at;
    }
}
