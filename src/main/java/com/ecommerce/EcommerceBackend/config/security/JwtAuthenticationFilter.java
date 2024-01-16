package com.ecommerce.EcommerceBackend.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    Logger logger1 = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        logger1.info("message {}",requestToken);

        String username= null;
        String jwtToken = null;

        if(requestToken != null && requestToken.trim().startsWith("Bearer")){
            jwtToken = requestToken.substring(7);
            try{
                username = helper.getUserName(jwtToken);
            } catch (ExpiredJwtException e){
                logger1.info("Invalid token message {}","Jwt token expired");
            } catch (MalformedJwtException e){
                logger1.info("Invalid token message {}","Invalid Jwt token");
            } catch (IllegalArgumentException e){
                logger1.info("Invalid token message {}","Unable to get token");
            }

            if( username != null && SecurityContextHolder.getContext().getAuthentication() != null){
                //validate
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if(helper.validateToken(jwtToken,userDetails)){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }else {
                    logger1.info("not valid message {}","Invalid jwt token");
                }
            }
        }
        else {
            logger1.info("User message {}","username is null or auth is already there");
        }
        filterChain.doFilter(request,response);
    }
}
