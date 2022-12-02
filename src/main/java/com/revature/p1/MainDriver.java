package com.revature.p1;

import com.revature.p1.utils.ConnectionFactory;
import com.revature.p1.utils.Router;
import io.javalin.Javalin;

import java.sql.SQLException;

public class MainDriver {
    public static void main(String[] args) throws SQLException {
        System.out.println(ConnectionFactory.getInstance().getConnection());
        Javalin app = Javalin.create(c -> {
            c.contextPath = "/p1";
        }).start(7070);

        Router.router(app);
    }
}