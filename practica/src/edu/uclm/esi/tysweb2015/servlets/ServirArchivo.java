package edu.uclm.esi.tysweb2015.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServirArchivo
 */
@WebServlet("/ServirArchivo")
public class ServirArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//http://www.javatpoint.com/example-to-display-image-using-servlet
		response.setContentType("image/jpeg");  
		ServletOutputStream out;  
		out = response.getOutputStream();  
		String rutaFoto=request.getParameter("rutaFoto");
		String rutaVideo=request.getParameter("rutaVideo");
		FileInputStream fin=null;;
		if(rutaFoto!=null){
			fin = new FileInputStream(rutaFoto); 
			response.setContentType("image/jpeg");  
		}else if(rutaVideo!=null){
			fin = new FileInputStream(rutaVideo);
			response.setContentType("video/mp4"); 
			
		}
		
		BufferedInputStream bin = new BufferedInputStream(fin);  
		BufferedOutputStream bout = new BufferedOutputStream(out);  
		int ch =0; ;  
		while((ch=bin.read())!=-1) {bout.write(ch);}  

		bin.close();  
		fin.close();  
		bout.close();  
		out.close();  
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//http://www.javatpoint.com/example-to-display-image-using-servlet
		ServletOutputStream out;  
		out = response.getOutputStream();  
		String rutaFoto=request.getParameter("rutaFoto");
		String rutaVideo=request.getParameter("rutaVideo");
		FileInputStream fin=null;;
		if(rutaFoto!=null){
			fin = new FileInputStream(rutaFoto); 
			response.setContentType("image/jpeg");  
		}else if(rutaVideo!=null){
			fin = new FileInputStream(rutaVideo);
			response.setContentType("video/mp4"); 
			
		}

		BufferedInputStream bin = new BufferedInputStream(fin);  
		BufferedOutputStream bout = new BufferedOutputStream(out);  
		int ch =0; ;  
		while((ch=bin.read())!=-1) {bout.write(ch);}  

		bin.close();  
		fin.close();  
		bout.close();  
		out.close();  
	}

}
