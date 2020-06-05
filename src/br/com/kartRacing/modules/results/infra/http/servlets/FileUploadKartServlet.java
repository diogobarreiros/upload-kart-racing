package br.com.kartRacing.modules.results.infra.http.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.kartRacing.modules.results.infra.http.controllers.FileUploadKartController;

/**
 * 
 * Class responsible for taking information from the JSP
 * 
 * @author diogo
 *
 */
public class FileUploadKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1 ;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileUploadKartController fileUploadKartController = new FileUploadKartController();
		fileUploadKartController.readTextFileUpload(request, response);
	}
}