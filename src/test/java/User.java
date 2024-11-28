import dao.CommonSelect;
import dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.MyBatisUtils;

import java.util.List;
import java.util.Map;

public class User {
    private SqlSession sqlSession;
    private UserDao commonSelect;

    @Before
    public void testBefore(){
        sqlSession = MyBatisUtils.getSqlSession();
        commonSelect = sqlSession.getMapper(UserDao.class);

    }

    @After
    public void testAfter(){
        sqlSession.commit(); // 事务提交
        sqlSession.close();  // 把SQLSesion关闭
    }

    @Test
    public void test(){
//        List<Map<String, Object>> sql = commonSelect.findSql("select * from type");
        entity.User user = new entity.User("15163983022", "usernickname", "uservia", "1", "usersignature", "usertimer");
        int i = commonSelect.updateByName(user);
        System.out.println(i);
    }
}
