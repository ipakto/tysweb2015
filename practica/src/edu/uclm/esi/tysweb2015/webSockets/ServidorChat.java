package edu.uclm.esi.tysweb2015.webSockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/ServidorChat")
public class ServidorChat {
	static Set<Session> users=Collections.synchronizedSet(new HashSet<Session>());
	static Hashtable<String,ChatUser> chatUsers= new Hashtable<>();
	@OnOpen
	public void open(Session session) {
		users.add(session);
		System.out.println("Se ha abierto una cnxn.");
	}
	@OnClose
	public void close(Session session) {
		users.remove(session);
	}
	@OnMessage
	public void recibir(String msg, Session session) {
		/*try {
			JSONObject jso=new JSONObject(msg);
			if (jso.get("tipo").equals("mensaje")) {
				enviar(session, "eco", jso.getString("remitente"), jso.getString("contenido"));
			}
		} catch (JSONException e) {
		}*/
		System.out.println("Se ha recibido: "+msg);
		try {
			JSONObject jso=new JSONObject(msg);
			if(jso.get("tipo").equals("identificacion")){
				String login=jso.getString("texto");
				ChatUser chatUser=new ChatUser(login,session);
				chatUsers.put(login,chatUser);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void enviar(Session session, String tipo, String remitente, String texto) {
		JSONObject jso=new JSONObject();
		try {
			jso.put("tipo", tipo);
			jso.put("remitente", remitente);
			jso.put("contenido", texto);
			session.getBasicRemote().sendText(jso.toString());
		} catch (IOException e) {
			users.remove(session);
		}
		catch(JSONException e) {}
	}
}