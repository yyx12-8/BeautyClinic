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

@WebServlet("/getProductById")
public class GetProductByCon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String proid =req.getParameter("proid");


        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        List<Map<String, Object>> sql = commonSelect.findSqlByCon("select * from product where proid = #{con}",proid);

        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon2("select iscollect from procollect where username=#{con1} and pro_id = #{con2}", username, proid);

        if(sql.size()==0){
            System.out.println("proid---"+proid+"--username ---"+ username);
        } else {
            if (sqlByCon2.size() != 0) {
                sql.get(0).put("iscollect", sqlByCon2.get(0).get("iscollect"));
            } else {
                sql.get(0).put("iscollect", 0);
            }
        }
        ResultEntity<List<Map<String, Object>>> success = new ResultEntity<>("200", "查询成功，返回商品类型", sql);

        JsonUtil.writeJson(resp,success);

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
