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

	import com.opensymphony.xwork2.ActionSupport;

	import edu.uclm.esi.tysweb2015.dominio.Gestor;
	
	public class ModificarUsuario extends ActionSupport {

		private String emailSession;
		private String email;
		private String nombre;
		private String apellido1;
		private String apellido2;
		private String telefono;
		private String resultado;
			
		public String execute(){
			try{
				Gestor gestor=Gestor.get();
				gestor.modificarPerfil(emailSession,email,nombre,apellido1,apellido2,telefono);
				this.resultado="OK";
				return SUCCESS;
			}catch(Exception e){
				this.resultado=e.getMessage();
				return ERROR;
			}
		}
		public String getResultado(){
			return this.resultado;
		}
		public void setEmailSession(String emailSession){
			this.emailSession=emailSession;
		}
		public void setEmail(String email){this.email=email;}
		public void setNombre(String nombre){this.nombre=nombre;}
		public void setApellido1(String ap1){this.apellido1=ap1;}
		public void setApellido2(String ap2){this.apellido2=ap2;}
		public void setTelefono(String telefono){this.telefono=telefono;}

	}