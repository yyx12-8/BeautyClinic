package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import dao.CommonSelect;
import dao.Notice;
import dao.NoticeMan;
import entity.Noticeman;
import entity.ResultEntity;

import org.apache.ibatis.session.SqlSession;
import utils.Deal;
import utils.GetRequestJsonUtils;
import utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/addNoticeComment")
public class InsertNoticeComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String a = GetRequestJsonUtils.getRequestJsonString(req);
        String b =  a.replaceAll("\"","\\\"");

        JsonParser parse = new JsonParser();
        Noticeman man = new Gson().fromJson(parse.parse(b).getAsJsonObject().get("discussObj"), Noticeman.class);

        String no_id = man.getNo_id();
        String username = man.getUsername();

        man.setIsup("0");
        man.setIscollect("0");

        NoticeMan noticeMan;
        CommonSelect commonSelect;
        Notice notice;

        SqlSession sqlSession = Deal.before();

        noticeMan = sqlSession.getMapper(NoticeMan.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);
        notice = sqlSession.getMapper(Notice.class);


        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon2("select * from noticeman where no_id = #{con1} and username= #{con2} and notice_via='小菜鸡'", no_id, username);

        int add = 0;
        int j = 0;
        if(sqlByCon2.size()!=0){

            add = noticeMan.updateperson(man);
            sqlSession.commit();

        }else{

            add = noticeMan.add(man);
            sqlSession.commit();
        }

//        System.out.println("add 的值："+add);

        List<Map<String, Object>> count = commonSelect.findSqlByCon("select count(1) from noticeman where no_id = #{con} and notice_via <> '小菜鸡'", no_id);

//        System.out.println(count.get(0).get("count(1)"));

        j = notice.updatenum(count.get(0).get("count(1)") + "", no_id);
        sqlSession.commit();

//        System.out.println("J的值 "+ j);

        if(add==1 && j==1){
            ResultEntity<Object> success = new ResultEntity<>("200", "添加成功,更新成功");
            JsonUtil.writeJson(resp,success);
        }

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
