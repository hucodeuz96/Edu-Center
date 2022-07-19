package com.example.hucodeuz.security;

import com.example.hucodeuz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author "Husniddin Ulachov"
 * @created 11:58 AM on 7/17/2022
 * @project Edu-Center
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.substring(7);
            if (jwtProvider.isExpiredToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = authService.loadUserByUsername(username);
                //tizimga kim kirganini set qilib qoyadi
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
            } else {
                System.out.println("Vaqti tugagan");
                logger.error("vaqti tugagan!");
            }
            if (jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = authService.loadUserByUsername(username);
                //tizimga kim kirganini set qilib qoyadi
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
            } else {
                System.out.println("JWT token emas!");
                 logger.error("JWT token emas!");
            }

        }
        doFilter(request, response, filterChain);

    }

}
