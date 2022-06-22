package FirstTry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail163 {
	private static BufferedReader stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
		private static PrintWriter stdOut = new  PrintWriter(System.out, true);
		private static PrintWriter stdErr = new  PrintWriter(System.err, true);
		public void run() throws Exception{
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");		
		Session session = Session.getInstance(props);
		session.setDebug(true);//提供信息
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		//信件
		Message msg = new MimeMessage(session);
		
		//内容输入
		stdErr.println("请输入发件人用户名");
		stdErr.flush();
		String user = null;
		user = stdIn.readLine();
		
		stdErr.println("请输入发件人密码");
		stdErr.flush();
		String password = null;
		password = stdIn.readLine();
		
		stdErr.println("请输入收件人邮箱");
		stdErr.flush();
		String toAdd = null;
		toAdd = stdIn.readLine();
		
		stdErr.println("请输入信件主题");
		stdErr.flush();
		String subject = null;
		subject = stdIn.readLine();
		
		stdErr.println("请输入信件正文");
		stdErr.flush();
		String text = null;
		text = stdIn.readLine();
		
		//发信人		
		Address fromAddress = new InternetAddress(user+"@163.com");
		msg.setFrom(fromAddress);
		
		//收件人
		Address toAddress = new InternetAddress(toAdd);
		msg.setRecipient(MimeMessage.RecipientType.TO, toAddress);
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送）
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
		
		//信件内容
		msg.setSubject(subject);//主题
		msg.setText(text);//正文

		//传输
		Transport transport = session.getTransport();
		try{transport.connect(user,password);}catch(Exception e) {
			stdErr.println("账户或密码输入错误");
		}//(“发件人账号”，“密码”)
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		
		
	}



}
