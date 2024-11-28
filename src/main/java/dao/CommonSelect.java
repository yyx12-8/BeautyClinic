package dao;

import entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonSelect {
    List<Map<String,Object>> findSql(@Param("sql") String sql);

    List<Map<String,Object>> findSqlByCon(@Param("sql") String sql, @Param("con") String condition);

    List<Map<String,Object>> findSqlByCon2(@Param("sql") String sql, @Param("con1") String condition1, @Param("con2") String condition2);
}
