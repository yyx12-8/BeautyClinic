package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ResultEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static  void writeJson(HttpServletResponse resp, ResultEntity data){

        try {
            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("text/html;charset=utf-8");
            mapper.writeValue(resp.getWriter(),data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

