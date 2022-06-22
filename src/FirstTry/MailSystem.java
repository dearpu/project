package FirstTry;

import java.io.*;
import java.util.*;
import java.text.*;
public class MailSystem {
	private static BufferedReader stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
		private static PrintWriter stdOut = new  PrintWriter(System.out, true);
		private static PrintWriter stdErr = new  PrintWriter(System.err, true);
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MailSystem  application = new  MailSystem();
		application.run();
	}
	private int  getChoice() throws IOException  {

		int  input;

		do  {
			try  {
				stdErr.println();
				stdErr.print("[0]  Quit\n"
				             + "[1]  163发邮件 \n"
				             + "[2]  163收邮件\n"
				             + "[3]  qq发邮件\n"
				             + "[4]  qq收邮件\n"
				             + "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && 4 >= input)  {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException  nfe)  {
				stdErr.println(nfe);
			}
		}  while (true);

		return  input;
	}
	
	private void run() throws Exception  {

		int  choice = getChoice();

		while (choice != 0)  {

			if (choice == 1)  {

				SendMail163 send = new SendMail163();
				send.run();
				break;

			} else if (choice == 2)  {

				GetMail163 get = new GetMail163();
				get.run();
				break;

			} else if (choice == 3)  {

				SendMailqq send = new SendMailqq();
				send.run();
				break;

			}else if (choice == 4)  {

				GetMailqq get = new GetMailqq();
				get.run();
				break;
				
			}
			choice = getChoice();
		}
		while (choice ==0) {break;}
	}
}



