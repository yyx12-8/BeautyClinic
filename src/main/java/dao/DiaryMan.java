package dao;

import entity.Diaryman;
import org.apache.ibatis.annotations.Param;

public interface DiaryMan {
    int update(@Param("isup") String isip,@Param("iscollect") String iscollect,@Param("username") String username,@Param("diary_id") String diary_id);
    int updatecol(@Param("iscollect") String iscollect,@Param("username") String username,@Param("diary_id") String diary_id);
    int updateup(@Param("isup") String isip,@Param("username") String username,@Param("diary_id") String diary_id);

    int add(Diaryman man);
    int updateperson(Diaryman man);
}
