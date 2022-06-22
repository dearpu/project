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

		props.put("mail.store.protocol", "imap");    //ָ���ʼ�����Э��
		props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");//ָ��֧��SMTPЭ���Transport�����࣬�����ɵ������ṩ
		props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");    //ָ��֧��IMAPЭ���Store�����࣬�����ɵ������ṩ
		props.put("mail.smtp.host",  "pop.163.com");//ָ������SMTPЭ����ʼ����ͷ�������IP��ַ��������

		Session session =Session .getInstance(props);// ���û�����Ϣ 
		Store store = session.getStore("pop3");//ָ�������ʼ�Э��

		//��������
		stdErr.println("�������û���");
		stdErr.flush();
		String user = null;
		user = stdIn.readLine();
		stdErr.println("����������");
		stdErr.flush();
		String password = null;
		password = stdIn.readLine();

		
		try{store.connect("pop.163.com",110,user, password);}catch(Exception e) {System.out.println("�˺Ż��������벻��ȷ");}
		//            ��������ķ�������ַ   �˺�  ����

		//�����ΪĬ��"inbox"���ʼ���
		Folder folder=store.getFolder("inbox");

		//���ʼ���
		folder.open(Folder.READ_ONLY);//����һ���ʼ��ļ����ࡣREAD_ONLY��ʾֻ��

		//����ʼ����е��ʼ���Ŀ
		System.out.println("��һ����:"+folder.getMessageCount()+" ���ʼ�");
		//����ʼ����е�δ���ʼ���Ŀ

		System.out.println("��һ����:"+folder.getUnreadMessageCount()+" δ���ʼ�");
		System.out.println("��һ��ɾ���� "+folder.getDeletedMessageCount()+" ���ʼ�");

		for(int i=1;i<=folder.getMessageCount();i++){

			Message msg=folder.getMessage(i);
			System.out.println("========================================");
			//����ʼ��ķ����ߡ����������
			if(msg.getFrom()[0].toString().contains("<")){
				System.out.println("�ʼ�����:"+msg.getFrom()[0].toString().substring(msg.getFrom()[0].toString().lastIndexOf("<")+1, msg.getFrom()[0].toString().lastIndexOf(">")));
			}else{
				System.out.println("�ʼ�����:"+msg.getFrom()[0]);
			}
			System.out.println("�ʼ�����:" + msg.getSubject());
			System.out.println("��������:" + msg.getSentDate());
			String type=msg.getContentType().toString().substring(0, msg.getContentType().toString().indexOf(";"));
			System.out.println("�ʼ�����:"+type);
			System.out.println("�ʼ�����:"+msg.getContentType().toString());
			if(type.equals("text/html")){
			}
			InternetAddress[] address = (InternetAddress[]) msg.getFrom();
			String from = address[0].getAddress();//����Ƿ��ʼ��˵ĵ�ַ
			if (from == null) {
				from = "";
			}
			String personal = address[0].getPersonal();//����Ƿ��ʼ����˵�����
			if (personal == null) {
				personal = "";
			}
			String fromaddr = personal + "<" + from + ">";
			System.out.println("�ʼ����ߣ�"+fromaddr);
			System.out.println("========================================\r\n");
		}

	}

}
