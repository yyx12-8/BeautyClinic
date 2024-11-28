package Controller;

import dao.*;

import entity.Diaryman;
import entity.Noticeman;
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

@WebServlet("/noticeChange")
public class NoticeCollect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        //id
        String no_id = req.getParameter("message_id");
        //状态改变 0取消，1点赞
        //状态未变化 2
        String operate_up = req.getParameter("like");

        String operate_collect = req.getParameter("collect");

        NoticeMan noticeMan;
        CommonSelect commonSelect;
        Notice notice;

        SqlSession sqlSession = Deal.before();

        noticeMan = sqlSession.getMapper(NoticeMan.class);
        commonSelect = sqlSession.getMapper(CommonSelect.class);
        notice = sqlSession.getMapper(Notice.class);

        //判断diary里是否有这个用户，没有创建，有额继续进行
        List<Map<String, Object>> sql = commonSelect.findSqlByCon2("select * from noticeman where username=#{con1} and no_id =#{con2}", username, no_id);
//        System.out.println(sql);
        if(sql.size()==0){
            Noticeman noticeman = new Noticeman(username, no_id, "https://", "小菜鸡", "嘿嘿嘿", "0", "0");
            int add = noticeMan.add(noticeman);
//            System.out.println("add:--->" + add);
            ResultEntity<Object> success = new ResultEntity<>("200", "新增、数据修改成功");
            JsonUtil.writeJson(resp,success);
        }
        int update=0;
        if(operate_up.equals("2")&&operate_collect.equals("2")){

        }else if(operate_collect.equals("2")){
            update = noticeMan.updateup(operate_up, username, no_id);
            sqlSession.commit();
        }else if(operate_up.equals("2")){
            update = noticeMan.updatecol(operate_collect, username, no_id);
            sqlSession.commit();
        }else{
            update = noticeMan.update(operate_up, operate_collect, username, no_id);
            sqlSession.commit();
        }
//        System.out.println("更新数据："+ update);

        List<Map<String, Object>> sqlByCon2 = commonSelect.findSqlByCon("select message_num_like,message_num_collect from noticelist where message_id = #{con}", no_id);

        int message_num_like = Integer.parseInt(sqlByCon2.get(0).get("message_num_like") + "");
        int message_num_collect = Integer.parseInt(sqlByCon2.get(0).get("message_num_collect") + "");
//        System.out.println(message_num_like+"-------"+message_num_collect);
        int i = 0;
        int j = 0;
        if(operate_up.equals("0")){
            i = notice.upddateUp(no_id, (message_num_like - 1) + "");
//            System.out.println("取消点赞"+i);
            sqlSession.commit();
        }else if(operate_up.equals("1")){
            i = notice.upddateUp(no_id, (message_num_like + 1) + "");
//            System.out.println("点赞"+i);
            sqlSession.commit();
        }
        if(operate_collect.equals("0")){
            j = notice.upddateCollect(no_id, (message_num_collect - 1) + "");
//            System.out.println("取消收藏"+i);
            sqlSession.commit();
        }else if(operate_collect.equals("1")){
            j = notice.upddateCollect(no_id, (message_num_collect + 1) + "");
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
}
