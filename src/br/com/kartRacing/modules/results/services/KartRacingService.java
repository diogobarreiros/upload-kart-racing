package br.com.kartRacing.modules.results.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;

/**
 * Class responsible for the result of the kart racing
 * 
 * @author diogo
 *
 */
public class KartRacingService {

	/**
	 * Method responsible for returning the result of kart racing
	 * @param it
	 * @return Result List Kart Racing
	 */
	public static ArrayList<String> resultKartRace(Iterator<FileItem> it){
		ArrayList<String> resultList = new ArrayList<String>();
		while (it.hasNext()) {
			FileItem fileItem = it.next();
			String file_name = fileItem.getName();
			resultList.add(file_name);
		}
		return resultList;
	}
}
