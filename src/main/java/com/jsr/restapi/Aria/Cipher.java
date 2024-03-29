package com.jsr.restapi.Aria;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Scanner;

import com.jsr.restapi.Aria.ARIAEngine;
import com.jsr.restapi.Base64.Base64;
import com.jsr.restapi.padding.BlockPadding;


/**
 * AIRA algorithm to encrypt or decrypt the data is the class that provides the ability to.
 * @author shson
 *
 */
public class Cipher {

    /**
     * ARIA encryption algorithm block size
     */
    private static final int ARIA_BLOCK_SIZE = 16;

    /**
     * ARIA algorithm to encrypt the data.
     * @param data Target Data
     * @param key Masterkey
     * @param keySize Masterkey Size
     * @param charset Data character set
     * @return Encrypted data
     * @throws UnsupportedEncodingException If character is not supported
     * @throws InvalidKeyException If the Masterkey is not valid
     */
    public static String encrypt(String data, byte[] key, int keySize, String charset)
            throws UnsupportedEncodingException, InvalidKeyException {

        byte[] encrypt = null;
        if( charset == null ) {
            encrypt = BlockPadding.getInstance().addPadding(data.getBytes(), ARIA_BLOCK_SIZE);
        } else {
            encrypt = BlockPadding.getInstance().addPadding(data.getBytes(charset), ARIA_BLOCK_SIZE);
        }

        ARIAEngine engine = new ARIAEngine(keySize);
        engine.setKey(key);
        engine.setupEncRoundKeys();

        int blockCount = encrypt.length / ARIA_BLOCK_SIZE;
        for( int i = 0; i < blockCount; i++ ) {

            byte buffer[] = new byte[ARIA_BLOCK_SIZE];
            System.arraycopy(encrypt, (i * ARIA_BLOCK_SIZE), buffer, 0, ARIA_BLOCK_SIZE);

            buffer = engine.encrypt(buffer, 0);
            System.arraycopy(buffer, 0, encrypt, (i * ARIA_BLOCK_SIZE), buffer.length);
        }

        return Base64.toString(encrypt);
    }

    /**
     * ARIA algorithm to decrypt the data.
     * @param data Target Data
     * @param key Masterkey
     * @param keySize Masterkey Size
     * @param charset Data character set
     * @return Decrypted data
     * @throws UnsupportedEncodingException If character is not supported
     * @throws InvalidKeyException If the Masterkey is not valid
     */
    public static String decrypt(String data, byte[] key, int keySize, String charset)
            throws UnsupportedEncodingException, InvalidKeyException {

        byte[] decrypt = Base64.toByte(data);

        ARIAEngine engine = new ARIAEngine(keySize);
        engine.setKey(key);
        engine.setupDecRoundKeys();

        int blockCount = decrypt.length / ARIA_BLOCK_SIZE;
        for( int i = 0; i < blockCount; i++ ) {

            byte buffer[] = new byte[ARIA_BLOCK_SIZE];
            System.arraycopy(decrypt, (i * ARIA_BLOCK_SIZE), buffer, 0, ARIA_BLOCK_SIZE);

            buffer = engine.decrypt(buffer, 0);
            System.arraycopy(buffer, 0, decrypt, (i * ARIA_BLOCK_SIZE), buffer.length);
        }

        if( charset == null ) {
            return new String(BlockPadding.getInstance().removePadding(decrypt, ARIA_BLOCK_SIZE));
        } else {
            return new String(BlockPadding.getInstance().removePadding(decrypt, ARIA_BLOCK_SIZE), charset);
        }
    }

    /**
     * The sample code in the Cipher class
     * @param args none
     */
    public static void main(String args[]) {


        try {

            byte[] key = new byte[32];
            for( int i = 0; i < key.length; i++ ) {
                key[i] = (byte)i;
            }

            Scanner sc = new Scanner(System.in);
            String data = sc.next();

            data = Cipher.encrypt(data, key, key.length*8, null);
            System.out.println("암호화: "+ data);

            data = Cipher.decrypt(data, key, key.length*8, null);
            System.out.println("복호화: "+ data);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
