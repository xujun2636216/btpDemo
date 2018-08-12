package Common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**   
* @Title: MailHelper.java 
* @Package com.jarvis.base.util 
* @Description:mail工具类
* @author Jack  
* @date 2017年9月2日 下午3:39:46 
* @version V1.0   
*/ 
public class MailHelper
{



    /**
     * 简单的发邮件方式    邮件内容只有标题和邮件内容  支持单个个用户发送
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址
     * @param fromEmailAddress 发件人的邮件地址
     * @throws EmailException
     */
    public static boolean sendjavaEmail(String host, String username, String password, String subject, String contents,String toEmailAddress, String fromEmailAddress){
        try{
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmailAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
            message.setSubject(subject);
            message.setText(contents);
            Transport.send(message);
        }catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return  true;
    }



    /**
     * 简单的发邮件方式    邮件内容只有标题和邮件内容  支持单个个用户发送
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址
     * @param fromEmailAddress 发件人的邮件地址
     * @throws EmailException
     */
    public static boolean sendSimpleEmail(String host, String username, String password, String subject, String contents,String toEmailAddress, String fromEmailAddress){
        String smtpPort ="25";  //发送邮件的服务端口
        String isSSL = "false";//是否为SSL链接的邮箱
        String isTSL = "true";//是否为TSL加密方式的邮箱，true=是，false=否
        try{
            SimpleEmail email = new SimpleEmail();
            email.setCharset("UTF-8");
            email.setHostName(host);
            email.setAuthentication(username,password);
            email.addTo(toEmailAddress, toEmailAddress);
            email.setFrom(fromEmailAddress,fromEmailAddress);
            if("true".equals(isSSL)){
                email.setSslSmtpPort(smtpPort);
                email.setSSLOnConnect(true);
            }else{
                email.setSmtpPort(25);
                if("true".equals(isTSL)) {
                    email.setStartTLSEnabled(true);
                }
            }
            email.setSubject(subject);
            email.setMsg(contents);
            email.setSentDate(new Date());
            email.send();
        }catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }
       return  true;
    }


    /**
     * 简单的发邮件方式    邮件内容只有标题和邮件内容  支持多个用户批量发送
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址
     * @param fromEmailAddress 发件人的邮件地址
     * @throws EmailException
     */
    public static boolean sendSimpleEmail(String host, String username, String password, String subject, String contents, String [] toEmailAddress, String fromEmailAddress)
    {
        String smtpPort ="25";  //发送邮件的服务端口
        String isSSL = "false";//是否为SSL链接的邮箱
        String isTSL = "true";//是否为TSL加密方式的邮箱，true=是，false=否
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(host);
            email.setAuthentication(username, password);
            //发送给多个人
            for (int i = 0; i < toEmailAddress.length; i++)
            {
                email.addTo(toEmailAddress[i], toEmailAddress[i]);
            }
            email.setFrom(fromEmailAddress, fromEmailAddress);
            if("true".equals(isSSL)){
                email.setSslSmtpPort(smtpPort);
                email.setSSLOnConnect(true);
            }else{
                email.setSmtpPort(25);
                if("true".equals(isTSL)) {
                    email.setStartTLSEnabled(true);
                }
            }
            email.setSubject(subject);
            email.setMsg(contents);
            email.setSentDate(new Date());
            email.send();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return  false;
        }
        return  true;
    }

    /**
     * 发送带附件的邮件方式  邮件内容有标题和邮件内容和附件，附件可以是本地机器上的文本，也可以是web上的一个URL 文件，
     * 当为web上的一个URL文件时，此方法可以将WEB中的URL文件先下载到本地，再发送给收入用户
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址
     * @param fromEmailAddress 发件人的邮件地址
     * @param multiPaths       附件文件数组
     * @throws EmailException
     */

    public static void sendMultiPartEmail(String host, String username, String password, String subject,
                                          String contents, String toEmailAddress, String fromEmailAddress,
                                          String []multiPaths) throws MalformedURLException, EmailException
    {
        List<EmailAttachment> attachmentList = new ArrayList<EmailAttachment>();
        if (multiPaths != null)
        {
            for (int i = 0; i < multiPaths.length; i++)
            {
                EmailAttachment attachment = new EmailAttachment();
                if (multiPaths[i].indexOf("http") == -1)   //判断当前这个文件路径是否在本地  如果是：setPath  否则  setURL;
                {
                    attachment.setPath(multiPaths[i]);
                }
                else
                {
                    attachment.setURL(new URL(multiPaths[i]));
                }
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("");
                attachmentList.add(attachment);
            }
        }

        //发送邮件信息
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(host);
        email.setAuthentication(username, password);
        email.addTo(toEmailAddress);
        email.setFrom(fromEmailAddress, fromEmailAddress);
        email.setSubject(subject);
        email.setMsg(contents);   //注意这个不要使用setContent这个方法  setMsg不会出现乱码
        for (int i = 0; i < attachmentList.size(); i++)   //添加多个附件
        {
            email.attach((EmailAttachment) attachmentList.get(i));
        }
        email.send();
    }

    /**
     * 发送带附件的邮件方式  邮件内容有标题和邮件内容和附件，附件可以是本地机器上的文本，也可以是web上的一个URL 文件，
     * 当为web上的一个URL文件时，此方法可以将WEB中的URL文件先下载到本地，再发送给收入用户
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址数组
     * @param fromEmailAddress 发件人的邮件地址
     * @param multiPaths       附件文件数组
     * @throws EmailException
     */

    public static void sendMultiPartEmail(String host, String username, String password, String subject,
                                          String contents, String[] toEmailAddress, String fromEmailAddress,
                                          String []multiPaths) throws MalformedURLException, EmailException
    {
        List<EmailAttachment> attachmentList = new ArrayList<EmailAttachment>();
        if (multiPaths != null)
        {
            for (int i = 0; i < multiPaths.length; i++)
            {
                EmailAttachment attachment = new EmailAttachment();
                if (multiPaths[i].indexOf("http") == -1)   //判断当前这个文件路径是否在本地  如果是：setPath  否则  setURL;
                {
                    attachment.setPath(multiPaths[i]);
                }
                else
                {
                    attachment.setURL(new URL(multiPaths[i]));
                }
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("");
                attachmentList.add(attachment);
            }
        }

        //发送邮件信息
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(host);
        email.setAuthentication(username, password);
        //发送给多个人
        for (int i = 0; i < toEmailAddress.length; i++)
        {
            email.addTo(toEmailAddress[i], toEmailAddress[i]);
        }
        email.setFrom(fromEmailAddress, fromEmailAddress);
        email.setSubject(subject);
        email.setMsg(contents);   //注意这个不要使用setContent这个方法  setMsg不会出现乱码
        for (int i = 0; i < attachmentList.size(); i++)   //添加多个附件
        {
            email.attach((EmailAttachment) attachmentList.get(i));
        }
        email.send();
    }


    /**
     * 发送Html格式的邮件
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址
     * @param fromEmailAddress 发件人邮件地址
     * @throws EmailException
     */
    public static void sendHtmlEmail(String host, String username, String password, String subject, String contents, String toEmailAddress, String fromEmailAddress) throws EmailException
    {
        HtmlEmail email = new HtmlEmail();
        //email.setDebug(true);
        email.setHostName(host);
        email.setAuthentication(username, password);
        email.addTo(toEmailAddress);
        email.setFrom(fromEmailAddress, fromEmailAddress);
        email.setSubject(subject);
        email.setHtmlMsg(CharHelper.GBKto8859(contents));
        email.send();
    }

    /**
     * 发送Html格式的邮件
     *
     * @param host             邮件服务器地址
     * @param username         连接邮件服务器的用户名称
     * @param password         连接邮件服务器的用户密码
     * @param subject          邮件的主题
     * @param contents         邮件的内容
     * @param toEmailAddress   收件人的邮件地址数组
     * @param fromEmailAddress 发件人邮件地址
     * @throws EmailException
     */
    public static void sendHtmlEmail(String host, String username, String password, String subject, String contents, String[] toEmailAddress, String fromEmailAddress) throws EmailException
    {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setAuthentication(username, password);
        //发送给多个人
        for (int i = 0; i < toEmailAddress.length; i++)
        {
            email.addTo(toEmailAddress[i], toEmailAddress[i]);
        }
        email.setFrom(fromEmailAddress, fromEmailAddress);
        email.setSubject(subject);
        email.setHtmlMsg(CharHelper.GBKto8859(contents));
        email.send();
    }
}
