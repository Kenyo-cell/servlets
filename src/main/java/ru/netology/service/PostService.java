package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) throws NotFoundException {
    try {
      return repository.getById(id).orElseThrow(NotFoundException::new);
    } catch (NotFoundException e) {
      throw new NotFoundException("Post not found with id " + id);
    }
  }

  public Post save(Post post) throws NotFoundException {
    try {
      return repository.save(post);
    } catch (NotFoundException e) {
      throw new NotFoundException("Post not found with id " + post.getId());
    }
  }

  public boolean removeById(long id) throws NotFoundException {
    try {
      return repository.removeById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Post not found with id " + id);
    }
  }
}

