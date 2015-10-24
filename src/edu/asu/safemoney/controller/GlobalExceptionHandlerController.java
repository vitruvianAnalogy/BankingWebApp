package edu.asu.safemoney.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	
	public static final Logger logger = Logger.getLogger(GlobalExceptionHandlerController.class);
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
            ModelAndView mv = new ModelAndView("shared/default");
            logger.error(e);
            logger.error(e.getStackTrace());
            return mv;
            
	}

}
