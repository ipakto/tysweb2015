/**
 * 
 */

$(document).ready(function() {
	
	$('.list-group-item').click(function() {
		$('.list-group-item').removeClass('active');
		$(this).addClass('active');
	});
	

});
function crearForm(){
	var b=document.getElementById("paraForms");
	var s=document.createElement("div");
	s.setAttribute("id","forms");
	b.appendChild(s);
}

function borrarForm(){
	//document.body.removeChild(document.getElementById("forms"));
	document.getElementById("paraForms").removeChild(document.getElementById("forms"));
}
function gestionarCuenta(){
	  var bc=document.getElementById("miga");
	  var nodos=bc.childNodes;
	  var liB=nodos[2];
	  for (var i=nodos.length-1;i>1;i--){
	    bc.removeChild(nodos[i]);
	  }
	  var li=document.createElement("li");
	  li.innerHTML="Gestionar cuenta";
	  bc.setAttribute("class","breadcrumb");
	  li.setAttribute("class","active");
	  li.value=-2;
	  bc.appendChild(li);
	  
	  var areaPrincipal=document.getElementById("areaPrincipal");
	  areaPrincipal.innerHTML="";
	  $("#areaPrincipal").load("FormGestionarCuenta.html");
	}

function Anuncio(id,descripcion,titulo, precio,ruta,tipo){
	this.id=id;
	this.descripcion=descripcion;
	this.titulo=titulo;
	this.precio=precio;
	this.fotos=ruta;
	this.tipo=tipo;
}

Anuncio.prototype.getWidget=function(){
	var div= document.createElement("div");
	div.setAttribute("id","divAnuncio"+this.id);
	div.setAttribute("class","divAnuncio");
	//IMAGEN ANUNCIO
	var foto=document.createElement("img");
	//foto.setAttribute("src",this.fotos);
	servirFoto(this.fotos,foto);
	foto.setAttribute("height","175px");
	foto.setAttribute("width","150px");
	//TITULO ANUNCIO
	var verMas=document.createElement("a");
	verMas.setAttribute("href","javascript:mostrarAnuncio("+this.id+");");
	var etiquetaTitulo=document.createElement("label");
	etiquetaTitulo.innerHTML=this.titulo;
	verMas.setAttribute("id", "labelVerMas");
	verMas.appendChild(etiquetaTitulo);			
	var divPrecioFav= document.createElement("div");
	//PRECIO ANUNCIO
	var precio=document.createElement("label");
	precio.innerHTML=this.precio+" €";
	//DESMARCAR FAVORITO (TIPO = 0) O BORRAR (TIPO = 1) O MARCAR FAVORITO (TIPO = 2)
	var boton=document.createElement("span");
	var a=document.createElement("a");
	
	if(this.tipo==0){
		a.setAttribute("href","javascript:desmarcarFavorito("+this.id+");");
		a.setAttribute("class","desmarcarFav");
		boton.setAttribute("id","btnFavorito2");
	}else if(this.tipo==1){
		a.setAttribute("href","javascript:borrarAnuncio("+this.id+");");
		boton.setAttribute("id","btnBorrarAnuncio");
	}else if(this.tipo==2){
		a.setAttribute("href","javascript:marcarFavorito("+this.id+");");
		a.setAttribute("class","marcarFav");		
		boton.setAttribute("id","btnFavorito");
	}
	
	//CREACION DE ESTRUCTURA	
	div.appendChild(foto);
	div.appendChild(document.createElement("br"));
	div.appendChild(verMas);
	div.appendChild(document.createElement("br"));
	a.appendChild(boton);
	divPrecioFav.appendChild(a);
	divPrecioFav.appendChild(precio);
	div.appendChild(divPrecioFav);
	
	return div;
	
}


function servirFoto(ruta,img){
	if(ruta==="http://localhost:8080/practica/img/NO_EXISTE.png"){
		img.setAttribute("src","http://localhost:8080/practica/img/NO_EXISTE.png");
	}else{
		img.setAttribute("src","/practica/ServirArchivo?rutaFoto="+ruta);
		/*var request=new XMLHttpRequest();
		request.open("POST","/practica/ServirFoto");
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.onreadystatechange=function(){
			if (request.readyState==4){ //ESTADO DE LA PETICIÓN
				if(request.status==200){//ESTADO De la respuesta devuelta x el servidor
					img.src = 'data:image/jpeg;base64,' + btoa(request.responseText);
					//img.setAttribute("src","data:image/jpeg;base64,"+);
				}else{
					alert("Error: " + request.statusText);
				}
			}
		};
		var par="rutaFoto="+ruta;
		request.send(par);*/
	}
}
function servirVideo(ruta,video){
	video.setAttribute("src","/practica/ServirArchivo?rutaVideo="+ruta)
}