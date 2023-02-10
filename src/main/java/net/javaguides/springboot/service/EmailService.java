import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

   public void sendEmail(String to, String subject, String body) throws MessagingException {

      String from = "your-email-id@gmail.com";
      String password = "your-email-password";

      Properties prop = new Properties();
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.starttls.enable", "true");
      prop.put("mail.smtp.host", "smtp.gmail.com");
      prop.put("mail.smtp.port", "587");

      Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
         protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(from, password);
         }
      });

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(subject);
      message.setText(body);

      Transport.send(message);
   }
}
