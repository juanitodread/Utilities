package org.juanitodread.utilities.security.crypto;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.juanitodread.utilities.encodecs.Base64;

/**
 * Encrypt or decrypt text using a dynamic PBKDF2 secret key
 * @author juanitodread
 *
 */
public class AESAlgorithm {

	private byte[] mySecretKey = null;
	private byte[] iv = null;
	
	public static final String basicKey = "abcdefghijklmnñopqrstuvwxyz0123456789";
	private SecretKey secretKey = null;
	
	public AESAlgorithm() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException{
		try {
			mySecretKey = PBKDF2Algorithm.generateSecureKeyByte(basicKey);
			secretKey = new SecretKeySpec(mySecretKey, "AES");
		} catch (InvalidKeySpecException e) {
			throw new InvalidKeyException("Llave inválida");
		}
	}
	
	public AESAlgorithm(String basicKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException{
		try {
			mySecretKey = PBKDF2Algorithm.generateSecureKeyByte(basicKey);
			secretKey = new SecretKeySpec(mySecretKey, "AES");
		} catch (InvalidKeySpecException e) {
			throw new InvalidKeyException("Llave inválida");
		}
	}

	public String encrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, InvalidParameterSpecException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		if(iv != null){
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
		}else{
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
		}
			
		AlgorithmParameters params = cipher.getParameters();
		iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] encryptedText = cipher.doFinal(text.getBytes("UTF-8"));
		
		return Base64.encodeToString(encryptedText);		
	}

	public String decrypt(String text, byte[] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(initialVector));
		byte[] decryptedText = cipher.doFinal(Base64.decode(text));
		return new String(decryptedText, "UTF-8");
	}

	public String decrypt(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
		return decrypt(text, iv);
	}

	public byte[] getIv() {
		return iv;
	}

	public void setIv(byte[] iv) {
		this.iv = iv;
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

}
