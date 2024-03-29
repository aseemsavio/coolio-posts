package com.savio.coolio.posts.pojo;

import java.util.Date;

/**
 * This class states what a coolio post will contain.
 *
 * @author Aseem Savio
 */
public class Post {

    private Integer postID;
    private String title;
    private String textContent;
    private String imageURL;
    private Date creationTimestamp;
    private String createdBy;

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", title='" + title + '\'' +
                ", textContent='" + textContent + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
