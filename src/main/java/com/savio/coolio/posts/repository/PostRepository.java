package com.savio.coolio.posts.repository;

import com.savio.coolio.posts.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
}
