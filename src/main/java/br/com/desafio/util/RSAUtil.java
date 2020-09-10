package br.com.desafio.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {
	
	private static final String ALGORITMO_CRIPTOGRAFIA = "RSA";
	private static final int TAMANHO_CHAVE_BITS = 2048;
	private static final String CIPHER_TIPO_TRANSFORMACAO = "RSA/ECB/PKCS1Padding";

    public static ChavePrivadaPublica gerarChavesPrivadaPublica(){
    	ChavePrivadaPublica chaves = new ChavePrivadaPublica();
    	PrivateKey chavePrivada;
        PublicKey chavePublica;
    	try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITMO_CRIPTOGRAFIA);
			keyGen.initialize(TAMANHO_CHAVE_BITS);
			KeyPair pair = keyGen.generateKeyPair();
			chavePrivada = pair.getPrivate();
			chavePublica = pair.getPublic();
			
			//Converte em String
			String chavePrivadaString = Base64.getEncoder().encodeToString(chavePrivada.getEncoded());
			String chavePublicaString = Base64.getEncoder().encodeToString(chavePublica.getEncoded());
			chaves.setChavePrivada(chavePrivadaString);
			chaves.setChavePublica(chavePublicaString);
		} catch (Exception e) {
		}
    	return chaves;
    }
    
    private static PublicKey getChavePublica(String base64ChavePublica){
        PublicKey chavePublica = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64ChavePublica.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITMO_CRIPTOGRAFIA);
            chavePublica = keyFactory.generatePublic(keySpec);
            return chavePublica;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return chavePublica;
    }

    private static PrivateKey getChavePrivada(String base64ChavePrivada){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64ChavePrivada.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(ALGORITMO_CRIPTOGRAFIA);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    private static byte[] criptografar(String dados, String chavePublica) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(CIPHER_TIPO_TRANSFORMACAO);
        cipher.init(Cipher.ENCRYPT_MODE, getChavePublica(chavePublica));
        return cipher.doFinal(dados.getBytes());
    }

    private static String descriptografar(byte[] dados, PrivateKey chavePrivada) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_TIPO_TRANSFORMACAO);
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
        return new String(cipher.doFinal(dados));
    }
    
    public static String criptografarString(String dados, String base64ChavePublica) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return Base64.getEncoder().encodeToString(criptografar(dados, base64ChavePublica));
    }

    public static String descriptografarString(String dados, String base64ChavePrivada) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return descriptografar(Base64.getDecoder().decode(dados.getBytes()), getChavePrivada(base64ChavePrivada));
    }
    
    public static class ChavePrivadaPublica {
    	
    	private String chavePrivada;
    	private String chavePublica;
    	
    	public ChavePrivadaPublica() {
    		super();
    	}
    	
    	public ChavePrivadaPublica(String chavePrivada, String chavePublica) {
    		super();
    		this.chavePrivada = chavePrivada;
    		this.chavePublica = chavePublica;
    	}
    	
    	public String getChavePrivada() {
    		return chavePrivada;
    	}
    	public void setChavePrivada(String chavePrivada) {
    		this.chavePrivada = chavePrivada;
    	}
    	public String getChavePublica() {
    		return chavePublica;
    	}
    	public void setChavePublica(String chavePublica) {
    		this.chavePublica = chavePublica;
    	}
    }
    
    /*
    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
        try {
        	ChavePrivadaPublica chaves = gerarChavesPrivadaPublica();
            String dadosCriptografadosString = RSAUtil.criptografarString("teste@1234", chaves.getChavePublica());
            System.out.println(dadosCriptografadosString);
            String dadosDescriptografadosString = RSAUtil.descriptografarString(dadosCriptografadosString, chaves.getChavePrivada());
            System.out.println(dadosDescriptografadosString);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }*/
    
}