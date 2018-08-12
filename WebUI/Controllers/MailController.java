package Controllers;

import Common.MailHelper;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailController {
    /**
     * 发送邮件(方式1)
     */
    public static void main(String[] args) throws EmailException {

        try {
            String host = "hwhzsmtp.qiye.163.com";
            String username = "ebu-app@jinher.com";
            String password = "Jinher58858686";
            String subject = "错误信息";
            String contents = "请处理错误信息";
            String fromEmailAddress = "ebu-app@jinher.com";
            String toEmailAddress = "1099280025@qq.com";
            MailHelper.sendSimpleEmail(host, username, password, subject, contents, toEmailAddress, fromEmailAddress);
        } catch (Exception ex) {
            System.out.printf("%s", ex);
        }

    }


    /**
     * 发送邮件(方式二)
     */
    @Test
    public void SendEmial() {
        try {
            String host = "hwhzsmtp.qiye.163.com";
            String username = "ebu-app@jinher.com";
            String password = "Jinher58858686";
            String subject = "错误信息";
            String contents = "请处理错误信息";
            String fromEmailAddress = "ebu-app@jinher.com";
            String toEmailAddress = "1099280025@qq.com";
            MailHelper.sendjavaEmail(host, username, password, subject, contents, toEmailAddress, fromEmailAddress);
        } catch (Exception ex) {
            System.out.printf("%s", ex);
        }

    }


}
