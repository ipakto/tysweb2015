package edu.uclm.esi.tysweb2015.webSockets;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import javax.websocket.Session;

public class ChatUser {

	private String login;
	private Session session;

	public ChatUser(String login, Session session) {
		this.login=login;
		this.session=session;
	}
	public Session getSession(){
		return session;
	}

}
