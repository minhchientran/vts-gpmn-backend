package viettel.gpmn.platform.core.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import viettel.gpmn.platform.core.data.response.Response;

import java.util.Locale;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@ResponseBody
@ControllerAdvice
public class ExceptionHandleController {

    private Boolean isTest;
    private MessageSource messageSource;

    @ExceptionHandler(value = { Exception.class })
    public Response handleException(HttpRequest httpRequest, Exception exception) {
        if (isTest) {
            exception.printStackTrace();
        }
        log.error(exception.getMessage());
        return new Response(500, getLocalizedMessage("error.system"));
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public Response handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.error(exception.getMessage());
        return new Response(HttpStatus.CONFLICT.value(), getLocalizedMessage("error.existed"));
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = (String) Objects.requireNonNull(exception.getDetailMessageArguments())[1];
        log.error(message);
        return new Response(HttpStatus.BAD_REQUEST.value(), message);
    }

    @ExceptionHandler(value = { HandlerMethodValidationException.class })
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Response handleHandlerMethodValidationException(HandlerMethodValidationException exception) {
        String message =
                exception.getAllValidationResults().get(0).getMethodParameter().getParameter().getName()
                        + " "
                        + exception.getAllValidationResults().get(0).getResolvableErrors().get(0).getDefaultMessage();
        log.error(message);
        return new Response(HttpStatus.BAD_REQUEST.value(), message);
    }

    private String getLocalizedMessage(String translationKey)
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(translationKey, null, locale);
    }

}
