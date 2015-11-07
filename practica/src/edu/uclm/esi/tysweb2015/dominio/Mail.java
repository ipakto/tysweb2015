package edu.uclm.esi.tysweb2015.dominio;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by anirudh on 28/10/14.
 */
public class Mail {
	private static String username = "prj.tysweb2015@gmail.com";
	private static String password = "P@co1234";

	private static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		return session;
	}
	public static void enviarMail(String destino){
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			message.setSubject("Recuperar contraseña");
			message.setText("Recuperar contraseña: "+
			"http://localhost:8080/practica/FormDPCambiar.html?user="+destino);

			Transport.send(message);

			//System.out.println("Mail sent succesfully!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}