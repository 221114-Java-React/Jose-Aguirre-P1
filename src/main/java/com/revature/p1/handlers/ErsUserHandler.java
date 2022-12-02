package com.revature.p1.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.models.ErsUser;
import com.revature.p1.models.Role;
import com.revature.p1.services.TokenService;
import com.revature.p1.services.UserService;
import com.revature.p1.utils.custom_exceptions.InvalidAuthException;
import com.revature.p1.utils.custom_exeptions.InvalidUserException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ErsUserHandler {
    private UserService userService;
    private TokenService tokenService;
    private ObjectMapper mapper;
    private final static Logger logger = LoggerFactory.getLogger(ErsUser.class);

    public ErsUserHandler(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    public void UserHandler(UserService userService, TokenService tokenService, ObjectMapper mapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    public void signup(Context ctx) throws IOException {
        NewUserRequest req = mapper.readValue(ctx.req.getInputStream(), NewUserRequest.class);

        try {
            logger.info("Attempting to signup...");

            ErsUser createdUser;

            if (userService.isValidUsername(req.getUserName())) {
                if (!userService.isDuplicateUsername(req.getUserName())) {
                    if (userService.isValidPassword(req.getPassword1())) {
                        if (userService.isSamePassword(req.getPassword1(), req.getPassword2())) {
                            createdUser = userService.signUp(req);
                        } else throw new InvalidUserException("Passwords doe not match");
                    } else throw new InvalidUserException("Password needs to be minimum 8 characters long, and one number");
                } else throw new InvalidUserException("Username is already taken");
            } else throw new InvalidUserException("Username needs to be 8 - 20 characters long");

            ctx.status(201); // CREATED
            ctx.json(createdUser.getUser_id());
            logger.info("Signup attempt successful...");
        } catch (InvalidUserException e) {
            ctx.status(403); // FORBIDDEN
            ctx.json(e);
            logger.info("Signup attempt unsuccessful...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllUsers(Context ctx) {
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");

            Principal principal = tokenService.extractRequesterDetails(token);
            if (principal == null) throw new InvalidAuthException("Invalid token");
            if (!principal.getRole().equals(Role.ADMIN)) throw new InvalidAuthException("You are not authorized to do this");

            List<ErsUser> ersUsers = userService.getAllUsers();
            ctx.json(ersUsers);
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }
    }

    public void getAllUsersByUsername(Context ctx) {
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in");

            Principal principal = tokenService.extractRequesterDetails(token);
            if (principal == null) throw new InvalidAuthException("Invalid token");
            if (!principal.getRole().equals(Role.ADMIN)) throw new InvalidAuthException("You are not authorized to do this");

            String username = ctx.req.getParameter("userName");
            List<ErsUser> ersUsers = userService.getAllUsersByUserName(username);
            ctx.json(ersUsers);
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }
    }
}