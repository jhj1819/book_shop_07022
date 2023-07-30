package summer.book_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summer.book_shop.domain.Post;
import summer.book_shop.exception.PostException;
import summer.book_shop.service.PostService;

import java.util.List;

@RestController
//@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //모두 보기
    @GetMapping("/post")
    public ResponseEntity<List<Post>> findAll() {
        try {
            List<Post> posts = postService.allPost();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (PostException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //조회수순 정렬
    @GetMapping("/post/orderByViews")
    public ResponseEntity<List<Post>> orderByViews() {
        try {
            List<Post> posts = postService.orderByView();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (PostException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //해당 id 보기
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> findById(@PathVariable long id) {
        try {
            Post post = postService.getPostInfo(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //객체 생성
    @PostMapping("/post")
    public ResponseEntity<String> save(@RequestBody Post post) {
        try {
            postService.createPost(post);
            return new ResponseEntity<>("post created!", HttpStatus.OK);
        } catch (PostException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //예제 데이터 생성
    @PostMapping("/post/ex")
    public void saveEx() {
        postService.createPost(new Post(1L, "DataStructure", "stack", "good", 35));
        postService.createPost(new Post(2L, "JAVA", "JAVAcontent", "bad", 45));
        postService.createPost(new Post(3L, "C", "Ccontent", "bad", 15));
        System.out.println("findSave()");
    }

    //모든 게시물 삭제
    @DeleteMapping("/post/delete")
    public ResponseEntity<String> deleteAll() {
        try {
            postService.deleteAll();
            return new ResponseEntity<>("all posts deleted!", HttpStatus.OK);
        } catch (PostException e) {
            System.out.println(e.getExceptionType().getErrorMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
