package com.savio.coolio.posts.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * This class reads and writes to the COOLIO POSTS database.
 *
 * @author Aseem Savio
 */

@Entity
@DynamicUpdate
@Table(name = "post")
public class PostEntity {

    @Id
    @Column(name = "postid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postID;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String textContent;

    @Column(name = "image")
    private String imageURL;

    @Column(name = "creationts")
    private Date creationTimestamp;

    @Column(name = "createdby")
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
        return "PostEntity{" +
                "postID=" + postID +
                ", title='" + title + '\'' +
                ", textContent='" + textContent + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
