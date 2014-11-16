package com.mta.chen;
import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Mta_java_chenServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
		
		//question #1
		int Radius = 50;
		float PIE = (float)Math.PI;
		double Area = PIE * Math.pow(Radius, 2);
	
		//question #2
		int OppositeB = 30;
		int hypotenuseAB = 50;
		float Opposite = (float)Math.sin(Math.toRadians(OppositeB)) * hypotenuseAB;

		//question #3
		int A = 20;
		int B = 13;
		float result = (float)Math.pow(A, B);
		
		String line1 = new String("calculation 1: Area of circle with radius " +Radius+ " is: " +Area+ " square cm");
		String line2 = new String("calculation 2: Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: " +Opposite+ " cm");
		String line3 = new String("Power of 20 with exp of 13 is: " +result);
		
		String resultStr = new String(line1 + "<br>" + line2 + "<br>" +line3);
		resp.setContentType("text/html");//הבדל בין פליין לאקסמל הוא שפליין זה כתב קטן השני גדול
		resp.getWriter().println(resultStr);
	}
}
