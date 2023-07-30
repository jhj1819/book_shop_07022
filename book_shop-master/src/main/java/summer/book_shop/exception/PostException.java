package summer.book_shop.exception;

public class PostException extends BaseException {

    private final BaseExceptionType exceptionType;

    public PostException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }

}
