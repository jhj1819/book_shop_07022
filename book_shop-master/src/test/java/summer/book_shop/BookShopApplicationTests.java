package summer.book_shop;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import summer.book_shop.domain.Post;
import summer.book_shop.repository.PostRepository;
import summer.book_shop.service.PostService;

@SpringBootTest
class BookShopApplicationTests {


    private PostService postService;
    private PostRepository postRepository;

    @Test
    public void testPost() {
        Post post = null;
        postService.createPost(post);

    }

}
