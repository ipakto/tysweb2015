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

public class CrearCuenta {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String email,nombre,ap1,ap2,telefono,pw1,pw2,mensajeEsperado;
  
  
  @Parameters   
  public static Collection<Object[]> valores() { 
	  return Arrays.asList (new Object[][]{      
			  {"manuel.gote@gmail.com","Manuel", "González","Torres", "687954123", "pass1", "pass1","USUARIO REGISTRADO CORRECTAMENTE"},//creacion correcta
			  {"manuela.te@gmail.com","Manuela", "Terma","García", "675120123", "pass1", "pass1","USUARIO REGISTRADO CORRECTAMENTE"},//creacion correcta
			  {"moneteGris@gmail.com","Carmelo", "López","Torres", "697826323", "pass1", "pass2","Las passwords no coinciden"}, // password no coinciden
			  {"manuel.gote@gmail.com","Manuel", "González","Torres", "687954123", "pass1", "pass1","Error al registrar usuario. ¿Tal vez ya existe?"}//usuario registrado
			  });   
  }
  
  public CrearCuenta(String email, String nombre, String ap1, String ap2, String telefono, String pw1, String pw2, String mensajeEsperado){
	  this.email=email;
	  this.nombre=nombre;
	  this.ap1=ap1;
	  this.ap2=ap2;
	  this.telefono=telefono;
	  this.pw1=pw1;
	  this.pw2=pw2;
	  this.mensajeEsperado=mensajeEsperado;
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCrearCuenta() throws Exception {
    driver.get(baseUrl + "/practica/index.html#formFlotante");
    driver.findElement(By.id("crearCuenta")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(email);
    driver.findElement(By.id("nombre")).clear();
    driver.findElement(By.id("nombre")).sendKeys(nombre);
    driver.findElement(By.id("apellido1")).clear();
    driver.findElement(By.id("apellido1")).sendKeys(ap1);
    driver.findElement(By.id("apellido2")).clear();
    driver.findElement(By.id("apellido2")).sendKeys(ap2);
    driver.findElement(By.id("telefono")).clear();
    driver.findElement(By.id("telefono")).sendKeys(telefono);
    driver.findElement(By.id("pwd1")).clear();
    driver.findElement(By.id("pwd1")).sendKeys(pw1);
    driver.findElement(By.id("pwd2")).clear();
    driver.findElement(By.id("pwd2")).sendKeys(pw2);
    new Select(driver.findElement(By.id("selectCCAA"))).selectByVisibleText("Valencia");
    driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
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
