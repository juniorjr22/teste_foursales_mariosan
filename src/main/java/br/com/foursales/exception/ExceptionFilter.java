package br.com.foursales.exception;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionFilter {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);
	private final MessageSource messageSource;

	public ExceptionFilter(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorDto> handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		logger.error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());

		Set<ErrorDto> errors = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> buildError(error.getCode(), error.getDefaultMessage())).collect(Collectors.toSet());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
	}

	private ErrorDto buildError(String code, String message) {
		return new ErrorDto(code, message);
	}

	private ApiErrorDto baseErrorBuilder(HttpStatus httpStatus, Set<ErrorDto> errorList) {
		return new ApiErrorDto(new Date(), httpStatus.value(), httpStatus.name(), errorList);
	}

}
