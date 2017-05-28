package com.smartnewsvillaadmin.utilities;

import com.smartnewsvillaadmin.utilities.Constant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {

    // Retrive methods of MAFactoryHelper
    public String encodeMD5(String plaintext) {
        String sessionid = plaintext;
        byte[] defaultBytes = sessionid.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            String foo = messageDigest.toString();
			// System.out.println("sessionid " + sessionid + " md5 version is "
            // + hexString.toString());
            sessionid = hexString + "";
        } catch (NoSuchAlgorithmException nsae) {
        }
        return sessionid;

    }

    public String encrypt(HttpServletRequest request, String message)
            throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, UnsupportedEncodingException {
        // Get a cipher object.
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, new SessionUtils().getKey(request, Constant.KEY));

		// Gets the raw bytes to encrypt, UTF8 is needed for
        // having a standard character set
        byte[] stringBytes = message.getBytes("UTF8");

        // encrypt using the cypher
        byte[] raw = cipher.doFinal(stringBytes);

		// converts to base64 for easier display.
        // BASE64Encoder encoder = new BASE64Encoder();
        // String base64 = encoder.encode(raw);
        //
        // return base64.replace("/", "@");
        return "";
    }

    public String encrypt(String value) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(Constant.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            String encryptedValue64 = Base64.getEncoder().encodeToString(encryptedByteValue);
            encryptedValue64 = encryptedValue64.replace("/", "SmS_rm_vp");
            return encryptedValue64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "none";
    }

    public String decrypt(String value) {
        try {
            value = value.replace("SmS_rm_vp", "/");
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(Constant.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = Base64.getDecoder().decode(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            String decryptedValue = new String(decryptedByteValue, "utf-8");
            return decryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "none";
    }

    private Key generateKey() {
        Key key = new SecretKeySpec(Constant.EKEY.getBytes(), Constant.ALGORITHM);
        return key;
    }
}
