package edu.uclm.esi.tysweb2015.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class GenerateID {
//Metodo 1
  public static String generate() {
	SecureRandom r=new SecureRandom();
    return new BigInteger(130, r).toString(32);
  }
  //Metodo 2
  public static String generateUUID(){
	  return UUID.randomUUID().toString().replaceAll("-", "");
  }
}