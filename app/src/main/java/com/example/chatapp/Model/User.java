package com.example.chatapp.Model;

public class User {

    public String Id;
    public String username;
    public String ImageUrl;

    public void User(String Id,String username,String ImageUrl){
        this.Id = Id;
        this.username = username;
        this.ImageUrl = ImageUrl;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }
}
