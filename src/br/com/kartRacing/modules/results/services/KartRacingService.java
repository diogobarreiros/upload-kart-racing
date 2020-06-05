package br.com.kartRacing.modules.results.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;

import br.com.kartRacing.modules.results.infra.enums.PilotEnum;

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
		try {
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				String textFile = fileItem.getString("UTF-8");
				textFile = textFile.replaceAll("\r", "");
				
				String[] lines = textFile.split("\n");
				if(!lines[0].equals("Hora Piloto No Volta Tempo Volta Velocidade média da volta")) {
					throw new Exception("Arquivo inválido");
				}
				for (int i = 1; i < lines.length; i++) {
					String line = lines[i];
					
					String[] values = line.split(" ");
					resultList.add(values[PilotEnum.CODE.getValue()]);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
