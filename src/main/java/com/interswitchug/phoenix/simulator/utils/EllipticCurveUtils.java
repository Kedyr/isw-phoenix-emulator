package com.interswitchug.phoenix.simulator.utils;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.KeyAgreement;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;

	public class EllipticCurveUtils {
		
		private static String ELIPTIC_CURVE_PRIME256 = "prime256v1";
		private String protocal;
		
		public EllipticCurveUtils(String protocal) {
			this.protocal = protocal;
			Security.addProvider(new BouncyCastleProvider());
		}
		
		
		
		public PublicKey loadPublicKey (byte [] data) throws Exception
		{
			ECParameterSpec params = ECNamedCurveTable.getParameterSpec(ELIPTIC_CURVE_PRIME256);
			ECPublicKeySpec pubKey = new ECPublicKeySpec(
					params.getCurve().decodePoint(data), params);
			KeyFactory kf = KeyFactory.getInstance(this.protocal, "BC");
			return kf.generatePublic(pubKey);
		}
		
		public  PrivateKey loadPrivateKey (byte [] data) throws Exception
		{
			ECParameterSpec params = ECNamedCurveTable.getParameterSpec(ELIPTIC_CURVE_PRIME256);
			ECPrivateKeySpec prvkey = new ECPrivateKeySpec(new BigInteger(data), params);
			KeyFactory kf = KeyFactory.getInstance(this.protocal, "BC");
			return kf.generatePrivate(prvkey);
		}
		

		
		public static byte [] savePrivateKey (PrivateKey key) throws Exception
		{
			ECPrivateKey eckey = (ECPrivateKey)key;
			return eckey.getD().toByteArray();
		}
		
		public static byte [] savePublicKey (PublicKey key) throws Exception
		{
			ECPublicKey eckey = (ECPublicKey)key;
			return eckey.getQ().getEncoded(true);
		}
		
		public static String getSignature(String plaintext, PrivateKey privateKey) throws Exception {
			Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
			ecdsaSign.initSign(privateKey);
			ecdsaSign.update(plaintext.getBytes(StandardCharsets.UTF_8));
			return Base64.encodeBase64String( ecdsaSign.sign());
		}
		
		public  String getSignature(String plaintext, String privateKey) throws Exception {
			PrivateKey _privateKey = this.loadPrivateKey(Base64.decodeBase64(privateKey));
			Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
			ecdsaSign.initSign(_privateKey);
			ecdsaSign.update(plaintext.getBytes(StandardCharsets.UTF_8));
			return Base64.encodeBase64String( ecdsaSign.sign());
		}
		
		public static boolean verifySignature(String signature,String plaintext, PublicKey publicKey) throws Exception {
			Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(plaintext.getBytes(StandardCharsets.UTF_8));
			return ecdsaVerify.verify(Base64.decodeBase64(signature));
		}
		
		public  boolean verifySignature(String signature,String plaintext, String publicKey) throws Exception {
			byte[] pubicKeybytes = Base64.decodeBase64(publicKey);
			Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
			ecdsaVerify.initVerify(loadPublicKey(pubicKeybytes));
			ecdsaVerify.update(plaintext.getBytes(StandardCharsets.UTF_8));
			return ecdsaVerify.verify(Base64.decodeBase64(signature));
		}
		
		public  String doECDH (String privateKey,String publicKey) throws Exception
		{
			byte[] dataPrv = Base64.decodeBase64(privateKey);
			byte[] dataPub = Base64.decodeBase64(publicKey);
			KeyAgreement ka = KeyAgreement.getInstance("ECDH", "BC");
			ka.init(loadPrivateKey(dataPrv));
			ka.doPhase(loadPublicKey(dataPub), true);
			byte [] secret = ka.generateSecret();
			return Base64.encodeBase64String(secret);
		}
		
		public KeyPair generateKeypair() throws Exception {
			KeyPairGenerator kpgen = KeyPairGenerator.getInstance(this.protocal, "BC");
			kpgen.initialize(new ECGenParameterSpec(ELIPTIC_CURVE_PRIME256), new SecureRandom());
			return kpgen.generateKeyPair();
		}
			
		public String getPrivateKey(KeyPair pair) throws Exception {
				byte [] priv =  savePrivateKey(pair.getPrivate());
				return Base64.encodeBase64String(priv);
		}
		
		public String getPublicKey(KeyPair pair) throws Exception {
			byte [] pub =  savePublicKey(pair.getPublic());
			return Base64.encodeBase64String(pub);
		}
		
	}

