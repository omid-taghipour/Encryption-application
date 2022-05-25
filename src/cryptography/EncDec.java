package cryptography;

public class EncDec {

    private static int counter = 0;
    private int enc_id;
    private String msg;
    private String key;
    private String cipher;
    private int algo;

    public static int getCounter() {
        return counter;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public static void setCounter(int counter) {
        EncDec.counter = counter;
    }

    public int getEnc_id() {
        return enc_id;
    }

    public void setEnc_id(int enc_id) {
        this.enc_id = enc_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAlgo() {
        return algo;
    }

    public void setAlgo(int algo) {
        this.algo = algo;
    }

    public EncDec(String msg, String key, String cipher, int algo) {
        this.enc_id = ++counter;
        this.msg = msg;
        this.key = key;
        this.cipher = cipher;
        this.algo = algo;
    }

    @Override
    public String toString() {
        String output = "Message: " + this.msg + "\nKey: " + this.key + "\nCipher: " + this.cipher + "\nAlgorithm: ";
        switch (this.algo) {
            case 0 ->
                output += "Caesar Cipher";
            case 1 ->
                output += "Playfair Cipher";
            case 2 ->
                output += "Vigenere Cipher";
            case 3 ->
                output += "Block Cipher";
            case 4 ->
                output += "One Time Pad";
        }
        return output;
    }

}
