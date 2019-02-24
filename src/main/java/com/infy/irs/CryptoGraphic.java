package com.infy.irs;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rajesh 
 * Cryptographic class encrypts & Decrypts based on
 *         StandardPBESStringEncryptor
 * 
 */
public class CryptoGraphic {
	private String password;
	private String input;
	private static final Logger logger = LoggerFactory.getLogger(CryptoGraphic.class);

	/**
	 * @param password
	 * @param input
	 */
	public CryptoGraphic(String password, String input) {
		super();
		this.password = password;
		this.input = input;
	}

	/**
	 * @return StandardPBEStringEncryptor
	 */
	public StandardPBEStringEncryptor cryptoObject() {
		StandardPBEStringEncryptor cryptoObject = new StandardPBEStringEncryptor();
		cryptoObject.setPassword(password);
		return cryptoObject;
	}

	/**
	 * @param cryptoObject
	 * @return
	 */
	public String decrypt(StandardPBEStringEncryptor cryptoObject) {
		String decrypted = cryptoObject.decrypt(input);

		return decrypted;
	}

	/**
	 * @param cryptoObject
	 * @return
	 */
	public String encrypt(StandardPBEStringEncryptor cryptoObject) {

		String encrypted = cryptoObject.encrypt(input);
		return encrypted;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CryptoGraphic cryptoGraphicObject = null;
		try {
			cryptoGraphicObject = new CryptoGraphic(args[0], args[1]);

			if (args[2].equalsIgnoreCase("encrypt"))

			System.out.println(cryptoGraphicObject.encrypt(cryptoGraphicObject.cryptoObject()));

			else {
				logger.info("Decrypting Started");
				String msg = cryptoGraphicObject.decrypt(cryptoGraphicObject.cryptoObject());
				KafkaWriter writerObject = new KafkaWriter();
				writerObject.WriteToKafka(msg, "test");
			
			}
		}

		catch (Exception e) {
			logger.error(e.getMessage());

		}

	}

}
