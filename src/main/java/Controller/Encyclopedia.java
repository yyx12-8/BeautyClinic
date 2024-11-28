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

@WebServlet("/encyclopediaListProDetail")
public class Encyclopedia extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String encyclopedia_type = req.getParameter("encyclopedia_type");

        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from encyclopedialist where encyclopedia_type = #{con}", encyclopedia_type);
        sqlSession.commit();
        if(sqlByCon.size() != 0){

            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回该数据的所有信息", sqlByCon);
            JsonUtil.writeJson(resp,success);
        }else {
            ResultEntity<Object> error = new ResultEntity<>("400", "查询错误");
            JsonUtil.writeJson(resp,error);
        }

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
