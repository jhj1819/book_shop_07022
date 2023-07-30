package summer.book_shop.exception;

public enum PostExceptionType implements BaseExceptionType {

    NOT_FOUND_POST(601, "게시글을 찾을 수 없습니다."),
    ALREADY_EXIST_POST(602, "이미 존재하는 게시글입니다."),
    INVALID_POST_CONTENT(603, "게시글 내용이 유효하지 않습니다."),
    CANNOT_CREATE_POST(604, "게시글 생성 조건이 만족되지 않았습니다."),
    CANNOT_DELETE_POST(605, "게시글 삭제 실패하였습니다."),
    CANNOT_UPDATE_POST(606, "게시글 업데이트 실패하였습니다.");

    private final int errorCode;
    private final String errorMessage;

    PostExceptionType(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
