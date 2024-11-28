package dao;

import entity.Coupons;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponsDao {
    int addCoupons(@Param("list") List<Coupons> list, @Param("username") String username, @Param("id") String id);
    List<CouponsDao> getAllByCon(@Param("username") String username);
}
