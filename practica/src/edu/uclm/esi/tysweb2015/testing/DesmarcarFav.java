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
public class DesmarcarFav {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
private String idAnuncio;
private String mensajeEsperado;

  @Parameters   
  public static Collection<String[]> valores() { 
	  return Arrays.asList (new String[][] {      
			  {"1230", "ANUNCIO DESMARCADO COMO FAVORITO"},//desmarcar anuncio favorito propio
			  {"1227", "ANUNCIO DESMARCADO COMO FAVORITO"},//desmarcar anuncio favorito ajeno
			  {"9999", "NO ES UN ANUNCIO FAVORITO"},//desmarcar anunicio no exitente
			  {"1228", "NO ES UN ANUNCIO FAVORITO"}//desmarcar anunicio que  no está como favorito
			  });   
  }
  
  public void DesmarcarFav(String idAnuncio, String mensajeEsperado){
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
  public void testDesmarcarFav() throws Exception {
    driver.get(baseUrl + "/practica/index.html#formFlotante");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("btnFavorito2")).click();
    //COMO NO DETECTA EL DIV EN EL CUAL SE HACE CLIC, NO SE HA PODIDO PARAMETRIZAR LOS VALORES.
    assertEquals("ANUNCIO DESMARCADO COMO FAVORITO", closeAlertAndGetItsText());
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
