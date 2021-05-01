package br.com.foursales.util;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;

public class CriptografiaUtil {

	private static final String SENHA_CRIPTOGRAFIA = "senha-de-seguranca";

	public static String criptografar(String texto) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(SENHA_CRIPTOGRAFIA);
		try {
			return textEncryptor.encrypt(texto);
		} catch (EncryptionOperationNotPossibleException e) {
			return texto;
		}
	}

	public static String descriptografar(String texto) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(SENHA_CRIPTOGRAFIA);
		try {
			return textEncryptor.decrypt(texto);
		} catch (EncryptionOperationNotPossibleException e) {
			return texto;
		}
	}

}
