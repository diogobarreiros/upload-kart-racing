package br.com.kartRacing.modules.results.infra.http.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.kartRacing.modules.results.services.KartRacingService;

/**
 * 
 * Class responsible for handling information that comes from Servlet
 * 
 * @author diogo
 *
 */
public class FileUploadKartController {

	/**
	 * Method responsible for handling the text file sent
	 * @param request
	 * @param response
	 */
	public void readTextFileUpload(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return;
		}
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List < FileItem > fields = upload.parseRequest(request);
			Iterator < FileItem > it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}
			
			ArrayList<String> resultList = KartRacingService.resultKartRace(it);
			request.setAttribute("result", resultList);

		    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		    view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
