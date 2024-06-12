package viettel.gpmn.platform.core.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import viettel.gpmn.platform.core.data.response.Response;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class ExceptionHandleController {

    private Boolean isTest;

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus
    @ResponseBody
    public Response handleException(Exception exception) {
        if (isTest) {
            exception.printStackTrace();
        }
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
