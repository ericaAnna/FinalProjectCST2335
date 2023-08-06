package com.example.finalprojectcst2335;
public class Article {
    private long id;
    private String headline;
    private String url;

    public Article(long id, String headline, String url) {
        this.id = id;
        this.headline = headline;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getUrl() {
        return url;
    }
}
