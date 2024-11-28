package dao;

import entity.Productman;
import org.apache.ibatis.annotations.Param;

public interface ProductMan {
    int update(@Param("iscollect") String iscollect, @Param("username") String username, @Param("pro_id") String pro_id);
    int add(Productman man);
}
