package com.revature.p1.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.dtos.requests.NewLoginRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.models.ErsUser;
import com.revature.p1.services.TokenService;
import com.revature.p1.services.UserService;
import com.revature.p1.utils.custom_exceptions.InvalidAuthException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthHandlers {
    private final UserService userService;
    private final TokenService tokenService;
    private final ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ErsUser.class);

    public AuthHandlers(UserService userService, TokenService tokenService, ObjectMapper mapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    public void authenticateUser(Context ctx) throws IOException {
        NewLoginRequest req = mapper.readValue(ctx.req.getInputStream(), NewLoginRequest.class);
        logger.info("Attempting to login...");
        try {
            Principal principal = userService.login(req);
            String token = tokenService.generateToken(principal);
            ctx.res.setHeader("authorization", token);
            ctx.json(principal);
            ctx.status(202);
            logger.info("Login successful...");
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e.getMessage());
            logger.info("Login Did Not Work");
        }
    }
}