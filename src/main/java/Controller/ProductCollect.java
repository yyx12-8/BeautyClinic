package Controller;

import dao.CommonSelect;
import dao.ProductMan;
import dao.UserDao;
import entity.Noticeman;
import entity.Productman;
import entity.ResultEntity;
import entity.User;
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
import java.util.UUID;

@WebServlet("/productChange")
public class ProductCollect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        //id
        String pro_id = req.getParameter("pro_id");
        //状态改变 0取消，1点赞
        //状态未变化 2
        String operate_collect = req.getParameter("collect");

        ProductMan productMan;
        CommonSelect commonSelect;
        UserDao userDao;

        SqlSession sqlSession = Deal.before();
        productMan = sqlSession.getMapper(ProductMan.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        //判断diary里是否有这个用户，没有创建，有额继续进行
        List<Map<String, Object>> sql = commonSelect.findSqlByCon2("select * from procollect where username=#{con1} and pro_id =#{con2}", username, pro_id);
//        System.out.println(sql);
        if(sql.size()==0){

//            System.out.println("username"+username);
//            System.out.println("proid"+pro_id);

            Productman proMan = new Productman(username,pro_id,"https://", "某某某", "嘿嘿嘿", "0");
            int add = productMan.add(proMan);
//            System.out.println("add:--->" + add);

            sqlSession.commit();
            ResultEntity<Object> success = new ResultEntity<>("200", "新增、数据修改成功");
            JsonUtil.writeJson(resp,success);
        }

        int update=0;
        if(!operate_collect.equals("2")){
            update = productMan.update(operate_collect, username, pro_id);
            sqlSession.commit();
        }
//        System.out.println("更新数据："+ update);
        if(update==1){
            ResultEntity<Object> success = new ResultEntity<>("200", "状态改变成功");
            JsonUtil.writeJson(resp,success);
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
