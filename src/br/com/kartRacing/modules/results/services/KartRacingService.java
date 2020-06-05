package br.com.kartRacing.modules.results.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;

import br.com.kartRacing.modules.results.infra.enums.LapEnum;
import br.com.kartRacing.modules.results.infra.enums.PilotEnum;
import br.com.kartRacing.shared.infra.data.entities.Lap;

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
				
				ArrayList<Lap> laps = fillLaps(lines);
				
				for (int i = 0; i < laps.size(); i++) {
					resultList.add(laps.get(i).getHour());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * Method responsible for filling lap
	 * @param lines
	 * @return
	 */
	public static ArrayList<Lap> fillLaps(String[] lines) {
		ArrayList<Lap> laps = new ArrayList<Lap>();

		for (int i = 1; i < lines.length; i++) {
			String line = lines[i];

			String[] values = line.split(" ");

			Lap lap = new Lap();
			lap.setPilotCode(Integer.parseInt(values[PilotEnum.CODE.getValue()]));
			lap.setHour(values[LapEnum.HOUR.getValue()]);
			lap.setNumber(Integer.parseInt(values[LapEnum.NUMBER.getValue()]));
			lap.setTime(values[LapEnum.TIME.getValue()]);
			
			laps.add(lap);
		}

		return laps;
	}
}
