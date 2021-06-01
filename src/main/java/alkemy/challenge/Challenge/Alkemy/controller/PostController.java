package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Post;
import alkemy.challenge.Challenge.Alkemy.service.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.findAll();
    }

    @PostMapping
    public void newPost(@RequestBody Post post) {
        postService.save(post);
    }

    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) throws NotFoundException {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty()) {
            throw new NotFoundException("post not found");
        }
        return post;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updatePostById(@PathVariable("id") Long id, @RequestParam Post post) {
        Optional<Post> postAux = postService.findById(id);
        if (postAux.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        try {
            postAux.get().setTitle(post.getTitle());
            postAux.get().setImage(post.getImage());
            postAux.get().setCategory(post.getCategory());
            postAux.get().setUser(post.getUser());
            postAux.get().setIsDeleted(post.getIsDeleted());
            postService.save(postAux.get());
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity softDeletePostById(@PathVariable Long id){
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else try{ post.get().setIsDeleted(true);} catch (Exception e){ return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);}
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

//    @GetMapping
//    public List<Post> searchByTitle(@RequestParam(required = false) String title) {
//        return postService.findByTitle(title);
//    }
//
//    @GetMapping
//    public List<Post> searchByCategory(@RequestParam(required = false) String category) {
//        return postService.findByCategory(category);
//    }

//    @GetMapping
//    public List<Post> searchByTitleAndCategory(@RequestParam(required = false) String title, @RequestParam(required = false) String category){
//        return postService.findByTitleAndCategory(title, category);
//    }
}
