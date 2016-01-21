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
public class PagarPREMIUM {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String nTarjeta, CVC, mesFCaducidad, anioFCaducidad,mensajeEsperado;
  
  @Parameters   
  public static Collection<String[]> valores() { 
	  return Arrays.asList (new String[][] {      
			  {"4111111111111111","123","12","2018", "Felicidades, ahora eres PREMIUM"},//pago correcto
			  {"0011111111111111","123","12","2018", "Invalid card number"},//n tarjeta erróneo
			  {"4111111111111111","A","12","2018","field_invalid_card_cvc"},//cvc incorreto
			  {"4111111111111111","123","13","2018","Invalid expiration date"}, //mes incorrecto
			  {"4111111111111111","123","12","2012","Invalid expiration date"}//año incorrecto
			  
			  });   
  }
  public PagarPREMIUM(String nTarjeta, String CVC, String mesFCaducidad, String anioFCaducidad, String mensajeEsperado) { 
	  this.nTarjeta=nTarjeta; 
	  this.CVC=CVC;
	  this.mesFCaducidad=mesFCaducidad;
	  this.anioFCaducidad=anioFCaducidad;
	  this.mensajeEsperado=mensajeEsperado;
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPagarPREMIUM() throws Exception {
    driver.findElement(By.id("email")).click();
    driver.findElement(By.xpath("//img[@onclick='pagarPaymill()']")).click();
    driver.findElement(By.cssSelector("input.card-holdername")).clear();
    driver.findElement(By.cssSelector("input.card-number")).sendKeys(nTarjeta);
    driver.findElement(By.cssSelector("input.card-cvc")).sendKeys(CVC);
    driver.findElement(By.cssSelector("input.card-expiry-month")).sendKeys(mesFCaducidad);
    driver.findElement(By.cssSelector("input.card-expiry-year")).sendKeys(anioFCaducidad);
    driver.findElement(By.cssSelector("button.submit-button")).click();
    Thread.sleep (1000);
    try{
    	 assertEquals(mensajeEsperado, driver.findElement(By.cssSelector("label.payment-errors")).getText());
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
