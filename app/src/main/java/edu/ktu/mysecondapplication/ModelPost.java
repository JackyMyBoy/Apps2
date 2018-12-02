package edu.ktu.mysecondapplication;

public class ModelPost {
    int id;
    int userId;
    String title;
    String bodyText;

    public ModelPost(){

    }
    public ModelPost(int id, int userId, String title, String bodyText){
        this.id = id;
        this.userId = userId;
        this.title=title;
        this.bodyText = bodyText;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
    public String getTitle(){
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }
}
