package mkkg.fatec.esiii.util;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

//https://gist.github.com/daniel-shuy/212084f23bf67b357076374377dd7773
public class Validacao {
    public static List<String> getErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
