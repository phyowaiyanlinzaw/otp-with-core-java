import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {

    private static final String SMTP_SERVER = "smtp.gmail.com";//smtp server address
    private static final int SMTP_PORT = 587;//port number
    private static final String userName = "otp.fbt@gmail.com";//your gmail address
    private static final String password = "npulqhjohccxaizq";//your password
    public static void main(String[] args) throws Exception {

        String to = "yephoneaung33002@gmail.com";//receiver email address
        String subject = "OTP";//subject of the email

        String body = "Your OTP is : "+generateOtp();//body of the email
        
        sendEmail(to, subject, body);//sending email

    }

    public static String generateOtp(){
        Random random = new Random();//instance of random class
        int otp = random.nextInt(999999);//generate otp
        return String.format("%06d", otp);//return 6 digit otp
    }//end of generateOtp() method

    public static void sendEmail(   String to, String subject, String body){
        try{
            Properties properties = new Properties();//System.getProperties();
            properties.put("mail.smtp.auth", "true");//enable authentication
            properties.put("mail.smtp.starttls.enable","true");//enable STARTTLS
            properties.put("mail.smtp.host", SMTP_SERVER);//SMTP server address
            properties.put("mail.smtp.port", SMTP_PORT);//SMTP port number

            Session session = Session.getInstance(properties, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(userName, password);//email address and password
                }//end of getPasswordAuthentication() method
            }
            
            );//end of Authenticator() class

            Message message = new MimeMessage(session);//compose message
            message.setFrom(new InternetAddress(userName));//sender email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//receiver email address
            message.setSubject(subject);//subject of the email
            message.setText(body);//body of the email

            Transport.send(message);//send message
            System.out.println("Email sent successfully");//print success message
        }catch(Exception e){
            e.printStackTrace();//print error message
        }
    }
}
