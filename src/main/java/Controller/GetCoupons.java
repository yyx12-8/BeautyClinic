package Controller;

import dao.CommonSelect;
import dao.CouponsDao;
import entity.Coupons;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/mycoupons")
public class
GetCoupons extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String token =  req.getParameter("token");

        CommonSelect commonSelect;
        CouponsDao couponsDao;

        SqlSession sqlSession = Deal.before();
        commonSelect = sqlSession.getMapper(CommonSelect.class);
        couponsDao = sqlSession.getMapper(CouponsDao.class);

        String token1 = GetToken.getToken(commonSelect, username);

        if(token1 == null){
            ResultEntity<Object> error = new ResultEntity<>("600", "服务器的token未请求到，请联系后端");

            System.out.println("mycoupons的token1");

            JsonUtil.writeJson(resp,error);
        }

        if(!token1.equals(token)){
            ResultEntity<Object> error = new ResultEntity<>("400", "token过期，请登陆后尝试");
            JsonUtil.writeJson(resp,error);
            return;
        }


        Coupons coupons1 = new Coupons("360-40", "￥40", "满360减40", "服务类商品均可用", "未使用", "2021-6-7 过期");
        Coupons coupons2 = new Coupons("500-50", "￥50", "满500减50", "服务类商品均可用", "未使用", "2021-6-7 过期");
        Coupons coupons3 = new Coupons("1000-100", "￥100", "满1000减100", "服务类商品均可用", "未使用", "2021-6-7 过期");
        Coupons coupons4 = new Coupons("2000-300", "￥300", "满2000减300", "服务类商品均可用", "未使用", "2021-6-7 过期");
        List<Coupons> list = new ArrayList<>();

        list.add(coupons1);
        list.add(coupons2);
        list.add(coupons3);
        list.add(coupons4);

        List<Map<String, Object>> sqlByCon = commonSelect.findSqlByCon("select id from user where username = #{con}", username);

        String id = sqlByCon.get(0).get("id").toString();

        int i = couponsDao.addCoupons(list, username, id);
        sqlSession.commit();
        if(i != 0){
            ResultEntity<List<CouponsDao>> listResultEntity = new ResultEntity<>("200", "领取成功");
            JsonUtil.writeJson(resp,listResultEntity);
        } else {
            ResultEntity<Object> error = new ResultEntity<>("400", "领取失败");
            JsonUtil.writeJson(resp,error);
        }

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
