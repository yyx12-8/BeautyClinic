package utils;

import java.util.HashMap;

public class CheckCode {
    private static HashMap<String, String> check;

    static {
        check = new HashMap<>();
    }

    public static void setCode(String username, String code){
        check.put(username,code);
    }
    public static  String getCode(String username){
        return  check.get(username);
    }
}
