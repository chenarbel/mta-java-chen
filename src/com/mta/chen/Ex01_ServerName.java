package com.mta.chen;
	import java.io.IOException;

	import javax.servlet.http.*;

	@SuppressWarnings("serial")
	public class Ex01_ServerName extends HttpServlet
	{
		public void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws IOException {
			
			
			String sentence = "Hello Chen Arbel";
			resp.getWriter().println(sentence);
		}
	}

