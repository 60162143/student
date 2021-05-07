/**
 * @author Sefik Ilkin Serengil
 * 
 */

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ClassEncryption {
	
	public static void main(String[] args) throws Exception{
		String algorithm = "AES";
		// byte[] key = {75, 110, -45, -61, 101, -103, -26, -25, 55, -70, 19, 51, 66, -91, -35, 19}; //128 bit key
		
		// generate key
                byte[] key = new byte[16]; // 16 * 8 = 128bit
		byte[] src = args[0].getBytes();
                System.arraycopy(src, 0, key, 0, src.length);
		
		System.out.println(Arrays.toString(key));
		encrypt(args[1], algorithm, key);
	}
	
	public static void encrypt(String classname, String algorithm, byte[] key) throws Exception{
		Path file = Paths.get("./" + classname + ".class");
		byte[] content = Files.readAllBytes(file);
		Cipher encryption = Cipher.getInstance(algorithm);
		encryption.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, 0, key.length, algorithm));
		byte[] encryptedContent = encryption.doFinal(content);
		writeToFile(classname, encryptedContent);
	}
	
	public static void writeToFile(String filename, byte[] content) throws Exception{
		FileOutputStream out = new FileOutputStream("./enc_"+filename + ".class");
		out.write(content);
		out.close();
	}

}
