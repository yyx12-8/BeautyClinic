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

@WebServlet("/personLike")
public class PersonLIKE extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String type = req.getParameter("type");

        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        if(type.equals("日记")){

//            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select a.* from diary as a,comment as b where a.diary_id = b.diary_id and b.username = #{con} and b.isup = 1", username);
            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from diary where diary_id in (select a.diary_id from diary as a,comment as b where a.diary_id = b.diary_id and b.username = #{con} and b.isup = 1)", username);
            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "返回所查询的数据", sqlByCon);
            JsonUtil.writeJson(resp,success);

        }else if(type.equals("通知")){
//            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select a.* from noticelist as a, noticeman as b where a.message_id = b.no_id and b.username = #{con} and b.isup =1", username);
            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from noticelist where message_id in (select a.message_id from noticelist as a, noticeman as b where a.message_id = b.no_id and b.username = #{con} and b.isup =1)", username);

            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "返回所查询的数据", sqlByCon);

            JsonUtil.writeJson(resp,success);
        } else{
            ResultEntity<Object> error = new ResultEntity<>("400", "类型输入错误");
            JsonUtil.writeJson(resp,error);
        }

        sqlSession.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
