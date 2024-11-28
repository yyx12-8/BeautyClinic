package utils;


import dao.CommonSelect;

import java.util.List;
import java.util.Map;

public class GetToken {

    public static String getToken(CommonSelect commonSelect, String username){
        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select token from user where username = #{con}", username);
        return sqlByCon.get(0).get("token").toString();
    }
}
