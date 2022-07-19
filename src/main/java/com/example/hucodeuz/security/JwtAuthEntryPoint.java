package com.example.hucodeuz.security;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author "Husniddin Ulachov"
 * @created 11:36 PM on 7/17/2022
 * @project Edu-Center
 */
@Slf4j
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("Tizimga kirishhga urunish: {} #####");
        response.sendError(401,"Tizimga kirishga urunish malumoti yetarli emas!!!");
        log.info("Tugadi .... #####");

    }
}
