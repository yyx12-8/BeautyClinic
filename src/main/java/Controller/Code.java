package Controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import utils.CheckCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getCheckCode")
public class Code extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("phoneNumber");
//
//
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FhgfjjNJhTF3rnLSvfR", "uzclYLWUwZxEmCcwqSZlMhXvvOoMoz");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "爱美医美");
        request.putQueryParameter("TemplateCode", "SMS_187240092");


//        request.putQueryParameter("TemplateParam", "{\"code\":\"2005\"}");

        String num = "";
        for (int i = 0; i < 6; i++) {
            num += (int)(Math.random() * 10);
        }
//
        CheckCode.setCode(phoneNumber,num);
//        CheckCode.setCode(phoneNumber,"111111");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+num+"\"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            PrintWriter writer = resp.getWriter();
            writer.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
