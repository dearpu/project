package FirstTry;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import javax.mail.internet.InternetAddress;


public class GetMail163 {
	private static BufferedReader stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
		private static PrintWriter stdOut = new  PrintWriter(System.out, true);
		private static PrintWriter stdErr = new  PrintWriter(System.err, true);
	public void run() throws Exception {
		// TODO Auto-generated method stub
		Properties  props = new Properties();

		props.put("mail.store.protocol", "imap");    //指定邮件接收协议
		props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//指定支持SMTP协议的Transport具体类，允许由第三方提供
		props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");    //指定支持IMAP协议的Store具体类，允许由第三方提供
		props.put("mail.smtp.host",  "pop.163.com");//指定采用SMTP协议的邮件发送服务器的IP地址或主机名

		Session session =Session .getInstance(props);// 设置环境信息 
		Store store = session.getStore("pop3");//指定接收邮件协议

		//内容输入
		stdErr.println("请输入用户名");
		stdErr.flush();
		String user = null;
		user = stdIn.readLine();
		stdErr.println("请输入密码");
		stdErr.flush();
		String password = null;
		password = stdIn.readLine();

		
		try{store.connect("pop.163.com",110,user, password);}catch(Exception e) {System.out.println("账号或密码输入不正确");}
		//            网易邮箱的服务器地址   账号  密码

		//获得名为默认"inbox"的邮件夹
		Folder folder=store.getFolder("inbox");

		//打开邮件夹
		folder.open(Folder.READ_ONLY);//它是一个邮件文件夹类。READ_ONLY表示只读

		//获得邮件夹中的邮件数目
		System.out.println("你一共有:"+folder.getMessageCount()+" 封邮件");
		//获得邮件夹中的未读邮件数目

		System.out.println("你一共有:"+folder.getUnreadMessageCount()+" 未读邮件");
		System.out.println("你一共删除了 "+folder.getDeletedMessageCount()+" 封邮件");

		for(int i=1;i<=folder.getMessageCount();i++){

			Message msg=folder.getMessage(i);
			System.out.println("========================================");
			//获得邮件的发送者、主题和正文
			if(msg.getFrom()[0].toString().contains("<")){
				System.out.println("邮件来自:"+msg.getFrom()[0].toString().substring(msg.getFrom()[0].toString().lastIndexOf("<")+1, msg.getFrom()[0].toString().lastIndexOf(">")));
			}else{
				System.out.println("邮件来自:"+msg.getFrom()[0]);
			}
			System.out.println("邮件主题:" + msg.getSubject());
			System.out.println("发送日期:" + msg.getSentDate());
			String type=msg.getContentType().toString().substring(0, msg.getContentType().toString().indexOf(";"));
			System.out.println("邮件类型:"+type);
			System.out.println("邮件内容:"+msg.getContentType().toString());
			if(type.equals("text/html")){
			}
			InternetAddress[] address = (InternetAddress[]) msg.getFrom();
			String from = address[0].getAddress();//这个是发邮件人的地址
			if (from == null) {
				from = "";
			}
			String personal = address[0].getPersonal();//这个是发邮件的人的姓名
			if (personal == null) {
				personal = "";
			}
			String fromaddr = personal + "<" + from + ">";
			System.out.println("邮件作者："+fromaddr);
			System.out.println("========================================\r\n");
		}

	}

}
