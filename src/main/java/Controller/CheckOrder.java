package Controller;

import dao.CommonSelect;
import dao.OrderDao;

import entity.Order;
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

@WebServlet("/submitOrder")
public class CheckOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String token = req.getParameter("token");
        String proid = req.getParameter("proid");
        String pro_num = req.getParameter("pro_num");
        String pro_price = req.getParameter("pro_price");
        String pro_coupons = req.getParameter("pro_coupons");
        String pro_remark = req.getParameter("pro_remark");
        String status = req.getParameter("status");

        OrderDao orderDao;
        CommonSelect commonSelect;
        SqlSession sqlSession = Deal.before();
        orderDao = sqlSession.getMapper(OrderDao.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);

        System.out.println("checkCode前端"+username);

        String token1 = GetToken.getToken(commonSelect, username);

        if(token1 == null){
            ResultEntity<Object> error = new ResultEntity<>("600", "服务器的token未请求到，请联系后端");

            System.out.println("submitOrder的token1");

            JsonUtil.writeJson(resp,error);
        }

        if(!token.equals(token1)){
            ResultEntity<Object> error = new ResultEntity<>("400", "token过期了");
            JsonUtil.writeJson(resp,error);
            return;
        }

        Order order = new Order(proid, pro_num, pro_price, pro_coupons, pro_remark, status, username);


        int i = orderDao.addOrder(order);
        sqlSession.commit();
        if( i != 0){
            ResultEntity<Object> success = new ResultEntity<>("200", "添加成功",order);
            JsonUtil.writeJson(resp,success);
        }else {
            ResultEntity<Object> error = new ResultEntity<>("404", "添加失败");
            JsonUtil.writeJson(resp,error);
        }

        sqlSession.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
