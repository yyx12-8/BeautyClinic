package Controller;

import dao.OrderDao;
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

@WebServlet("/ModifyOrder")
public class OrderModify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String order_id = req.getParameter("order_id");

        OrderDao orderDao;

        SqlSession sqlSession = Deal.before();
        orderDao =  sqlSession.getMapper(OrderDao.class);

        int i = orderDao.update(order_id, "1");
        sqlSession.commit();

        sqlSession.close();
        if(i != 0){
            ResultEntity<Object> success = new ResultEntity<>("200", "修改订单状态成功");
            JsonUtil.writeJson(resp,success);
        }else{
            ResultEntity<Object> error = new ResultEntity<>("400", "修改失败" );
            JsonUtil.writeJson(resp,error);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
