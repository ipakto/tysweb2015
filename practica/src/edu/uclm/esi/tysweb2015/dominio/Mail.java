package edu.uclm.esi.tysweb2015.dominio;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.uclm.esi.tysweb2015.dao.DAOTokens;
import edu.uclm.esi.tysweb2015.util.GenerateID;

import java.util.Properties;

/**
 * Created by anirudh on 28/10/14.
 */
public class Mail {
	private static String userName = "prj.tysweb2015@gmail.com";
	private static String password = "P@co1234";

	private static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		/*Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username,password);
	}
});*/
Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
});
		return session;
	}
	public static void enviarMail(String destino, int idUsuario) throws Exception{
		try {

			Message message = new MimeMessage(getSession());
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destino));
			message.setSubject("Recuperar contraseña");
			String token=GenerateID.generate();
			message.setText("Recuperar contraseña: "+
			"http://localhost:8080/practica/FormDPCambiar.html?u="+token);
			DAOTokens.crearRecuperar(token, idUsuario);
			Transport.send(message);

			//System.out.println("Mail sent succesfully!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}