package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {


	public static BigInteger hashOf(String entity) {

		BigInteger hashint = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(entity.getBytes());
			byte[] digest = md.digest();
			String theHex = toHex(digest);
			hashint = new BigInteger(theHex,16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
		return hashint;
	}
	
	public static BigInteger addressSize() {
		BigInteger addressSize = new BigInteger("2").pow(bitSize());
		return addressSize;
	}
	
	public static int bitSize() {
        int digestlen = 0;
        try {
            digestlen = MessageDigest.getInstance("MD5").getDigestLength();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
