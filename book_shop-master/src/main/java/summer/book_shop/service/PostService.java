package summer.book_shop.service;

import org.springframework.stereotype.Service;
import summer.book_shop.domain.Post;
import summer.book_shop.exception.PostException;
import summer.book_shop.exception.PostExceptionType;
import summer.book_shop.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    public void createPost(Post post) {
        if (false) {
            throw new PostException(PostExceptionType.CANNOT_CREATE_POST);
        }
        postRepository.save(post);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public void removePost(Long postId) throws Exception {
        if (!postRepository.existByPostId(postId)) {
            throw new PostException(PostExceptionType.NOT_FOUND_POST);
        }
        postRepository.delete(postId);
    }

    public Post getPostInfo(Long postId) {
        Post post = postRepository.findByPostId(postId);
        if (!postRepository.existByPostId(postId)) {
            throw new PostException(PostExceptionType.NOT_FOUND_POST);
        }
        return post;
    }

    //최신순으로 정렬
    public List<Post> orderByUpdateDate() {
        return postRepository.findPostsOrderByLatest();
    }

    //좋아요순
    public List<Post> orderByLike() {
        return postRepository.findPostsOrderByLikes();
    }

    //조회수순
    public List<Post> orderByView() {
        return postRepository.findPostsOrderByViews();
    }

    public List<Post> allPost() throws PostException {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            throw new PostException(PostExceptionType.CANNOT_CREATE_POST);
        }
    }
}
