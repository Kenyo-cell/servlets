package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryImpl implements PostRepository {
  private final int NOT_FOUND = -1;
  private final int NEW_POST = 0;

  private final List<Post> postsList = new CopyOnWriteArrayList<>();
  private final AtomicLong idCounter = new AtomicLong(0);

  public List<Post> all() {
    return postsList;
  }

  public Optional<Post> getById(long id) {
    return Optional.of(postsList.get((int) id));
  }

  public Post save(Post post) throws NotFoundException {
    if (post.getId() == NEW_POST) {
      post.setId(idCounter.incrementAndGet());
      postsList.add(post);
    } else {
      int index = postsList.indexOf(
              postsList.stream()
                      .filter(p -> p.getId() == post.getId())
                      .findFirst()
                      .orElseThrow(NotFoundException::new)
      );

      if (index == NOT_FOUND) throw new NotFoundException();
      postsList.set(index, post);
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
