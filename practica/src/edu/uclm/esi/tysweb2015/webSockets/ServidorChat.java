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
	static ChatUser admin=null;
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
		System.out.println("Se ha recibido: "+msg);
		try {
			JSONObject jso=new JSONObject(msg);
			if(jso.get("tipo").equals("identificacion")){
				String login=jso.getString("texto");
				if(login.equals("admin")){
					admin=new ChatUser(login,session);
				}else if(admin==null){
					enviar(session,"NoDisponible","No existe ninguna persona del soporte conectada al chat. Intentelo más tarde","");
				}else{
					ChatUser chatUser=new ChatUser(login,session);
					chatUsers.put(login,chatUser);
					enviar(admin.getSession(),"nuevoChat",jso.getString("texto"),"");
				}
			}else if(jso.get("tipo").equals("mensaje") && admin!=null){
				enviar(admin.getSession(),"mensaje",jso.getString("remitente"),jso.getString("texto"));
			}else if(jso.get("tipo").equals("respuesta")){
				ChatUser u=chatUsers.get(jso.getString("destinatario"));
				enviar(u.getSession(),"respuesta",jso.getString("remitente"),jso.getString("texto"));
			}else if(jso.get("tipo").equals("cierre") && admin!=null){
				enviar(admin.getSession(),"cierre",jso.getString("texto"),"");
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