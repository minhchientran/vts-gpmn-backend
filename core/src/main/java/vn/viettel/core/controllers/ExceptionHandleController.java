package vn.viettel.core.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import vn.viettel.core.data.response.Response;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus()
    Response handleException(RuntimeException exception, WebRequest request) {
        return new Response(500, "System error");
    }
}
