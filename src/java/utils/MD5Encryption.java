/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class MD5Encryption {

    public String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String passwordMD5 = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return passwordMD5;
    }
    
    public static void main(String[] args) {
        try {
            MD5Encryption converter = new MD5Encryption();
            String a = "123";
            String md5 = converter.hashPassword(a);
            System.out.println("MD5 of " + a + " is: " + md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
}
