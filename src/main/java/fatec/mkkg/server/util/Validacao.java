package fatec.mkkg.server.util;

public class Validacao {

    public static String validar(Object input, String nomeCampo) {
        if (input == null || input.toString().isBlank()) {
            return nomeCampo;
        }

        return "";
    }

}
