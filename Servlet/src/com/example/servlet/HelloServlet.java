package com.example.servlet;
 
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
public class HelloServlet extends HttpServlet {
    
   private static final long serialVersionUID = 102831973239L;
   private static HashMap<String, String> data = new HashMap<>();
 
    
   public HelloServlet() {
   }
   
   private String buildString() {
	   String out = new String();
	   
	   for(String key : data.keySet()) {
		   out = out.concat("<p>" + key + " -> " + data.get(key));
	   }
	   
	   return out;
   }
 
   @Override
   protected void doGet(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
        
	// Set response content type
	      response.setContentType("text/html");

	      ServletOutputStream out = response.getOutputStream();
	         
	      out.println("<html>" +
	            "<head>" + "</head>" +
	            "<body>" +
	                "<h1> Enter data </h1>" +
	                "<form method='POST'>" +
	               		"<input type = 'text' name = 'key' placeholder='key' />" + "<br>" +
	               		"<input type = 'text' name = 'value' placeholder='value' />" + "<br>" +
	               		"<input type = 'submit'/>" +
	                "</form>" +
	            "</body>" +
	         "</html>"
	      );
	      
	      out.close();
   }
 
   @Override
   protected void doPost(HttpServletRequest request,
           HttpServletResponse response) throws ServletException, IOException {
       //this.doGet(request, response);
       response.setContentType("text/html");
       
       String key = request.getParameter("key");
       String value = request.getParameter("value");
       
       data.put(key, value);
       
       response.getOutputStream().println("<html><body><h1>DATA</h1>" + buildString() + "</body></html>");
       
   }
 
}