package br.com.twproject.web.common.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(NoSuchElementException e) {
        var model = Map.of("message", e.getMessage());
        return new ModelAndView("error/error", model);
    }

}
