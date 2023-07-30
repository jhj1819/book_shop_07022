package summer.book_shop.domain;

import jdk.jfr.DataAmount;
import lombok.*;

import java.util.Date;




//@Builder
//@ToString
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class Post {
    private Long postId; // PK
    private String title;
    private String content;
    private String review;
    private int likeCount;
    private int views;
    private String bookId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    private Date updateDate;

    public Post(Long postId, String title, String content, String review, int views) {
        this.postId = postId;
        this.title = title;
        this.content =content;
        this.review = review;
        this.views = views;
    }
    public  Post(){

    }

}
