package vn.viettel.core.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.viettel.core.data.response.Response;

@ControllerAdvice
public class ExceptionHandleController {
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus
    @ResponseBody
    public Response handleException(Exception exception) {
        exception.printStackTrace();
        return new Response(500, "System error");
    }

}
