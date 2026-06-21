package com.example.App.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

         @Override
        protected boolean shouldNotFilter(HttpServletRequest request) {
             String path = request.getServletPath();

             return path.startsWith("/auth/register")
                     || path.startsWith("/auth/login")
                     || path.startsWith("/auth/refresh") || path.startsWith("/swagger-ui")
                    || path.startsWith("/v3/api-docs");
       }

@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getRequestURI();



    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    String token = authHeader.substring(7);

    if (!jwtUtil.validateToken(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid JWT Token");
        return;
    }

    String email = jwtUtil.extractEmail(token);
    String role = jwtUtil.extractRole(token);

    UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );

    SecurityContextHolder.getContext().setAuthentication(auth);

    filterChain.doFilter(request, response);
}
}
