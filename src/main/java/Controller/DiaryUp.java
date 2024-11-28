package Controller;

import dao.CommonSelect;
import dao.Diary;
import dao.DiaryMan;
import entity.Diaryman;
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
import java.util.List;
import java.util.Map;

@WebServlet("/diaryChange")
public class DiaryUp extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        //id
        String diary_id = req.getParameter("diary_id");
        //状态改变 0取消，1点赞
        //状态未变化 2
        String operate_up = req.getParameter("like");

        String operate_collect = req.getParameter("collect");

        DiaryMan diaryMan;
        CommonSelect commonSelect;
        Diary diary;

        SqlSession sqlSession = Deal.before();

        diaryMan = sqlSession.getMapper(DiaryMan.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);
        diary = sqlSession.getMapper(Diary.class);

        //判断diary里是否有这个用户，没有创建，有额继续进行
        List<Map<String, Object>> sql = commonSelect.findSqlByCon2("select * from comment where username=#{con1} and diary_id =#{con2}", username, diary_id);
//        System.out.println(sql);
        if(sql.size()==0){
            Diaryman man = new Diaryman("http://...", "某某某", "", diary_id, username, "0", "0");
            int add = diaryMan.add(man);
//            System.out.println("add:--->" + add);
            ResultEntity<Object> success = new ResultEntity<>("200", "新增、数据修改成功");
            JsonUtil.writeJson(resp,success);
        }

        int update=0;
        if(operate_up.equals("2")&&operate_collect.equals("2")){

        }else if(operate_collect.equals("2")){
            update = diaryMan.updateup(operate_up, username, diary_id);
            sqlSession.commit();
        }else if(operate_up.equals("2")){
            update = diaryMan.updatecol(operate_collect, username, diary_id);
            sqlSession.commit();
        }else{
            update = diaryMan.update(operate_up, operate_collect, username, diary_id);
            sqlSession.commit();
        }
//        System.out.println("更新数据："+ update);

        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon("select diary_num_like,diary_num_collect from diary where diary_id = #{con}", diary_id);

        int diary_num_like = Integer.parseInt(sqlByCon2.get(0).get("diary_num_like") + "");
        int diary_num_collect = Integer.parseInt(sqlByCon2.get(0).get("diary_num_collect") + "");
//        System.out.println(diary_num_like+"-------"+diary_num_collect);
        int i = 0;
        int j = 0;
        if(operate_up.equals("0")){
            i = diary.upddateUp(diary_id, (diary_num_like - 1) + "");
//            System.out.println("取消点赞"+i);
            sqlSession.commit();
        }else if(operate_up.equals("1")){
            i = diary.upddateUp(diary_id, (diary_num_like + 1) + "");
//            System.out.println("点赞"+i);
            sqlSession.commit();
        }
        if(operate_collect.equals("0")){
            j = diary.upddateCollect(diary_id, (diary_num_collect - 1) + "");
//            System.out.println("取消收藏"+i);
            sqlSession.commit();
        }else if(operate_collect.equals("1")){
            j = diary.upddateCollect(diary_id, (diary_num_collect + 1) + "");
//            System.out.println("收藏"+i);
            sqlSession.commit();
        }
        if(operate_up.equals("2") || operate_collect.equals("2")){
            if( i ==1 || j ==1){
                ResultEntity<Object> success = new ResultEntity<>("200", "状态改变成功");
                JsonUtil.writeJson(resp,success);
            }else{
                ResultEntity<Object> error = new ResultEntity<>("400", "更新失败");
                JsonUtil.writeJson(resp,error);
            }
        }else {
            if( i ==1 && j ==1){
                ResultEntity<Object> success = new ResultEntity<>("200", "状态改变成功");
                JsonUtil.writeJson(resp,success);
            }else{
                ResultEntity<Object> error = new ResultEntity<>("400", "更新失败");
                JsonUtil.writeJson(resp,error);
            }
        }

        sqlSession.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
