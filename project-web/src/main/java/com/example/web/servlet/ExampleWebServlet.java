package com.example.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
/*
 * This is mapped by the web.xml
 * The same as if annotated with @WebServlet(urlPatterns = "/servlet")
 */
public class ExampleWebServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    /*
     * Accessible when deployed:
     * http://localhost:8080/ExampleWAR/servlet?name=Marci
     */
    private void process(HttpServletRequest request, HttpServletResponse response) {

        JSONObject responseObject = new JSONObject();
        String name = (String) request.getParameter("name");
        responseObject.put("message", "Hello " + name + " from servlet!");

        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println(responseObject.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
