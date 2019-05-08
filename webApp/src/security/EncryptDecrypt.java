package security;

import java.security.SecureRandom;
import java.util.List;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AlgorithmParameters;



public class EncryptDecrypt {

	private String key = "";
	
	public EncryptDecrypt(){
		try {
			this.key = readFile(Paths.get(this.getClass().getResource("store.txt").toURI()).toString(),StandardCharsets.UTF_8);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getKey() {
		return this.key;
	}

	static String readFile(String path, Charset encoding) 
			throws IOException 
	{
		//byte[] encoded = Files.readAllBytes(Paths.get(path));
		List<String> lines = Files.readAllLines(Paths.get(path), encoding);
		return lines.get(0);
	}

	public static String encrypt(String record, String patientSecret) throws Exception {
		byte[] ivBytes;
		/*you can give whatever you want for patientSecret. This is for testing purpose*/
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		// Derive the key
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(patientSecret.toCharArray(), bytes, 65556, 128);
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		//encrypting the record
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] encryptedTextBytes = cipher.doFinal(record.getBytes(StandardCharsets.UTF_8));
		//prepend salt and vi
		byte[] buffer = new byte[bytes.length + ivBytes.length + encryptedTextBytes.length];
		System.arraycopy(bytes, 0, buffer, 0, bytes.length);
		System.arraycopy(ivBytes, 0, buffer, bytes.length, ivBytes.length);
		System.arraycopy(encryptedTextBytes, 0, buffer, bytes.length + ivBytes.length, encryptedTextBytes.length);
		return new Base64().encodeToString(buffer);
	}

	public static String decrypt(String encryptedDoc, String patientSecret) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		//strip off the salt and iv
		ByteBuffer buffer = ByteBuffer.wrap(new Base64().decode(encryptedDoc));
		byte[] saltBytes = new byte[20];
		buffer.get(saltBytes, 0, saltBytes.length);
		byte[] ivBytes1 = new byte[cipher.getBlockSize()];
		buffer.get(ivBytes1, 0, ivBytes1.length);
		byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes1.length];

		buffer.get(encryptedTextBytes);
		// Deriving the key
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(patientSecret.toCharArray(), saltBytes, 65556, 128);
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes1));
		byte[] decryptedTextBytes = null;
		try {
			decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
			return new String(decryptedTextBytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			return "";
		}
	}
}