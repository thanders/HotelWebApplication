package org.beans;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

// This is a class for ad hoc methods
public class AdHoc {
	// declare class variables
	private BigInteger randomNumber;

	// constructor
	public AdHoc() {
		// TODO Auto-generated constructor stub

	}
	
	public BigInteger randomNumber() {
        // creating the object of SecureRandom 
        SecureRandom sr = new SecureRandom(new byte[] { 1, 2, 3, 4 }); 

        // Declaring the string variable 
        String str = "Foxhound"; 

        // Declaring the byte Array b 
        byte[] b = str.getBytes(); 

        // printing the byte array 
        System.out.println("Byte array before operation : " + Arrays.toString(b)); 

        // generating user-specified number of random bytes 
        // using nextBytes() method 
        sr.nextBytes(b); 

        // printing the new byte array 
        System.out.println("Byte array after operation : " + Arrays.toString(b)); 

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            stringBuilder.append(b[i]);
        }
        
        String newString = stringBuilder.toString();
        String   tmpString = newString.replaceAll( "-", "" );
        randomNumber = new BigInteger(tmpString);
        
		return this.randomNumber;
	}
	
	public BigInteger randomBigInteger() {
        Random rand = new Random();
        BigInteger result = new BigInteger(20, rand); 
        return result;
    }

}
