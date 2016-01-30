package edu.uclm.esi.tysweb2015.testing;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


@RunWith(Parameterized.class) 
public class PonerAnuncioNoFoto {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String titulo,precio,categoria, descripcion, mensajeEsperado;
  
  @Parameters   
  public static Collection<String[]> valores() { 
	  return Arrays.asList (new String[][] {      
			  {"Moto moderna","500","Motor","Bonita moto tuneada como la mejor. No deja a nadie indiferente.","Anuncio añadido correctamente"},//subida anuncio correcta
			  {"","30","Textil","Bonito jersey para hombre de invierno.", "Por favor, introduzca un titulo del anuncio"},//no puede insertar con titulo vacío
			  {"Zapatillas Nike","96","","Zapatillas de mi hijo casi sin usar, le ha crecido el pie al pobre","Por favor, seleccione una categoría"},//no se puede insertar sin categoria
			  {"Citroen C4","4500","Motor","","Por favor, introduzca una descripción del anuncio"},//no se puede insertar sin descripcion
			  {"iPhone6", "","Tecnología","Me compré este móvil pero me he comprado el nuevo, no lo quiero ya por eso vendo.","Por favor, introduzca un precio para el anuncio"} //no se puede insertar sin precio
			  });   
  }
  public PonerAnuncioNoFoto(String titulo, String precio, String categoria,
		String descripcion, String mensajeEsperado) {
	super();
	this.titulo = titulo;
	this.precio = precio;
	this.categoria = categoria;
	this.descripcion = descripcion;
	this.mensajeEsperado=mensajeEsperado;
  } 
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void testPonerAnuncioFoto() throws Exception {
    driver.get(baseUrl + "/practica/index.html");
    driver.findElement(By.id("ponerAnuncio")).click();
    driver.findElement(By.id("titulo")).clear();
    driver.findElement(By.id("titulo")).sendKeys(titulo);
    driver.findElement(By.id("precio")).clear();
    driver.findElement(By.id("precio")).sendKeys(precio);
    new Select(driver.findElement(By.id("categoria"))).selectByVisibleText(categoria);
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys(descripcion);
    driver.findElement(By.id("publicar")).click();
    Thread.sleep(1000);
    try{
    	assertEquals(mensajeEsperado, closeAlertAndGetItsText());
    }catch (Error e) {    
    	verificationErrors.append(e.toString()); 
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
