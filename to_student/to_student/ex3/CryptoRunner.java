/**
 * @author Sefik Ilkin Serengil
 * 
 */

import java.lang.reflect.Method;
import java.util.Arrays;

public class CryptoRunner {
	
	public static void main(String[] args) throws Exception{
		
		// byte[] key = {75, 110, -45, -61, 101, -103, -26, -25, 55, -70, 19, 51, 66, -91, -35, 19}; //128 bit key
		String classname = args[1];

                // generate key
                byte[] key = new byte[16]; // 16 * 8 = 128bit
                byte[] src = args[0].getBytes();
                System.arraycopy(src, 0, key, 0, src.length);
		
		String algorithm = "AES";
		
		EncryptedClassLoader myClassLoader = new EncryptedClassLoader();
		Class dynamicClass = myClassLoader.findClass(".", classname, algorithm, key);
		
		Method m = dynamicClass.getMethod("main", String[].class);
		m.invoke(null, new Object[] {null});
		
	}

}
