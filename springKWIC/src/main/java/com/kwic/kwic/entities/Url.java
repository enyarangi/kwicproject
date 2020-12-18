package com.kwic.kwic.entities;

import javax.persistence.*;

@Entity
@Table(name="url")
public class Url {
    String link;
    String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int url_id;

    public String getLink() {
        return link;
    }

    public void setLink(String url) {
        this.link = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public Url() {
    }
    public Url(String description, String url){
        this.link = url;
        this.description = description;
    }
}
