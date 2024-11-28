package Controller;


import dao.CommonSelect;
import dao.CouponsDao;
import entity.ResultEntity;
import org.apache.ibatis.session.SqlSession;
import utils.Deal;
import utils.GetToken;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userCoupons")
public class AllCoupons extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String token =  req.getParameter("token");

        CouponsDao couponsDao;
        CommonSelect commonSelect;

        SqlSession sqlSession = Deal.before();
        couponsDao = sqlSession.getMapper(CouponsDao.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        System.out.println("allCoupons"+username);

        String token1 = GetToken.getToken(commonSelect, username);
        if(token1 == null){
            ResultEntity<Object> error = new ResultEntity<>("600", "服务器的token未请求到，请联系后端");

            System.out.println("userCoupons的token1");

            JsonUtil.writeJson(resp,error);
        }

        if(!token1.equals(token)){
            ResultEntity<Object> error = new ResultEntity<>("400", "token过期，请登陆后尝试");
            JsonUtil.writeJson(resp,error);
            return;
        }



        List<CouponsDao> all = couponsDao.getAllByCon(username);

        if(all.size() != 0){
            ResultEntity<List<CouponsDao>> listResultEntity = new ResultEntity<>("200", "查询成功，返回所有数据", all);
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
