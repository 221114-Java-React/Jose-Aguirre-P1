package com.revature.p1.utils;

import static io.javalin.apibuilder.ApiBuilder.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.ErsUserDao;
import com.revature.p1.handlers.AuthHandlers;
import com.revature.p1.handlers.ErsUserHandler;
import com.revature.p1.services.TokenService;
import com.revature.p1.services.UserService;
import io.javalin.Javalin;

public class Router {
    public static void router(Javalin app) {
        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        ErsUserDao ers_userDao = new ErsUserDao();
        UserService userService = new UserService(ers_userDao);
        ErsUserHandler ersUserHandler = new ErsUserHandler(userService, tokenService, mapper);
        AuthHandlers authHandlers = new AuthHandlers(userService, tokenService, mapper);

        app.routes(() -> {
            path("/ersUsers", () -> {
                get(ersUserHandler::getAllUsers);
                post(ersUserHandler::signup);
            });

            path("/auth", () -> {
                post(authHandlers::authenticateUser);

            });
        });
    }
}
