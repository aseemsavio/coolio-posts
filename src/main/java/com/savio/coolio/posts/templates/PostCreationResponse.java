package com.savio.coolio.posts.templates;

/**
 * This class is a template for the Post Creation Response.
 * @author Aseem Savio
 */
public class PostCreationResponse {

    private String statusCode;
    private String responseText;
    private String postTitle;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @Override
    public String toString() {
        return "PostCreationResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", responseText='" + responseText + '\'' +
                ", postTitle='" + postTitle + '\'' +
                '}';
    }
}
