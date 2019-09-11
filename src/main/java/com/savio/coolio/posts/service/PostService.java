package com.savio.coolio.posts.service;

import com.savio.coolio.posts.entities.PostEntity;
import com.savio.coolio.posts.repository.PostRepository;
import com.savio.coolio.posts.templates.EmailPayload;
import com.savio.coolio.posts.templates.PostCreationRequest;
import com.savio.coolio.posts.templates.PostCreationResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class has service methods for the Post Controller
 *
 * @author Aseem Savio
 */

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Value("${coolioposts.coolio-service-url}")
    private String coolioServiceURL;

    private final RabbitTemplate rabbitTemplate;

    @Value("${coolioposts.rabbitmq.exchangename}")
    private String EXCHANGE_NAME;

    @Value("${coolioposts.rabbitmq.queuename}")
    private String QUEUE_NAME;

    @Value("${coolioposts.rabbitmq.routingkey}")
    private String ROUTING_KEY;

    public PostService(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseEntity<PostCreationResponse> createPost(PostCreationRequest postCreationRequest) {
        if (!sanityCheck(postCreationRequest))
            return getResponseEntity("NA", "Sanity Check failed! Some crucial fields are missing.", "-1", ResponseEntity.badRequest(), "Sanity Check failed. Some crucial fields are missing");
        else {
            PostEntity postEntity = new PostEntity();
            postEntity.setPostID(postCreationRequest.getPostID());
            postEntity.setCreatedBy(postCreationRequest.getCreatedBy());
            postEntity.setCreationTimestamp(postCreationRequest.getCreationTimestamp());
            postEntity.setImageURL(postCreationRequest.getImageURL());
            postEntity.setTitle(postCreationRequest.getTitle());
            postEntity.setTextContent(postCreationRequest.getTextContent());

            PostEntity createdPostEntity = new PostEntity();
            if (isUserFound(postCreationRequest.getUserName(), postCreationRequest.getPassWord()))
                createdPostEntity = postRepository.save(postEntity);
            else
                return getResponseEntity("NA", "Couldn't create Post - User Not Found", "-1", ResponseEntity.badRequest(), "User not found in Resource Server");

            String titlePost = createdPostEntity.getTitle();
            if (postEntity.getTitle().equals(titlePost))
                return getResponseEntity(titlePost, "New post created - " + titlePost, "0", ResponseEntity.ok(), "success");
        }
        return getResponseEntity("NA", "Something went wrong!", "-1", ResponseEntity.badRequest(), "Something went wrong.");
    }

    private ResponseEntity<PostCreationResponse> getResponseEntity(String postTitle, String responseText, String statusCode, ResponseEntity.BodyBuilder bodyBuilder, String headerStatus) {
        PostCreationResponse postCreationResponse = new PostCreationResponse();
        postCreationResponse.setPostTitle(postTitle);
        postCreationResponse.setResponseText(responseText);
        postCreationResponse.setStatusCode(statusCode);
        return bodyBuilder.header("status", headerStatus).body(postCreationResponse);
    }

    private boolean isUserFound(String userName, String passWord) {
        try {
            RestTemplate restTemplate = restTemplateBuilder.basicAuthorization(userName, passWord).build();
            String URI = coolioServiceURL + "/protected/isUserFound";
            String response = restTemplate.getForObject(URI, String.class);
            if (response.equals("1"))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean sanityCheck(PostCreationRequest postCreationRequest) {
        if (postCreationRequest.getCreatedBy() != null && postCreationRequest.getTitle() != null)
            return true;
        return false;
    }

    public String dropMessageToRabbitMQ(){
        EmailPayload emailPayload = new EmailPayload("Aseem", "aseem@gmail.com", "aseemsavio3@gmail.com", "SEND_POST_CREATION_EMAIL", "no content", "Post Creation Successful");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, emailPayload);
        return "Message Dropped";
    }

}
