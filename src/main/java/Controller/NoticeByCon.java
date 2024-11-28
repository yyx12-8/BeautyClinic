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

@WebServlet("/getNoticeById")
public class NoticeByCon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message_id = req.getParameter("message_id");
        String username = req.getParameter("username");

        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sql = commonSelect.findSqlByCon("select * from noticelist where message_id = #{con}", message_id);

        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon2("select isup,iscollect from noticeman where no_id = #{con1} and username=#{con2}", message_id, username);

        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from noticeman where no_id = #{con} and notice_via <> '小菜鸡'", message_id);


        if(sqlByCon2.size() != 0){
            sql.get(0).put("iscollect",sqlByCon2.get(0).get("iscollect"));
            sql.get(0).put("isup",sqlByCon2.get(0).get("isup"));
        } else {
            sql.get(0).put("iscollect",0);
            sql.get(0).put("isup",0);
        }
        sql.get(0).put("NoticeMan",sqlByCon);

        ResultEntity<List<Map<String, Object>>> listResultEntity = new ResultEntity<>("200", "查询成功，返回所有数据", sql);
        JsonUtil.writeJson(resp,listResultEntity);
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
