package summer.book_shop.repository;

import summer.book_shop.domain.Grade;
import summer.book_shop.domain.User;

import java.sql.*;

public class UserRepository {
    private Connection conn; // 데이터베이스 연결, 쿼리 전송(insert, delete, update)
    private PreparedStatement pstmt; //
    private ResultSet rs;

    public UserRepository() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String hostName = "localhost";
        String databaseName = "book_shop";
        String utf8Connection = "?useUnicode=true&characterEncoding=utf8";

        String url = "jdbc:mysql://" + hostName + ":3306/" + databaseName + utf8Connection;
        String userName = "user_root";
        String password = "1234";

        try {
//            Class.forName(driver); // forName 을 통해 JVM 에 메모리를 올린다 (JDBC 4.0 이후로는 사용하지 않아도 자동 초기화)
            conn = DriverManager.getConnection(url, userName, password); // 이후 등록된 정보를 통해 데이터베이스 연결
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(User user) {
        String sql = "insert into user values(?, ?, ?, ?, ?);";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhoneNum());
            pstmt.setString(4, user.getNickname());
            pstmt.setDate(5, (Date) user.getBirthDate());
            pstmt.setString(6, user.getGrade().name()); //name메소드로 string으로 변환



            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String userId) {
        String sql = "delete from user where user_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String userId, String nPassword) {
        String sql = "update user set password=? where user_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, nPassword);
            pstmt.setString(2, userId);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existByUserId(String userId) {
        String sql = "select * from user where user_id=?"; // 쿼리 수정 예정

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);
            return pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByUserId(String userId) {
        String sql = "select * from user where user_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = new User();

            while(rs.next()) {
                user.setUserId(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setPhoneNum(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setBirthDate(rs.getDate(5));
                user.setGrade(Grade.valueOf(rs.getString(6)));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countByUser() {
        String sql = "select count(*) from user;";

        try {
            pstmt = conn.prepareStatement(sql);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
