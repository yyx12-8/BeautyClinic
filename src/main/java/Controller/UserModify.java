package Controller;

import com.google.gson.*;
import dao.CommonSelect;
import dao.UserDao;
import entity.ResultEntity;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import utils.Deal;
import utils.GetRequestJsonUtils;
import utils.GetToken;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeMsg")
public class UserModify extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String a = GetRequestJsonUtils.getRequestJsonString(req);
        String b =  a.replaceAll("\"","\\\"");

        JsonParser parse = new JsonParser();
        User user = new Gson().fromJson(parse.parse(b).getAsJsonObject().get("userobj"), User.class);

        String token =  user.getToken();
        String username = user.getUsername();

        CommonSelect commonSelect;
        UserDao userDao;
        SqlSession sqlSession = Deal.before();

        userDao = sqlSession.getMapper(UserDao.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        String token1 = GetToken.getToken(commonSelect, username);
        

        if(token1 == null){
            ResultEntity<Object> error = new ResultEntity<>("600", "服务器的token未请求到，请联系后端");

            System.out.println("changeMsg的token1");

            JsonUtil.writeJson(resp,error);
        }

        if(!token.equals(token1)){
            ResultEntity<Object> error = new ResultEntity<>("400", "token过期了");
            JsonUtil.writeJson(resp,error);
            return;
        }

//        User user = new User("15163983022", "usernickname", "uservia", "1", "usersignature", "usertimer");
//        System.out.println(user);

        int i = userDao.updateByName(user);

        sqlSession.commit();
        sqlSession.close();
        if( i != 0){
            ResultEntity<Object> success = new ResultEntity<>("200", "修改成功");
            JsonUtil.writeJson(resp,success);
        }else {
            ResultEntity<Object> error = new ResultEntity<>("404", "修改失败");
            JsonUtil.writeJson(resp,error);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
