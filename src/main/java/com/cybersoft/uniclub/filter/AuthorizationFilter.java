package com.cybersoft.uniclub.filter;

import com.cybersoft.uniclub.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationFilter  extends OncePerRequestFilter {
   @Autowired
   private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            System.out.println("Token: " + token);

            String data = jwtHelper.decodeToken(token);
            System.out.println("Decode Data: " + data);


            if (data!=null && !data.isEmpty()) {
                SecurityContext securityContext = SecurityContextHolder.getContext();

                List<GrantedAuthority> authorities = new ArrayList<>() ;
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(data);
                authorities.add(simpleGrantedAuthority);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("user", "", authorities);
                securityContext.setAuthentication(authentication);

            }
        }
        filterChain.doFilter(request, response);
    }
}
