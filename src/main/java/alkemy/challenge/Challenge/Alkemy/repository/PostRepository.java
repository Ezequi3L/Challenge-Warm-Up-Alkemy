package alkemy.challenge.Challenge.Alkemy.repository;

import alkemy.challenge.Challenge.Alkemy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Optional<Post> findById(Long id);

    public List<Post> findByTitle(String title);

    public List<Post> findByCategory(String category);

//    @Query("Select p, c.category_name FROM posts p INNER JOIN categories c ON p.category_id WHERE p.tittle = ?1 AND c.category_name = ?1")
//    public List<Post> buscarByTitleAndCategory(String title, String category);
}
