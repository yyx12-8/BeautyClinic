package dao;

import org.apache.ibatis.annotations.Param;

public interface Diary {
    int upddateUp(@Param("diary_id") String id,@Param("diary_num_like") String up);
    int upddateCollect(@Param("diary_id") String id,@Param("diary_num_collect") String collect);

    int updatenum(@Param("diary_num_comment") String diary_num_comment,@Param("diary_id") String id);
}
