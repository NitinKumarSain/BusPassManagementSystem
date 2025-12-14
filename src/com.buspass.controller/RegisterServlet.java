package com.buspass.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buspass.dao.UserDAO;
import com.buspass.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Form se data lena
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        // 2️⃣ Model object banana
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(password);

        // 3️⃣ DAO call
        int status = UserDAO.registerUser(user);

        // 4️⃣ Response
        if (status > 0) {
            response.sendRedirect("login.html");
        } else {
            response.getWriter().println("Registration Failed");
        }
    }
}
