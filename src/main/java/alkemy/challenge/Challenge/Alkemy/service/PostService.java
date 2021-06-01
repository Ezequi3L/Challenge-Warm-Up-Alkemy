package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Post;
import alkemy.challenge.Challenge.Alkemy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return (List<Post>) postRepository.findAll();
    }

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> findByTitle(String title){
        return postRepository.findByTitle(title);
    }

    public List<Post> findByCategory(String category){
        return postRepository.findByCategory(category);
    }

//    public List<Post> findByTitleAndCategory(String title, String category){
//        return postRepository.buscarByTitleAndCategory(title,category);
//    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }

    public void save(Post post){
        postRepository.save(post);
    }

}
