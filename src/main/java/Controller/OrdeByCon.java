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

@WebServlet("/getOrderByCon")
public class OrdeByCon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String order_id = req.getParameter("order_id");

        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> all = commonSelect.findSqlByCon2("select * from ordert where username = #{con1} and order_id = #{con2}",username,order_id);

        if(all.size() != 0){
            ResultEntity<List<Map<String, Object>>> listResultEntity = new ResultEntity<>("200", "查询成功，返回所有数据", all);
            JsonUtil.writeJson(resp,listResultEntity);
        } else {
            ResultEntity<Object> error = new ResultEntity<>("400", "查询失败,程序出现错误");
            JsonUtil.writeJson(resp,error);
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
