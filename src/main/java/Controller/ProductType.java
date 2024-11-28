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

@WebServlet("/getProType")
public class ProductType extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();

        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sql = commonSelect.findSql("select * from type");
//        List<Map<String, Object>> sql = commonSelect.findSqlByCon("select * from type where id = #{con}","1");

        ResultEntity<List<Map<String,Object>>> success =new ResultEntity("200","查询成功",sql);

        sqlSession.commit();
        sqlSession.close();

        JsonUtil.writeJson(resp,success);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
