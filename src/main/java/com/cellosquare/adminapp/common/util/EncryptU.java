package main.java.com.cellosquare.adminapp.common.util;

import KISA.SeedCBC;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

public class EncryptU {
	private static final String KEY_AES = "AES";

	private static final int KEY_LENGTH = 16;

	public static String encrypt(HttpServletRequest request, String plainText) throws EncoderException {
		
		//String keyPath = XmlPropertyManager.getPropertyValue("encryptKeyPath");
		
		String keyPath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/encryptKey/key.dat";
		System.out.println(keyPath);
		
		SeedCBC s = new SeedCBC();
		String retMsg = s.LoadConfig(keyPath);
		
		if(!retMsg.equals("OK")) {	
			throw new EncoderException(retMsg);
		} else {
			return new String(Base64.encodeBase64(s.Encryption(plainText.getBytes())));
		}
	}
	
	public static String decrypt(HttpServletRequest request, String cipherText) throws EncoderException {
		
		//String keyPath = XmlPropertyManager.getPropertyValue("encryptKeyPath");
		
		String keyPath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/encryptKey/key.dat";
		System.out.println(keyPath);
		
		byte[] bCipherText = Base64.decodeBase64(cipherText);

		SeedCBC s = new SeedCBC();
		String retMsg = s.LoadConfig(keyPath);
		
		if (!retMsg.equals("OK")) {	
			throw new EncoderException(retMsg);
		} else {
			return new String(s.Decryption(bCipherText));
		}
	}

	/**
	 * 加密
	 */
	public static String encrypt(String src, String key) {
		try {
			if (key == null || key.length() != KEY_LENGTH) {
				throw new IllegalArgumentException("密钥长度必须是16位");
			}
			byte[] raw = key.getBytes();
			SecretKeySpec skySpec = new SecretKeySpec(raw, KEY_AES);
			Cipher cipher = Cipher.getInstance(KEY_AES);
			cipher.init(Cipher.ENCRYPT_MODE, skySpec);
			byte[] encrypted = cipher.doFinal(src.getBytes());
			return byte2hex(encrypted);
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 解密
	 *
	 * @param src
	 * @param key
	 * @return
	 */
	public static String decrypt(String src, String key) {
		try {
			if (key == null || key.length() != KEY_LENGTH) {
				throw new IllegalArgumentException("密钥长度必须是16位");
			}
			byte[] raw = key.getBytes();
			SecretKeySpec skySpec = new SecretKeySpec(raw, KEY_AES);
			Cipher cipher = Cipher.getInstance(KEY_AES);
			cipher.init(Cipher.DECRYPT_MODE, skySpec);
			byte[] encrypted1 = hex2byte(src);
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original);
		} catch (Exception e) {
			//logger.error("AES解密出错", e);
		}
		return null;
	}
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String tmp;
		for (byte value : b) {
			tmp = (Integer.toHexString(value & 0XFF));
			if (tmp.length() == 1) {
				hs.append("0").append(tmp);
			} else {
				hs.append(tmp);
			}
		}
		return hs.toString().toUpperCase();
	}
	public static byte[] hex2byte(String hex) {
		if (hex == null) {
			return new byte[0];
		}
		int l = hex.length();
		int n = 2;
		if (l % n == 1) {
			return new byte[0];
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / n; i++) {
			b[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}
}
