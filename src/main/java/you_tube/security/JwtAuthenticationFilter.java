package you_tube.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import you_tube.profile.dto.JwtDTO;
import you_tube.utils.JwtUtil;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7).trim();

        try {
            JwtDTO dto = JwtUtil.decode(token);

            String username = dto.getEmail();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredJwtException e) {
            String refreshToken = request.getHeader("Refresh-Token");
//        If the token is invalid, create a new token.
            if (refreshToken != null) {
                try {
                    JwtDTO refreshDto = JwtUtil.decode(refreshToken);

                    if ("refresh".equals(refreshDto.getType())) {
                        String newAccessToken = JwtUtil.encode(refreshDto.getEmail(), refreshDto.getRole());

                        response.setHeader("New-Access-Token", newAccessToken);

                        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshDto.getEmail());
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (JwtException ex) {
                    // Deauthenticate user if refresh token is invalid
                    SecurityContextHolder.clearContext();
                }
            } else {
                // If refresh token does not exist
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
    }
