package vn.viettel.core.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.viettel.core.data.response.Response;

@ControllerAdvice
@Slf4j
public class ExceptionHandleController {
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus
    @ResponseBody
    public Response handleException(Exception exception) {
        log.error(exception.getMessage());
        return new Response(500, "System error");
    }


    @ExceptionHandler(value = { DataIntegrityViolationException.class })
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public Response handleDataIntegrityViolationException(Exception exception) {
        log.error(exception.getMessage());
        return new Response(409, "Code existed");
    }
}
