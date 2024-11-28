import dao.CommonSelect;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.MyBatisUtils;

import java.util.List;
import java.util.Map;

public class CommonTest {
    private SqlSession sqlSession;
    private CommonSelect commonSelect;

    @Before
    public void testBefore(){
        sqlSession = MyBatisUtils.getSqlSession();
        commonSelect = sqlSession.getMapper(CommonSelect.class);

    }

    @After
    public void testAfter(){
        sqlSession.commit(); // 事务提交
        sqlSession.close();  // 把SQLSesion关闭
    }

    @Test
    public void test(){
//        List<Map<String, Object>> sql = commonSelect.findSql("select * from type");
        List<Map<String, Object>> sql = commonSelect.findSqlByCon("select * from type where id = #{con}","1");
        for (int i = 0; i < sql.size(); i++) {
            System.out.println(sql.get(i));
        }
    }
}
