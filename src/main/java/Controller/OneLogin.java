package Controller;

import dao.CommonSelect;
import entity.ResultEntity;
import org.apache.ibatis.session.SqlSession;
import utils.Deal;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/oneLogin")
public class OneLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getParameter("token");

//        System.out.println("一键登陆前端:"+token);

        String username = req.getParameter("username");

        CommonSelect commonSelect;
        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from user where username = #{con}", username);

        if(sqlByCon.size() == 0){
            ResultEntity<Object> objectResultEntity = new ResultEntity<>("400", "该用户未注册，请注册后登陆");
            JsonUtil.writeJson(resp,objectResultEntity);
        } else {
            String token1 = sqlByCon.get(0).get("token").toString();

//            System.out.println("一键登陆后端:"+token1);

            if (token.equals(token1)){
                ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "一键登陆成功", sqlByCon);
                JsonUtil.writeJson(resp,success);
            } else{
                ResultEntity<List<Map<String, Object>>> out = new ResultEntity<>("300", "token过期了，请换种方式登陆");
                JsonUtil.writeJson(resp,out);
            }
        }


        sqlSession.close();
    }
}
