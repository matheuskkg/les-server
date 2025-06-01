package fatec.mkkg.server.util;

import org.mindrot.jbcrypt.BCrypt;

public class Criptografia {

    public static String criptografar(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt(12));
    }

    public static boolean verificarCriptografia(String plainInput, String hash) {
        return BCrypt.checkpw(plainInput, hash);
    }

}
