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

@WebServlet("/getDiaryById")
public class GetDiaryByCon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String diary_id =req.getParameter("diary_id");

        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sql = commonSelect.findSqlByCon("select * from diary where diary_id = #{con}",diary_id);

        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon2("select isup,iscollect from comment where username=#{con1} and diary_id = #{con2}", username, diary_id);

        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select * from comment where diary_id = #{con} and diary_comment_via <> '某某某'", diary_id);

        if (sqlByCon2.size()!=0){
            sql.get(0).put("iscollect",sqlByCon2.get(0).get("iscollect"));
            sql.get(0).put("isup",sqlByCon2.get(0).get("isup"));
        }else{
            sql.get(0).put("iscollect",0);
            sql.get(0).put("isup",0);
        }
        sql.get(0).put("comment",sqlByCon);

        ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回商品类型", sql);

        JsonUtil.writeJson(resp,success);

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
