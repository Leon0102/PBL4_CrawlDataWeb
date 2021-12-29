package BLL;

import java.util.Properties;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import Model.Exchange;
import javafx.collections.ObservableList;

public class SendMail_BLL {

   public static void sendmail(String s, String kq) {
	   
       try {
           Email email = new SimpleEmail();

           // Cấu hình thông tin Email Server
           email.setHostName("smtp.googlemail.com");
           email.setSmtpPort(465);
           email.setAuthenticator(new DefaultAuthenticator(SMailConstant.MY_EMAIL,
                   SMailConstant.MY_PASSWORD));

           // Với gmail cái này là bắt buộc.
           email.setSSLOnConnect(true);

           // Người gửi
           email.setFrom(SMailConstant.MY_EMAIL);

           // Tiêu đề
           email.setSubject("Notification!!!");

           // Nội dung email

           email.setMsg(kq);        	   

           // Người nhận
           email.addTo(s);
           email.send();
           System.out.println("Sent!!");
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}