package spring.cloud.dsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import spring.cloud.MessageException;
import spring.cloud.demo.model.ResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice(basePackages = {"spring.cloud.dsp"})
public class GlobalExceptionHandlerForRestRequest extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerForRestRequest.class);


    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseBody
    ResultModel handlerAllException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        LOGGER.error(writer.toString());
        ResultModel rst = ResultModel.createFail(ex.getMessage());
        return rst;
    }


}