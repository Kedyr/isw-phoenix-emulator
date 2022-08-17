package com.interswitchug.phoenix.simulator.utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interswitchug.phoenix.simulator.dto.PhoenixResponseCodes;

public class CryptoUtils {

	private static Logger LOG = LoggerFactory.getLogger(CryptoUtils.class);

	public static String encrypt(String plaintext, String terminalKey) throws SystemApiException {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			byte[] message = plaintext.getBytes(StandardCharsets.UTF_8);
			byte[] iv = Hex.decode(UtilMethods.randomBytesHexEncoded(16));
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance(Constants.AES_CBC_PKCS7_PADDING, "BC");
			byte[] keyBytes = Base64.decodeBase64(terminalKey);
			SecretKey secretKey = new SecretKeySpec(keyBytes, Constants.AES_CBC_PKCS7_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			byte[] secret = cipher.doFinal(message);
			ByteArrayOutputStream b = new ByteArrayOutputStream(); // concatnate IV with cipher text
			b.write(iv);
			b.write(secret);
			return Base64.encodeBase64String(b.toByteArray());
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to encrypt object");
		}
	}

	public static String decrypt(byte[] encryptedValue, String terminalKey) throws SystemApiException {
		try {
			byte[] secretKeyBytes = Base64.decodeBase64(terminalKey);
			SecretKey secretKey = new SecretKeySpec(secretKeyBytes, Constants.AES_CBC_PKCS7_PADDING);
			byte[] iVbytes = Arrays.copyOfRange(encryptedValue, 0, 16);// remove the iv
			encryptedValue = Arrays.copyOfRange(encryptedValue, 16, encryptedValue.length);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iVbytes);
			Cipher cipher = Cipher.getInstance(Constants.AES_CBC_PKCS7_PADDING, "BC");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			byte[] clear = cipher.doFinal(encryptedValue);
			return new String(clear);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to decrypt object");
		}
	}

	public static String decryptWithPrivate(String plaintext) throws SystemApiException {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			byte[] message = Base64.decodeBase64(plaintext);
			Cipher cipher = Cipher.getInstance(Constants.RSA_NONE_OAEPWithSHA256AndMGF1Padding, "BC");
			cipher.init(Cipher.DECRYPT_MODE, getRSAPrivate());
			byte[] secret = cipher.doFinal(message);
			return new String(secret);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to decryptWithPrivate ");
		}
	}

	public static String decryptWithPrivate(byte[] message, PrivateKey privateKey) throws SystemApiException {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(Constants.RSA_NONE_OAEPWithSHA256AndMGF1Padding, "BC");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] secret = cipher.doFinal(message);
			return new String(secret);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to decryptWithPrivate ");
		}
	}

	public static String decryptWithPrivate(String plaintext, String privateKey) throws SystemApiException {
		byte[] message = Base64.decodeBase64(plaintext);
		return decryptWithPrivate(message, getRSAPrivate(privateKey));
	}

	public static String encryptWithPrivate(String plaintext) throws SystemApiException {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			byte[] message = plaintext.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance(Constants.RSA_NONE_OAEPWithSHA256AndMGF1Padding, "BC");
			cipher.init(Cipher.ENCRYPT_MODE, getRSAPrivate());
			byte[] secret = cipher.doFinal(message);
			return Base64.encodeBase64String(secret);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to encryptWithPrivate ");
		}
	}

	public static PrivateKey getRSAPrivate() throws SystemApiException {
		return getRSAPrivate(Constants.PRIKEY.trim());
	}

	public static PrivateKey getRSAPrivate(String privateKey) throws SystemApiException {
		try {
			byte[] keyBytes = Base64.decodeBase64(privateKey.trim().getBytes());
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(spec);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to getRSAPrivate ");
		}
	}

	public static String signWithPrivateKey(String data) throws SystemApiException {
		return signWithPrivateKey(data, getRSAPrivate());
	}

	public static String signWithPrivateKey(String data, String privateKey) throws SystemApiException {
		return signWithPrivateKey(data, getRSAPrivate(privateKey));
	}

	public static String signWithPrivateKey(String data, PrivateKey privateKey) throws SystemApiException {
		try {
			if (data.equals(""))
				return "";
			Signature sign = Signature.getInstance("SHA256withRSA");
			sign.initSign(privateKey);
			sign.update(data.getBytes(StandardCharsets.UTF_8));
			return new String(Base64.encodeBase64(sign.sign()), StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to signWithPrivateKey ");
		}
	}

	public static boolean verifySignature(String signature, String message) throws SystemApiException {
		RSAPublicKey pubKey = getPublicKey(Constants.PUBKEY);
		return verifySignature(signature, message, pubKey);
	}

	public static boolean verifySignature(String signature, String message, String publicKey)
			throws SystemApiException {
		RSAPublicKey pubKey = getPublicKey(publicKey);
		return verifySignature(signature, message, pubKey);
	}

	public static boolean verifySignature(String signature, String message, RSAPublicKey pubKey)
			throws SystemApiException {
		try {
			Signature sign = Signature.getInstance("SHA256withRSA");
			sign.initVerify(pubKey);
			sign.update(message.getBytes(StandardCharsets.UTF_8));
			return sign.verify(Base64.decodeBase64(signature.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to verifySignature ");
		}
	}

	public static KeyPair generateKeyPair() throws SystemApiException {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			return kpg.generateKeyPair();
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to generateKeyPair ");
		}
	}

	public static RSAPublicKey getPublicKey(String publicKeyContent) throws SystemApiException {
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyContent));
			return (RSAPublicKey) kf.generatePublic(keySpecX509);
		} catch (Exception e) {
			LOG.error("Exception trace {} ", ExceptionUtils.getStackTrace(e));
			throw new SystemApiException(PhoenixResponseCodes.INTERNAL_ERROR.CODE, "Failure to getPublicKey ");
		}
	}
}
