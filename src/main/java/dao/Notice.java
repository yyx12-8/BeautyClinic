package dao;

import org.apache.ibatis.annotations.Param;

public interface Notice {
    int upddateUp(@Param("message_id") String id, @Param("message_num_like") String up);
    int upddateCollect(@Param("message_id") String id,@Param("message_num_collect") String collect);

    int updatenum(@Param("message_num_comment") String message_num_comment,@Param("message_id") String id);
}
