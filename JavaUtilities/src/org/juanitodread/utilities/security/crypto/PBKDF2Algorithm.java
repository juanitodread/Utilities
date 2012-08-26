package org.juanitodread.utilities.security.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 * Generates a secret key with the PBKDF2 algorithm.
 * @author juanitodread
 *
 */
public class PBKDF2Algorithm {

	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int derivedKeyLength = 128;
	public static final int iterations = 20000;
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] generateSecureKeyByte(String key) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return generateSecureKeyByte(key, generateSalt(), iterations, derivedKeyLength);
	}
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @param salt Is the salt needs for PBKDF2.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] generateSecureKeyByte(String key, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return generateSecureKeyByte(key, salt, iterations, derivedKeyLength);		
	}
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @param salt Is the salt needs for PBKDF2.
	 * @param iterations The number of iterations 10,000 or more is secure.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] generateSecureKeyByte(String key, byte[] salt, int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException{	
		return generateSecureKeyByte(key, salt, iterations, derivedKeyLength);
	}
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm.
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @param salt Is the salt needs for PBKDF2.
	 * @param iterations The number of iterations 10,000 or more is secure.
	 * @param derivedKeyLength Is the size of the key, by default the AES on Java only permits 128.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] generateSecureKeyByte(String key, byte[] salt, int iterations, int derivedKeyLength) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return generateSecureKey(key, salt, iterations, derivedKeyLength).getEncoded();		
	}
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static SecretKey generateSecureKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return generateSecureKey(key, generateSalt(), iterations, derivedKeyLength);
	}
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @param salt Is the salt needs for PBKDF2.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static SecretKey generateSecureKey(String key, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return generateSecureKey(key, salt, iterations, derivedKeyLength);		
	}
	
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @param salt Is the salt needs for PBKDF2.
	 * @param iterations The number of iterations 10,000 or more is secure.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static SecretKey generateSecureKey(String key, byte[] salt, int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException{	
		return generateSecureKey(key, salt, iterations, derivedKeyLength);
	}
	
	/**
	 * Generates a secret key using the PBKDF2 algorithm.
	 * @param key The key with you want to generates the PBKDF2 key.
	 * @param salt Is the salt needs for PBKDF2.
	 * @param iterations The number of iterations 10,000 or more is secure.
	 * @param derivedKeyLength Is the size of the key, by default the AES on Java only permits 128.
	 * @return A PBKDF2 secret key that can be used on AES algorithm.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static SecretKey generateSecureKey(String key, byte[] salt, int iterations, int derivedKeyLength) throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
		return factory.generateSecret(spec);		
	}
	
	
	/**
	 * Get a random salt using a SecureRandom. You can save the salt to generate the same key
	 * @return A byte array with the salt
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] generateSalt() throws NoSuchAlgorithmException{
		byte[] salt = new byte[8];
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(salt);
		return salt;
	}
	
}
