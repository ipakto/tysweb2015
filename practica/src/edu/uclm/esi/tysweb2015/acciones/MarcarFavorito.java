package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class MarcarFavorito extends ActionSupport  {

		// TODO Auto-generated method stub
		private int idAnuncio;
		
		private String emailAnunciante;
		private String resultado;
		public String execute(){
			try{
				Gestor g= Gestor.get();
				Usuario u=new Usuario(emailAnunciante);
				u.existe();
				g.marcarFavorito(idAnuncio, u.getId());
				this.resultado="OK";
				return SUCCESS;
			}catch(Exception e){
				this.resultado="No se ha podido marcar como favorito.";
				return ERROR;
			}
		}
		
		public void setEmailAnunciante(String emailAnunciante) {
			this.emailAnunciante = emailAnunciante;
		}
		public String getResultado() {
			return resultado;
		}
		public void setResultado(String resultado) {
			this.resultado = resultado;
		}
		public void setIdAnuncio(int idAnuncio) {
			this.idAnuncio = idAnuncio;
		}
	}