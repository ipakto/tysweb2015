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
public class InicioSesion {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
private String email;
private String pw1;
private String mensajeEsperado;

  @Parameters   
  public static Collection<Object[]> valores() { 
	  return Arrays.asList (new Object[][]{      
			  {"manuel.gote@gmail.com", "pass1", "USUARIO REGISTRADO CORRECTAMENTE"},//usuario y pass válidas
			  {"manuela.te@gmail.com","pass1", "USUARIO REGISTRADO CORRECTAMENTE"},//usuario y pass válidas
			  {"manue@gmail.com", "p", "Login o contraseña incorrectos"}, //usuario y contrasña no validas
			  {"ma@gmail.com", "pass1", "Login o contraseña incorrectos"}, //usuario no registrado y pass correcta
			  { "manuel.gote@gmail.com", "p", "Login o contraseña incorrectos"}//password incorrecta y usuario válido
			  });   
  }
  
  public InicioSesion(String email, String pw1, String mensajeEsperado){
	  this.email=email;
	  this.pw1=pw1;
	  this.mensajeEsperado=mensajeEsperado;
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/practica/index.html";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testInicioSesion() throws Exception {
    driver.get(baseUrl + "/practica/index.html");
    driver.findElement(By.id("login")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(email);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(pw1);
    driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
    Thread.sleep (1000);
    try{
    	assertEquals(driver.findElement(By.id("email")).getText(),email);
    }catch (Error e) {    
    	assertEquals(closeAlertAndGetItsText(),mensajeEsperado);
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
