package bucketlist.server.security;

import bucketlist.server.entities.Client;
import bucketlist.server.respositories.ClientDao;
import bucketlist.server.services.implementation.ClientServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;


import static bucketlist.server.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private ClientServiceImpl clientService;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ClientServiceImpl clientService) {
        this.authenticationManager = authenticationManager;
        this.clientService = clientService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {

            Client credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), Client.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getName(), credentials.getPassword()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(
                        ((User) authResult.getPrincipal()).getUsername()
                )
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+token);

        ////MOZDA////
        Client client = clientService.getOne(authResult.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonClient = objectMapper.writeValueAsString(client);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonClient);
        out.flush();
    }
}
