package edu.uclm.esi.tysweb2015.webSockets;

/******************************************************************************************
 * *****************TECNOLOG�AS Y SISTEMAS DE LA INFORMACI�N*******************************
 * ******************ESCUELA SUPERIOR DE INFORM�TICA(UCLM)*********************************
 * ************************PR�CTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodr�guez-Bobada Aranda								  *
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
