### Coolio Posts - System Design

[10-06-2019] Story: 1
Creating a post.

The user sends a POST request to the posts service with the username and other post related info.
To create a post, the posts service must send an async rest call to the Coolio service with the username. An end point must be created in the Coolio service which returns a positive or negative response for the availability of a particular user (The user that sent the request) in the Coolio-oauth2 service’s database.
If the response is affirmative, create a post with the username (mandatory) and other post details. 
The Likes column carries only the number of likes. The comments will contain a list of comment ids. A new table need to be created for comments with the comment  id as the primary key so that it could be used as a foreign key in the post-comment section. 

---
