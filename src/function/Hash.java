package function;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class Hash {

    public static String sha256(String stringToHash) {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                String hash = Integer.toHexString(0xff & b);
                if (hash.length() == 1) stringBuilder.append('0');
                stringBuilder.append(hash);
            }
            return stringBuilder.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
}
