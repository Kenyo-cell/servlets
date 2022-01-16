package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public interface PostRepository {
  public List<Post> all();
  public Optional<Post> getById(long id);
  public Post save(Post post);
  public boolean removeById(long id);
}
