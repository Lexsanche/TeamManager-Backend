package com.jnz.teamManager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnz.teamManager.exception.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@Component
public class ExceptionFilterHandler extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper mapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            var err = new HashMap<>();
            err.put("status", HttpStatus.FORBIDDEN.value());
            err.put("message", "JWT invalido");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            mapper.writeValue(response.getWriter(), err);

        }catch (MalformedJwtException e){
            var err = new HashMap<>();
            err.put("status", HttpStatus.BAD_REQUEST.value());
            err.put("message", "Malformed JWT token");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(), err);

        }catch (RuntimeException e){
            var err = new HashMap<>();
            err.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            err.put("message", "Unknown server error ");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            mapper.writeValue(response.getWriter(), err);
        }
    }
}
