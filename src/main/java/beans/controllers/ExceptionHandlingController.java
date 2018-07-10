package beans.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionHandlingController {

    private final Logger logger = Logger.getLogger(ExceptionHandlingController.class.getName());

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception ex, Model model) {
        logger.log(Level.SEVERE, "Request: " + req.getRequestURL() + " raised " + ex);
        model.addAttribute("exception", ex);
        return "errorPage";
    }

}
