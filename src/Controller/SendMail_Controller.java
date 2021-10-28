package Controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import Model.Hose;
import javafx.collections.ObservableList;

public class SendMail_Controller {

   public static void sendmail(String s, ObservableList<Hose> listM) {
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
           email.setSubject("Test Email");

           // Nội dung email
           String docs= "";
           for(Hose items : listM)
           {
        	   if( items.getTime() != "null")
        	   {
        		   docs	+=items.getTime() +"    Refer:    " + items.getRefer() +"    Ceiling: " + items.getCeiling() +"\n";        		   
        	   }
           }
           email.setMsg(docs);        	   

           // Người nhận
           email.addTo(s);
           email.send();
           System.out.println("Sent!!");
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}