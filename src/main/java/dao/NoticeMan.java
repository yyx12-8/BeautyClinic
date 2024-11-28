package dao;

import entity.Noticeman;
import org.apache.ibatis.annotations.Param;

public interface NoticeMan {
    int update(@Param("isup") String isip, @Param("iscollect") String iscollect, @Param("username") String username, @Param("no_id") String no_id);
    int updatecol(@Param("iscollect") String iscollect,@Param("username") String username,@Param("no_id") String no_id);
    int updateup(@Param("isup") String isip,@Param("username") String username,@Param("no_id") String no_id);

    int add(Noticeman man);
    int updateperson(Noticeman man);
}
