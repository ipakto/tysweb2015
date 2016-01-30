package edu.uclm.esi.tysweb2015.acciones;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class SubirArchivo  extends ActionSupport {
		private String resultado;
		private String tempFileName;
		private File upload;
		private String tipo;
		
		public String execute(){
			try{
				String tmpFolder=System.getProperty("java.io.tmpdir");
				int rnd=Math.abs(new Random().nextInt());
				this.tempFileName=tmpFolder+"tysweb2015/"+rnd;
				File theFile=new File (tempFileName);
				FileUtils.copyFile(upload,  theFile);
				int idAnuncio=(int) ServletActionContext.getRequest().getSession().getAttribute("idAnuncio");
				Gestor gestor=Gestor.get();
				gestor.insertarArchivo(theFile,idAnuncio,tipo);
				ServletActionContext.getRequest().getSession().removeAttribute("idAnuncio");
				this.resultado="OK";
				return SUCCESS;
			}catch(Exception e){
				this.resultado=e.getMessage();
				return ERROR;
			}
		}
	public void setArchivo(File upload){
		this.upload=upload;
	}
	public void setTipo(String tipo){
		this.tipo=tipo;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}
	public String getResultado(){
		return this.resultado;
	}
}