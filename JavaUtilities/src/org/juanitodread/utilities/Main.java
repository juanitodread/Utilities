package org.juanitodread.utilities;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.juanitodread.utilities.encodecs.Base64;
import org.juanitodread.utilities.security.crypto.AESAlgorithm;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//Pasos para encriptar y desencriptar
			//Instanciamos el objeto AESAlgorithm
			AESAlgorithm aes = new AESAlgorithm();
			
			//encriptamos el cualquier texto y nos devolverá el texto encriptado en base64
			aes.setSecretKey(new SecretKeySpec(Base64.decode("W7MnPfwxXPPp2klkdieRjA=="), "AES"));
			aes.setIv(Base64.decode("gMwfda82k8UwOyT06+9XNA=="));
			String encryptedText = aes.encrypt("Anita lava la tina! :)");
			
			//Debemos obtener la llave secreta con la que fué encriptado y el vector de inicialización para poder desencriptar despues
			String secretKey = Base64.encodeToString(aes.getSecretKey().getEncoded());
			String iv = Base64.encodeToString(aes.getIv());
			
			System.out.println("Texto encriptado: " + encryptedText);
			System.out.println("Secret Key: " + secretKey);
			System.out.println("IV: " + iv);
			
			
			//Ahora vamos a desencriptar utilizando la misma Secret Key y el vector de inicialización
			aes = new AESAlgorithm();
			
			//seteamos la secret key y el vector de inicialización
			aes.setSecretKey(new SecretKeySpec(Base64.decode(secretKey), "AES"));
			aes.setIv(Base64.decode(iv));
			
			//Desencryptamos el texto encryptado
			String decryptedText = aes.decrypt(encryptedText);
			
			System.out.println("Texto desencriptado: " + decryptedText);						
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterSpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
