package dao;

import entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {
    int addOrder(Order order);
    int delOrder(@Param("id") String id, @Param("username") String username);
    int update(@Param("id") String id,@Param("num") String num);
}
