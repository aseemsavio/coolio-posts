package com.savio.coolio.posts.service;

import com.savio.coolio.posts.entities.PostEntity;
import com.savio.coolio.posts.repository.PostRepository;
import com.savio.coolio.posts.templates.PostCreationRequest;
import com.savio.coolio.posts.templates.PostCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class has service methods for the Post Controller
 * @author Aseem Savio
 */

@Service
public class PostService {

    @Value("${coolioposts.coolio-service-url}")
    private String coolioServiceURL;

    @Autowired
    PostRepository postRepository;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public ResponseEntity<PostCreationResponse> createPost(PostCreationRequest postCreationRequest){
        if(!sanityCheck(postCreationRequest)){
            PostCreationResponse postCreationResponse = new PostCreationResponse();
            postCreationResponse.setPostTitle("NA");
            postCreationResponse.setResponseText("Sanity Check failed! Some crucial fields are missing.");
            postCreationResponse.setStatusCode("-1");
            return ResponseEntity.badRequest().header("status", "Sanity Check failed. Some crucial fields are missing").body(postCreationResponse);
        } else {
            PostEntity postEntity = new PostEntity();
            postEntity.setPostID(postCreationRequest.getPostID());
            postEntity.setCreatedBy(postCreationRequest.getCreatedBy());
            postEntity.setCreationTimestamp(postCreationRequest.getCreationTimestamp());
            postEntity.setImageURL(postCreationRequest.getImageURL());
            postEntity.setTitle(postCreationRequest.getTitle());
            postEntity.setTextContent(postCreationRequest.getTextContent());

            PostEntity createdPostEntity = new PostEntity();
            if(isUserFound(postCreationRequest.getUserName(), postCreationRequest.getPassWord())){
                createdPostEntity = postRepository.save(postEntity);
            } else {
                PostCreationResponse nullPostCreationResponse = new PostCreationResponse();
                nullPostCreationResponse.setPostTitle("NA");
                nullPostCreationResponse.setResponseText("Couldn't create Post - User Not Found");
                nullPostCreationResponse.setStatusCode("-1");
                return ResponseEntity.badRequest().header("status", "User not found in Resource Server").body(nullPostCreationResponse);
            }

            String titlePost = createdPostEntity.getTitle();

            if(postEntity.getTitle().equals(titlePost)){
                PostCreationResponse postCreationResponse = new PostCreationResponse();
                postCreationResponse.setPostTitle(titlePost);
                postCreationResponse.setResponseText("New post created - " + titlePost);
                postCreationResponse.setStatusCode("0");

                return ResponseEntity.ok().header("status", "success").body(postCreationResponse);
            }
        }
        PostCreationResponse nullPostCreationResponse = new PostCreationResponse();
        nullPostCreationResponse.setPostTitle("NA");
        nullPostCreationResponse.setResponseText("Something went wrong!");
        nullPostCreationResponse.setStatusCode("-1");
        return ResponseEntity.badRequest().header("status", "Something went wrong.").body(nullPostCreationResponse);
    }

    private boolean isUserFound(String userName, String passWord) {
        try{
            RestTemplate restTemplate = restTemplateBuilder.basicAuthorization(userName,passWord).build();
            String URI = coolioServiceURL + "/protected/isUserFound";
            String response = restTemplate.getForObject(URI, String.class);
            if(response.equals("1")){
                System.out.println("RESPONSE " + response);
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean sanityCheck(PostCreationRequest postCreationRequest) {
        if(postCreationRequest.getCreatedBy() != null && postCreationRequest.getTitle() != null)
            return true;
        return false;
    }

}
