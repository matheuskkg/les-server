package mkkg.fatec.esiii.util;

import mkkg.fatec.esiii.domain.FachadaResponseDTO;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

//https://gist.github.com/daniel-shuy/212084f23bf67b357076374377dd7773
public class Validacao {
    public static FachadaResponseDTO getErrorMessages(BindingResult bindingResult) {
        FachadaResponseDTO fachadaResponseDTO = new FachadaResponseDTO();

        fachadaResponseDTO.setMensagens(bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList()));

        return fachadaResponseDTO;
    }
}
