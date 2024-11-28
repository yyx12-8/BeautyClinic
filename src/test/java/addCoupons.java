
import dao.CouponsDao;
import entity.Coupons;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.MyBatisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class addCoupons {
    private SqlSession sqlSession;
    private CouponsDao commonSelect;

    @Before
    public void testBefore(){
        sqlSession = MyBatisUtils.getSqlSession();
        commonSelect = sqlSession.getMapper(CouponsDao.class);

    }

    @After
    public void testAfter(){
        sqlSession.commit(); // 事务提交
        sqlSession.close();  // 把SQLSesion关闭
    }

    @Test
    public void test(){
//        List<Map<String, Object>> sql = commonSelect.findSql("select * from type");
        Coupons coupons1 = new Coupons("360-40", "￥40", "满360减40", "服务类商品均可用", "未使用", "2021-6-7 过期");
        Coupons coupons2 = new Coupons("500-50", "￥50", "满500减50", "服务类商品均可用", "未使用", "2021-6-7 过期");
        Coupons coupons3 = new Coupons("1000-100", "￥100", "满1000减100", "服务类商品均可用", "未使用", "2021-6-7 过期");
        Coupons coupons4 = new Coupons("2000-300", "￥300", "满2000减300", "服务类商品均可用", "未使用", "2021-6-7 过期");
        List<Coupons> list = new ArrayList<>();

        list.add(coupons1);
        list.add(coupons2);
        list.add(coupons3);
        list.add(coupons4);

        commonSelect.addCoupons(list, "15163983022", "32");

    }
}
