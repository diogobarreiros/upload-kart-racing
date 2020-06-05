package br.com.kartRacing.modules.results.infra.http.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		String file_name = null;
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
			ArrayList<String> result = new ArrayList<String>();
			if (!it.hasNext()) {
				return;
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				file_name = fileItem.getName();
				result.add(file_name);
			}
			request.setAttribute("result", result);

		    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		    view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}