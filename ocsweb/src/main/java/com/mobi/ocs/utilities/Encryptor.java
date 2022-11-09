package com.mobi.ocs.utilities;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.jboss.logging.Logger;

import com.mobi.ocs.modal.OTPEncryptedData;
import com.mobi.ocs.service.SMSServiceImpl;

public class Encryptor {
	private static final String ALGO = "AES";
	/*
	 * private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e',
	 * 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
	 */

	private static final Logger logger = Logger.getLogger(SMSServiceImpl.class);
	private static byte[] keyValue = "VWcvImCCxcyN7nu1".getBytes();
	private static String key1 = "OgRYRqiL9GRVc68TL81O";

	private static String algorithm = "AES/CBC/PKCS5PADDING";

	public static IvParameterSpec generateIv() {
//		byte[] iv = keyValue;
//		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(keyValue);
	}

	public static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		password = String.format("%s%s", password, getRemainingString((16 - password.length())));
		System.out.println("password --> " + password);
		SecretKey secret = new SecretKeySpec(password.getBytes(), "AES");
		return secret;
	}

	private static String getRemainingString(int width) {
		System.out.println("RequiredFillers --> " + width);
		System.out.println("getRemainingString --> " + key1.substring(0, width));
		return key1.substring(0, width);
	}

	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(n);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	public static OTPEncryptedData encrypt(String otp, String mobileNumber) {

		logger.info("otp --> " + otp);
		logger.info("mobileNumber --> " + mobileNumber);

		try {
			SecretKey key = getKeyFromPassword(mobileNumber, key1);
			IvParameterSpec ivParameterSpec = generateIv();

			/*
			 * String ivKey = Base64.getEncoder().encodeToString(ivParameterSpec.getIV());
			 * System.out.println("ivParameterSpec --> " + ivKey);
			 * System.out.println("ivParameterSpec --> " +
			 * Base64.getDecoder().decode(ivKey));
			 */

			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
			byte[] cipherText = cipher.doFinal(otp.getBytes());
			String encrypted = Base64.getEncoder().encodeToString(cipherText);

			OTPEncryptedData response = new OTPEncryptedData();
			response.setEncryptedText(encrypted);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String cipherText, String mobile) {

		try {
			SecretKey key = getKeyFromPassword(mobile, key1);
			IvParameterSpec ivParameterSpec = generateIv();
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
			byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
			return new String(plainText);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
