package fatec.mkkg.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity handleInvalidCredentials(InvalidCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensagens", List.of(ex.getMessage())));
	}

	@ExceptionHandler(ErroFormatacao.class)
	public ResponseEntity handleDateTimeParseException(ErroFormatacao ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensagens", List.of(ex.getMessage())));
	}

}
