package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final int NOT_FOUND = -1;
  private final int NEW_POST = 0;

  private final List<Post> postsList = new CopyOnWriteArrayList<>();
  private final Map<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(0);

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.of(posts.get(id));
  }

  public Post save(Post post) throws NotFoundException {
    if (post.getId() == NEW_POST) {
      post.setId(idCounter.incrementAndGet());
      posts.put(post.getId(), post);
    } else {
      if (posts.get(post.getId()) == null)
        throw new NotFoundException("Post with id %d not found".formatted(post.getId()));

      posts.put(post.getId(), post);
    }
    return post;
  }

  public boolean removeById(long id) throws NotFoundException {
    return postsList.remove(postsList.stream()
            .filter(post -> post.getId() == id)
            .findFirst()
            .orElseThrow(NotFoundException::new)
    );
  }
}
