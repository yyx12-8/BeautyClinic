package Controller;

import dao.CommonSelect;
import entity.ResultEntity;
import org.apache.ibatis.session.SqlSession;
import utils.Deal;
import utils.GetToken;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;



@WebServlet("/queryMsg")
public class UserSelect extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String token  = req.getParameter("token");

        CommonSelect commonSelect;
        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

//        System.out.println("userselect"+username);
        String token1 = GetToken.getToken(commonSelect, username);
//        System.out.println(token1);

        if(token1 == null){
            ResultEntity<Object> error = new ResultEntity<>("600", "服务器的token未请求到，请联系后端");

            System.out.println("queryMsg的token1");

            JsonUtil.writeJson(resp,error);
        }

        if (!token.equals(token1)) {
            ResultEntity<Object> error = new ResultEntity<>("400", "token失效");
            JsonUtil.writeJson(resp,error);
            return;
        }


        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from user where username = #{con}", username);
        sqlSession.close();
        if (sqlByCon.size() == 0) {
            ResultEntity<Object> error = new ResultEntity<>("404", "未查到该用户");
            JsonUtil.writeJson(resp,error);
        }else{
            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回该用户的全部信息", sqlByCon);
            JsonUtil.writeJson(resp,success);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
