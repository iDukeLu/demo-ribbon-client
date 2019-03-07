package com.idukelu.demo.demoribbonclient.config.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest req, Exception e) {
        HashMap<String, Object> msg = new HashMap<String, Object>();
        msg.put("URL", req.getRequestURL());
        msg.put("Exception", e.toString());
        logger.error("Request Path：" + req.getRequestURL(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }
}
