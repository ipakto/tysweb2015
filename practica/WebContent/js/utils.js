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
