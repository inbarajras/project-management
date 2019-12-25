package com.erp.pma.conntoller;

import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController{
	
	
	
	@GetMapping("/error")
	public String handleErrorPages(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(Objects.nonNull(status)) {
			Integer value =Integer.valueOf(status.toString());
			if(value==HttpStatus.FORBIDDEN.value()) {
				return "errorpages/error-403";
			}
		}
		return "errorpages/errors.html";
		
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
