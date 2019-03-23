package ca.gb.sf.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException  {
    	
    	System.out.println("onAuthenticationSuccess");
    	
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        System.out.println(roles);
        
        if (roles.contains("ROLE_EDUCATOR")) {

            response.sendRedirect("/educatorPage");

            return;
            
        }

    	// RequestDispatcher dd = request.getRequestDispatcher("/exerciseList");
        // dd.forward(request, response);
        response.sendRedirect("/exerciseList");
        
        // super..onAuthenticationSuccess(request, response, authentication);
    }	
	
}
