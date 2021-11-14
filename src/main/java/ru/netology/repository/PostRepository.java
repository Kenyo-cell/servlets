package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
  private final List<Post> posts = new ArrayList<>();
  private long idCounter = 0;

  public List<Post> all() {
    return posts;
  }

  public Optional<Post> getById(long id) {
    return Optional.of(posts.get((int) id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(idCounter++);
      posts.add(post);
    } else {
      try {
        int index = posts.indexOf(posts.get((int) post.getId()));

        if (index == -1) throw new NotFoundException();

        posts.set(index, post);
      } catch (IndexOutOfBoundsException | NotFoundException e) {
        throw new NotFoundException("There is no post with id: " + post.getId());
      }
    }

    return post;
  }

  public void removeById(long id) {

    System.out.println(posts.remove(posts.stream()
            .filter(post -> post.getId() == id)
            .findFirst()
            .orElseThrow(NotFoundException::new)
    ));
  }
}
