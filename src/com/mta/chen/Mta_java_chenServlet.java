package com.mta.chen;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Mta_java_chenServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		int num1, num2, num3;
		num1 = 3;
		num2 = 4;
		num3 = 7;
		int result = (num1 + num2) * num3;
		resp.getWriter().println("Hello, chen");
	}
}
