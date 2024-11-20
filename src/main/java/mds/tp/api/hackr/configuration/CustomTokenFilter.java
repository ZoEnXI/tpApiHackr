package mds.tp.api.hackr.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

public class CustomTokenFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public CustomTokenFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //Si login ou register pas de verif
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/register") || requestURI.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);

        //Si pas de Token 401
        if (token == null) {
            logger.warn("No token found, blocking request...");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token found");
            return;
        }

        logger.info("Token is present, validating...");

        // Validation du token, si KO 401
        if (!validateToken(token)) {
            logger.warn("Invalid token, blocking request...");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) throws JsonProcessingException {

        String clientId = this.objectMapper.readTree(this.getTokenPayload(token)).get("sub").asText();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (isRoleAdmin(authorities)) {
            return clientId.equals("clientIdTpApiAdmin");
        } else if (isRoleUser(authorities)) {
            return clientId.equals("clientIdTpApiStandard");
        } else {
            return false;
        }
    }

    private boolean isRoleAdmin(Collection<? extends GrantedAuthority> a) {
        return a.stream().anyMatch(r -> r.getAuthority().equals("ROLE_admin"));
    }

    private boolean isRoleUser(Collection<? extends GrantedAuthority> a) {
        return a.stream().anyMatch(r -> r.getAuthority().equals("ROLE_user"));
    }

    private String getTokenPayload(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        return new String(decoder.decode(chunks[1]));
    }
}
