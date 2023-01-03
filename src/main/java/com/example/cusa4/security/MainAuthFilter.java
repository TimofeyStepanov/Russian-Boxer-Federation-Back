package com.example.cusa4.security;

import com.example.cusa4.models.User;
import com.example.cusa4.security.models.OurAuthToken;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MainAuthFilter implements Filter {

    private static final String TOKEN_HEADER = "x-access-token";

    protected final AuthenticationFailureHandler failureHandler;

    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    private List<RequestMatcher> requireAuthMatcher;

    public MainAuthFilter(
            AuthenticationFailureHandler failureHandler,
            JdbcTemplate jdbcTemplate,
            PasswordEncoder passwordEncoder) {
        this.failureHandler = failureHandler;
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public MainAuthFilter setRequireAuthMatcher(List<RequestMatcher> requireAuthMatcher) {
        this.requireAuthMatcher = requireAuthMatcher;
        return this;
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        if (requireAuth((HttpServletRequest) req)) {
            OurAuthToken token = tryAuth((HttpServletRequest) req, (HttpServletResponse) res);
            if (token == null) {
                failureHandler.onAuthenticationFailure(
                        (HttpServletRequest) req,
                        (HttpServletResponse) res,
                        new AuthenticationServiceException("Invalid user name or password")
                );
            } else {
                SecurityContextHolder.getContext().setAuthentication(token);
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    @Nullable
    protected OurAuthToken tryAuth(HttpServletRequest req, HttpServletResponse res) {
        String token = req.getHeader(TOKEN_HEADER);

        List<User> users = jdbcTemplate.query(
                "select * from userInfo where token = ?",
                new BeanPropertyRowMapper<>(User.class),
                token
        );
        if (users.size() == 0) {
            return null;
        }

        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("BASE_USER")
        );

        User user = users.get(0);
        return new OurAuthToken(user.getId(), user, authorities);
    }


    private boolean requireAuth(HttpServletRequest req) {
        return requireAuthMatcher.stream().anyMatch(requestMatcher -> requestMatcher.matches(req));
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}