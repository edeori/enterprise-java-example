package com.example.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.examle.interfaces.SingletonConcurrentTimerI;
import com.example.enums.TimerActionEnum;

public class SingletonConcurrentTimerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private SingletonConcurrentTimerI singletonConcurrentTimer;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject responseObject = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        responseObject.put("count", singletonConcurrentTimer.getCounter());

        try {
            PrintWriter out = response.getWriter();
            out.println(responseObject.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject responseObject = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");

        System.out.println(request.getParameter("action"));

        TimerActionEnum action = TimerActionEnum.fromValue(request.getParameter("action"));

        if (action == TimerActionEnum.RESET_COUNTER) {
            singletonConcurrentTimer.resetCounter();
            responseObject.put("count", singletonConcurrentTimer.getCounter());
        } else if (action == TimerActionEnum.INCREMENT_COUNTER) {
            singletonConcurrentTimer.incrementCounter();
            responseObject.put("count", singletonConcurrentTimer.getCounter());
        }

        PrintWriter out = response.getWriter();
        out.println(responseObject.toJSONString());
    }

}
