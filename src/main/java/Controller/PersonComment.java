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

@WebServlet("/getCommentByTy")
public class PersonComment  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String type = req.getParameter("type");

        CommonSelect commonSelect;
        SqlSession sqlSession = Deal.before();

        commonSelect = sqlSession.getMapper(CommonSelect.class);

        if("通知".equals(type)){
            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select b.*,a.notice_content from noticeman as a,noticelist as b where a.no_id = b.message_id and a.username = #{con} and a.notice_via <> '小菜鸡'", username);
            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回通知的相关内容", sqlByCon);
            JsonUtil.writeJson(resp,success);
            sqlSession.close();
        }else if("日记".equals(type)) {
//            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select a.*,b.diary_comment_user,b.diary_comment_content,b.diary_comment_via from diary as a, comment as b where a.diary_id = b.diary_id and b.username = #{con}", username);
            List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select a.*,b.diary_comment_content from diary as a, comment as b where a.diary_id = b.diary_id and b.username = #{con} and b.diary_comment_via <> '某某某'", username);
            ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回日记的相关内容", sqlByCon);
            JsonUtil.writeJson(resp,success);
            sqlSession.close();
        }else {
            ResultEntity<Object> error = new ResultEntity<>("404", "未找到该类型");

        }
    }
}
