package com.mta.chen;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03_Area_calculation extends HttpServlet{//������ ������� �� ��� ���
	public void doGet(HttpServletRequest req, HttpServletResponse resp) //������ ���� �� ������, ���� ������ ������
			throws IOException{
		resp.setContentType("text/html");//���� ��� ����� ������ ��� ������ �� ��� ��� ���� ����
		resp.getWriter().println("my ex 03");
	//����� ��� ��� ����
	}
			
}
