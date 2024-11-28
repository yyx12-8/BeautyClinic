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

@WebServlet("/encyclopediaList")
public class AllEncyclopedia extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sql = commonSelect.findSql("select * from encyclopedialist");

        if(sql.size() != 0){
            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回所有数据", sql);
            JsonUtil.writeJson(resp,success);
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
