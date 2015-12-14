package edu.uclm.esi.tysweb2015.acciones;

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class SubirFoto  extends ActionSupport {
		private String resultado;
		private String tempFileName;
		private Object uploadContentType;
		private int idFoto;
		private File upload;
		
		public String execute(){
			try{
				String tmpFolder=System.getProperty("java.io.tmpdir");
				int rnd=Math.abs(new Random().nextInt());
				this.tempFileName=tmpFolder+"tysweb2015/"+rnd;
				File theFile=new File (tempFileName);
				FileUtils.copyFile(upload,  theFile);
				int idAnuncio=(int) ServletActionContext.getRequest().getSession().getAttribute("idAnuncio");
				Gestor gestor=Gestor.get();
				gestor.insertarFoto(theFile,idAnuncio);
				ServletActionContext.getRequest().getSession().removeAttribute("idAnuncio");
				this.resultado="OK";
				return SUCCESS;
			}catch(Exception e){
				this.resultado=e.getMessage();
				return ERROR;
			}
		}
	public void setFoto(File upload){
		this.upload=upload;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}
	public void setUploadContentType(Object uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	public String getResultado(){
		return this.resultado;
	}
}