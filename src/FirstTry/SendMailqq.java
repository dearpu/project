package FirstTry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailqq {
	private static BufferedReader stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
		private static PrintWriter stdOut = new  PrintWriter(System.out, true);
		private static PrintWriter stdErr = new  PrintWriter(System.err, true);
		public void run() throws Exception{
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", 465);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props);
		session.setDebug(true);//�ṩ��Ϣ
		System.setProperty("java.net.preferIPv4Stack", "true");



		//�ż�
		Message msg = new MimeMessage(session);
		
		//��������
		stdErr.println("�����뷢�����û���");
		stdErr.flush();
		String user = null;
		user = stdIn.readLine();
		stdErr.println("�����뷢������Ȩ��");
		stdErr.flush();
		String password = null;
		password = stdIn.readLine();
		stdErr.println("�������ռ�������");
		stdErr.flush();
		String toAdd = null;
		toAdd = stdIn.readLine();
		stdErr.println("�������ż�����");
		stdErr.flush();
		String subject = null;
		subject = stdIn.readLine();
		stdErr.println("�������ż�����");
		stdErr.flush();
		String text = null;
		text = stdIn.readLine();
		
		//������		
		Address fromAddress = new InternetAddress(user+"@qq.com");
		msg.setFrom(fromAddress);
		
		//�ռ���
		Address toAddress = new InternetAddress(toAdd);
		msg.setRecipient(MimeMessage.RecipientType.TO, toAddress);
        /**
         * �����ռ��˵�ַ���������Ӷ���ռ��ˡ����͡����ͣ�
         * MimeMessage.RecipientType.TO:����
         * MimeMessage.RecipientType.CC������
         * MimeMessage.RecipientType.BCC������
         */
		
		//�ż�����
		msg.setSubject(subject);//����
		msg.setText(text);//����

		//����
		Transport transport = session.getTransport();
		try{transport.connect("smtp.qq.com", 465,user,password);}catch(Exception e) {
			stdErr.println("�˻��������������");
		}//(���������˺š��������롱)
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		
		
	}



}