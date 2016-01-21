package edu.uclm.esi.tysweb2015.testing;

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
public class BorrarAnuncio {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String idAnuncio, mensajeEsperado;
  @Parameters   
  public static Collection<String[]> valores() { 
	  return Arrays.asList (new String[][] {      
			  {"1224", "ANUNCIO BORRADO CORRECTAMENTE"},//correcto
			  {"1225", "ANUNCIO BORRADO CORRECTAMENTE"},//correcto
			  {"1226", "ANUNCIO BORRADO CORRECTAMENTE"},//correcto
			  {"1226", "NO SE PUEDE BORRAR ESTE ANUNCIO"}, //ya se ha borrado y no se debe poder borrar
			  {"9999", "NO SE PUEDE BORRAR ESTE ANUNCIO"},//no existe el anuncio
			  {"1227", "NO SE PUEDE BORRAR ESTE ANUNCIO"} //anuncio de otro usuario
			  });   
  }
  public BorrarAnuncio(String idAnuncio, String mensajeEsperado) { 
	  this.idAnuncio=idAnuncio; 
	  this.mensajeEsperado=mensajeEsperado;   
	  } 
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/practica/index.html";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBorrarAnuncio() throws Exception {
    driver.get(baseUrl + "/practica/index.html#formFlotante");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.cssSelector("#divAnuncio"+idAnuncio+" > div > a > #btnBorrarAnuncio")).click();
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
