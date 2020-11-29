package com.snow.probmgmt.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyErrorController implements ErrorController{
    
    // private static final Logger log = LoggerFactory.getLogger(MyErrorController.class);

    @GetMapping("/error")
    @ResponseBody
    public Map<String, String> handleError(HttpServletRequest request) {
        /*
        Enumeration<String> names = request.getAttributeNames();
        while (names.hasMoreElements()) {
            log.info(names.nextElement());
        }
        */

        HashMap<String, String> error = new HashMap<>();
        error.put("statusCode", "" + (Integer)request.getAttribute("javax.servlet.error.status_code"));
        error.put("message", (String) request.getAttribute("javax.servlet.error.message"));
        return error;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
