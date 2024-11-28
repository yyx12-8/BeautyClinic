package Controller;

import dao.CommonSelect;
import dao.UserDao;
import entity.ResultEntity;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import utils.CheckCode;
import utils.Deal;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    public String Lid;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String authCode = req.getParameter("authCode");

        try{
            String code  = CheckCode.getCode(username);
            if(!code.equals(authCode)){
                ResultEntity<Object> err = new  ResultEntity("400","验证码错误");
                JsonUtil.writeJson(resp,err);
             return;
            }
        }catch(NullPointerException e){
            ResultEntity<Object> err = new  ResultEntity("400","请先获取验证码后尝试登录");
            JsonUtil.writeJson(resp,err);
        }


        CommonSelect commonSelect;
        UserDao userDao;

        SqlSession sqlSession = Deal.before();

        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from user where username = #{con}", username);
        if(sqlByCon.size() == 0){
            UUID uuid = UUID.randomUUID();

            User user = new User(username,"用户昵称","https://m.juooo.com/static/img/logo-user.8413cbf.png","0","填写简历，让大家更好地认识你"
                            ,"",uuid+"");
            userDao = sqlSession.getMapper(UserDao.class);
            int insert = userDao.insert(user);
            sqlSession.commit();
            if(insert == 1){
                ResultEntity<Object> success = new ResultEntity("200","登录成功",user);
                JsonUtil.writeJson(resp,success);
            } else {
                ResultEntity<Object> err = new  ResultEntity("400","添加失败",user);
                JsonUtil.writeJson(resp,err);
            }
        } else {
            Lid = (sqlByCon.get(0).get("id") + "");
            UUID uuid = UUID.randomUUID();
            userDao = sqlSession.getMapper(UserDao.class);
            int i = userDao.update(Lid,uuid+"");
            sqlSession.commit();
            if(i == 1){
                sqlByCon.get(0).put("token",uuid);
            }
        }

        ResultEntity<List<Map<String, Object>>> success = new  ResultEntity("200","登录成功",sqlByCon);
        sqlSession.close();

        JsonUtil.writeJson(resp,success);


    }
}
