package summer.book_shop.service;

import summer.book_shop.domain.User;
import summer.book_shop.repository.UserRepository;

// 메인 기능
// 1. 회원 관리, 2. 상품 관리 3. 고객센터 4. 주문
// 1.1 회원 정보 수정, 회원 삭제, 회원 가입, 회원 등급 적용
// 2.1 상품 등록, 상품 수정, 상품 삭제, 등급별 할인율 적용?, 결제 방식에 따른 할인율 적용
// 3.1 1대1 문의, 후기, 별점, 추천 등
// 4.1 주문, 주문 수정, 주문 취소

//쿠폰 , 비밀번호찾기, 이벤트...?

// 예외처리, 테스트 코드 작성

public class UserService {

    private UserRepository userRepository;

    public void signUp(User user) throws Exception {
        if (userInfoNotValidate(user)) {
            throw new Exception("사용자 입력 정보가 올바르지 않습니다.");
        }

        userRepository.save(user);
    }

    public void signOut(String userId, String password, String cPassword) throws Exception {
        if (!userRepository.existByUserId(userId)) {
            throw new Exception("사용자를 찾을 수 없습니다.");
        }
        if (!password.equals(cPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(userId);
    }

    public void updateUserInfo(String userId, String password) throws Exception { // 유저 정보 수정 시 인증 코드와 일치하면 수정되도록 변경 예정
        if (!userRepository.existByUserId(userId)) {
            throw new Exception("사용자를 찾을 수 없습니다.");
        }

        userRepository.updatePassword(userId, password);
    }

    public User getUserInfo(String userId) {
        return userRepository.findByUserId(userId);
    }

    private boolean userInfoNotValidate(User user) {
        if (user.getUserId().length() < 4 || user.getUserId().length() > 11) return false;
        if (user.getPassword().length() < 4 || user.getPassword().length() > 11) return false;
        if (user.getNickname().startsWith(" ") || user.getNickname().length() < 4) return false;
        return true;
    }
}
