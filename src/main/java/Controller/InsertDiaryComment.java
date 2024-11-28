package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import dao.CommonSelect;
import dao.Diary;
import dao.DiaryMan;
import entity.Diaryman;
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

@WebServlet("/addDiaryComment")
public class InsertDiaryComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String a = GetRequestJsonUtils.getRequestJsonString(req);
        String b =  a.replaceAll("\"","\\\"");

        JsonParser parse = new JsonParser();
        Diaryman man = new Gson().fromJson(parse.parse(b).getAsJsonObject().get("discussObj"), Diaryman.class);

        String diary_id = man.getDiary_id();
        String username =man.getUsername();

        man.setIsup("0");
        man.setIscollect("0");

        DiaryMan diaryMan;
        CommonSelect commonSelect;
        Diary diary;

        SqlSession sqlSession = Deal.before();

        diaryMan = sqlSession.getMapper(DiaryMan.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);
        diary = sqlSession.getMapper(Diary.class);

        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon2("select * from comment where diary_id = #{con1} and username= #{con2} and diary_comment_via='某某某'", diary_id, username);

        int add = 0;
        int j = 0;

        if(sqlByCon2.size()!=0){

            add = diaryMan.updateperson(man);
            sqlSession.commit();

        }else{

            add = diaryMan.add(man);
            sqlSession.commit();
        }
        List<Map<String, Object>> count = commonSelect.findSqlByCon("select count(1) from comment where diary_id=#{con} and diary_comment_via <> '某某某'", diary_id);

        //System.out.println(count.get(0).get("count(1)"));

        j = diary.updatenum(count.get(0).get("count(1)") + "", diary_id);

        sqlSession.commit();

        if(add==1 && j==1){
            ResultEntity<Object> success = new ResultEntity<>("200", "添加成功,数据更新成功");
            JsonUtil.writeJson(resp,success);
        }

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
