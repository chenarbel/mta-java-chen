package com.mta.chen;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03_Area_calculation extends HttpServlet{//רספונס ורקווסט הם קלט פלט
	public void doGet(HttpServletRequest req, HttpServletResponse resp) //מחזירה למסך את ההדפסה, ממנה קוראים לפרינט
			throws IOException{
		resp.setContentType("text/html");//הבדל בין פליין לאקסמל הוא שפליין זה כתב קטן השני גדול
		resp.getWriter().println("my ex 03");
	//מיפוי בין קוד לווב
	}
			
}
