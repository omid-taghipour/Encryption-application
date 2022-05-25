package cryptography;

import java.util.LinkedList;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

public class Cryptography {

    public static LinkedList<EncDec> enc_tracker_list = new LinkedList<>();

    public static void main(String[] args) {
        Main menu = new Main();
        menu.show();
    }

    public static void add_Encryption(String message, String key, String cipher, int algo) {
        EncDec temp_obj = new EncDec(message, key, cipher, algo);
        enc_tracker_list.add(temp_obj);
        JOptionPane.showMessageDialog(null, "Encryption process is done");
        System.out.println(enc_tracker_list);
        System.out.println("\n\n");
    }
    
    public static void delete_Encryption(int id){
        enc_tracker_list.remove(id);
        JOptionPane.showMessageDialog(null, "Deleted!");
    }

    public static StringBuffer Encrypt_Caesar(String message, int key) {
        StringBuffer result = new StringBuffer();

        try {
            for (int i = 0; i < message.length(); i++) {
                if (Character.isUpperCase(message.charAt(i))) {
                    char ch = (char) (((int) message.charAt(i)
                            + key - 65) % 26 + 65);
                    result.append(ch);
                } else {
                    char ch = (char) (((int) message.charAt(i)
                            + key - 97) % 26 + 97);
                    result.append(ch);
                }
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid key has been entered");
            return null;
        }
    }

    public static String decrypt_Caesar(String cipherText, int shiftKey) {
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

        cipherText = cipherText.toLowerCase();
        String message = "";
        for (int ii = 0; ii < cipherText.length(); ii++) {
            int charPosition = ALPHABET.indexOf(cipherText.charAt(ii));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0) {
                keyVal = ALPHABET.length() + keyVal;
            }
            char replaceVal = ALPHABET.charAt(keyVal);
            message += replaceVal;
        }
        return message;
    }

    public static String Encrypt_Playfair(String message, String key) {
        try {
            Playfair temp_obj = new Playfair(key, message);
            temp_obj.cleanPlayFairKey();
            temp_obj.generateCipherKey();
            String encText2 = temp_obj.encryptMessage();
            EncDec temp_playfair = new EncDec(temp_obj.getPlainText(), temp_obj.getKey(), encText2, 1);
            return encText2;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid key has been entered");
            return null;
        }
    }

// This function generates the key in
// a cyclic manner until it's length isi'nt
// equal to the length of original text
    public static String generateKey_vigenere(String str, String key) {
        try {

            int x = str.length();

            for (int i = 0;; i++) {
                if (x == i) {
                    i = 0;
                }
                if (key.length() == str.length()) {
                    break;
                }
                key += (key.charAt(i));
            }
            return key;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid key has been entered");
            return null;
        }
    }

// This function returns the encrypted text
// generated with the help of the key
    public static String cipherText_vigenere(String str, String key) {
        try{
            String cipher_text = "";

        for (int i = 0; i < str.length(); i++) {
            // converting in range 0-25
            int x = (str.charAt(i) + key.charAt(i)) % 26;

            // convert into alphabets(ASCII)
            x += 'A';

            cipher_text += (char) (x);
        }
        return cipher_text;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid key has been entered");
            return null;
        }
        
    }

// This function decrypts the encrypted text
// and returns the original text
    public static String originalText_vigenere(String cipher_text, String key) {
        try{
        String orig_text = "";

        for (int i = 0; i < cipher_text.length()
                && i < key.length(); i++) {
            // converting in range 0-25
            int x = (cipher_text.charAt(i)
                    - key.charAt(i) + 26) % 26;

            // convert into alphabets(ASCII)
            x += 'A';
            orig_text += (char) (x);
        }
        return orig_text;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid key has been entered");
            return null;
        }
    }

// This function will convert the lower case character to Upper case
    public static String LowerToUpper_vigenere(String s) {
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                str.setCharAt(i, Character.toUpperCase(s.charAt(i)));
            }
        }
        s = str.toString();
        return s;
    }

    public static String Encrypt_DES(String text, final String key) {
        try{
        String cipher = "";
        DES des_obj = new DES();
        cipher = des_obj.encrypt(text, key);
        return cipher;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid key has been entered");
            return null;
        }
    }

    public static String Decrypt_DES(String text, String key) {
        String cipher = "";
        DES des_obj = new DES();
        cipher = des_obj.decrypt(text, key);
        return cipher;
    }

    public static String OTP_Encryption(String text, String key) {
        try {
            String cipherText = "";
            int cipher[] = new int[key.length()];

            for (int i = 0; i < key.length(); i++) {
                cipher[i] = text.charAt(i) - 'A'
                        + key.charAt(i)
                        - 'A';
            }
            for (int i = 0; i < key.length(); i++) {
                if (cipher[i] > 25) {
                    cipher[i] = cipher[i] - 26;
                }
            }
            for (int i = 0; i < key.length(); i++) {
                int x = cipher[i] + 'A';
                cipherText += (char) x;
            }
            return cipherText;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid key");
            return null;
        }
       
    }

    public static String OTP_Decryption(String s, String key) {
        String plainText = "";

        int plain[] = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            plain[i]
                    = s.charAt(i) - 'A'
                    - (key.charAt(i) - 'A');
        }

        for (int i = 0; i < key.length(); i++) {
            if (plain[i] < 0) {
                plain[i] = plain[i] + 26;
            }
        }

        for (int i = 0; i < key.length(); i++) {
            int x = plain[i] + 'A';
            plainText += (char) x;
        }

        return plainText;
    }
}
