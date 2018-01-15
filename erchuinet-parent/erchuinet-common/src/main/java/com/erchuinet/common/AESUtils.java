package com.erchuinet.common;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 */
public class AESUtils {
	/**
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 *
	 * @throws Exception
	 */
	public static byte[] initSecretKey() {
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
		kg.init(128);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	
	public static Key toKey(byte[] key) {
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}


	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 *
	 * @param data
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	
	 */
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}

	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	
	public static byte[] decrypt(byte[] data, Key key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}


	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
		// ��ԭ��Կ
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}

	
	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	public static String showByteArray(byte[] data) {
		if (null == data) {
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for (byte b : data) {
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {

		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String str, String key) throws Exception {
		if (str == null || key == null)
			return null;
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
		byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
		return new BASE64Encoder().encode(bytes);
	}

	public static String aesDecrypt(String str, String key) throws Exception {
		if (str == null || key == null)
			return null;
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
		byte[] bytes = new BASE64Decoder().decodeBuffer(str);
		bytes = cipher.doFinal(bytes);
		return new String(bytes, "utf-8");
	}

	public static void main(String[] args) throws Exception {
//		byte[] key = initSecretKey();
//		System.out.println("key��" + Base64.encodeBase64String(key));
//		System.out.println("key��" + showByteArray(key));

		// ָ��key
		String kekkk = "9iEepr1twrizIEKrs1hs2A==";
		System.out.println("kekkk:" + showByteArray(Base64.decodeBase64(kekkk)));
		Key k = toKey(Base64.decodeBase64(kekkk));

		String data = "{\"requestName\":\"BeforeIn\",\"requestValue\":{\"carCode\":\"��AD0V07\",\"inTime\":\"2016-09-29 10:06:03\",\"inChannelId\":\"4\",\"GUID\":\"1403970b-4eb2-46bc-8f2b-eeec91ddcd5f\",\"inOrOut\":\"0\"},\"Type\":\"0\"}";
		System.out.println(Base64.encodeBase64String(data.getBytes()));
		
		System.out.println("����ǰ����: string:" + data);
		System.out.println("����ǰ����: byte[]:" + showByteArray(data.getBytes()));
		System.out.println();
		
		byte[] encryptData = encrypt(data.getBytes(), k);
		String encryptStr=parseByte2HexStr(encryptData);
		
		System.out.println("���ܺ�����: byte[]:" + showByteArray(encryptData));
		System.out.println("���ܺ�����: Byte2HexStr:" + encryptStr);
		System.out.println();

		byte[] decryptData = decrypt(parseHexStr2Byte(encryptStr), k);
		System.out.println("���ܺ�����: byte[]:" + showByteArray(decryptData));
		System.out.println("���ܺ�����: string:" + new String(decryptData));

	}
}