package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int insert(User user);
    int update(@Param("id")String id,@Param("token") String token);
    int updateByName(User user);
}
