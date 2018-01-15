package com.erchuinet.common;
import java.security.Key;  
        
import javax.crypto.Cipher;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESedeKeySpec;  
import javax.crypto.spec.IvParameterSpec;  
        

public class DesUtil {  
     private final static String secretKey = "workworld@lx100$#365#$" ;  
     private final static String iv = "01234567" ;  
     private final static String encoding = "utf-8" ;  
        
     /** 
      *  
      * @param plainText 
      * @return 
      * @throws Exception  
      */ 
     public static String encode(String plainText) throws Exception {  
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
        
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
         byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
         return Base64.encode(encryptData);  
     }  
        
     /** 
      *  
      * @return 
      * @throws Exception 
      */ 
     public static String decode(String encryptText) throws Exception {  
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        
         byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        
         return new String(decryptData, encoding);  
     }  
} 