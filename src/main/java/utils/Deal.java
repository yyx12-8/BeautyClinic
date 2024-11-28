package utils;

import org.apache.ibatis.session.SqlSession;

public class Deal{
    private static SqlSession sqlSession;

    public static SqlSession before(){
        sqlSession = MyBatisUtils.getSqlSession();
        return sqlSession;
    }
}
