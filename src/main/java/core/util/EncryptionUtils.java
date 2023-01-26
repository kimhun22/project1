package core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * Encryption
 * MD5(Message-Digest algorithm 5)
 * SHA1(Secure Hash Standard Algorithm 1)
 * Sample
 *
 * Algorithm MD5
 * String password = "";
 * String encPassword = EncryptionUtils.encryptMd5(password);
 *
 * Algorithm SHA1
 * String password = "";
 * String encPassword = EncryptionUtils.encryptSha1(password);
 */
public class EncryptionUtils {

	static Logger logger = (Logger) LogManager.getLogger(EncryptionUtils.class);

	 /**
	  * SHA1(Secure Hash Standard Algorithm 1)
	  * producing a 160bit digest(40 hex numbers) from any data with a maximum size of 264bits
	  * jdk1.4 over
	  * @param inputStr
	  * @return
	  * @throws NoSuchAlgorithmException
	  */
	public static String encryptSha256(String inputStr) {
		String rtnStr = "";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] digest = messageDigest.digest(inputStr.getBytes());
			rtnStr = Hex.encodeHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		return rtnStr;
	}

	//2018-01-04 salt추가
	public static String encryptSha256(String inputStr,byte[] salt) {
		String rtnStr = "";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.reset();
			messageDigest.update(salt);
			byte[] digest = messageDigest.digest(inputStr.getBytes());
			rtnStr = Hex.encodeHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		return rtnStr;
	}

 /**
  * encryption
  * @param encType : MD5, SHA-1, SHA-256
  * @param inputStr :
  * @return
  * @throws NoSuchAlgorithmException
  */
	public static String encrypt(String encType , String inputStr) {
		String rtnStr = "";
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance(encType);
			byte[] byteData = inputStr.getBytes();
			messageDigest.update(byteData);
			byte[] digest = messageDigest.digest();
			rtnStr = Hex.encodeHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		return rtnStr;
	}
}
