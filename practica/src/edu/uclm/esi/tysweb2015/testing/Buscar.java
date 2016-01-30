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
public class Buscar {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String palabra,  mensajeEsperado;
  @Parameters   
  public static Collection<String[]> valores() { 
	  return Arrays.asList (new String[][] {      
			  {"prueba", "Se han encontrado 5 anuncios."},//busqueda que devuelve anuncios
			  {"no hay", "Se han encontrado 0 anuncios."},//busquead sin anuncios
			  });   
  }
  public Buscar(String palabra, String mensajeEsperado) { 
	  this.palabra=palabra; 
	  this.mensajeEsperado=mensajeEsperado;   
	  } 
  

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBuscar() throws Exception {
    driver.get(baseUrl + "/practica/index.html#formFlotante");
    driver.findElement(By.id("inputBus")).clear();
    driver.findElement(By.id("inputBus")).sendKeys("palabra");
    driver.findElement(By.id("buscar")).click();
    Thread.sleep(1000);
    try{
    	assertEquals(driver.findElement(By.id("infoAreaPrincipal")).getText(), mensajeEsperado);
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

 
}
