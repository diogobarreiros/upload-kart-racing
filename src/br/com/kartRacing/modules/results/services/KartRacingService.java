package br.com.kartRacing.modules.results.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;

import br.com.kartRacing.modules.results.infra.data.entities.Result;
import br.com.kartRacing.modules.results.infra.enums.LapEnum;
import br.com.kartRacing.modules.results.infra.enums.PilotEnum;
import br.com.kartRacing.shared.infra.data.entities.Lap;
import br.com.kartRacing.shared.infra.data.entities.Pilot;
import br.com.kartRacing.shared.utils.LocalTimeUtils;

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
	public static ArrayList<Result> resultKartRace(Iterator<FileItem> it){
		ArrayList<Result> resultList = new ArrayList<Result>();
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
				ArrayList<Pilot> pilots = fillPilots(lines, laps);
				resultList = fillResults(pilots);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * Method responsible for filling lap
	 * @param lines
	 * @return List Laps
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
			lap.setLocalTime(LocalTimeUtils.formatTime(lap.getTime()));
			lap.setAverageSpeed(Float.parseFloat(values[LapEnum.AVERAGESPEED.getValue()].replaceAll(",", ".")));
			
			laps.add(lap);
		}

		return laps;
	}
	
	/**
	 * Method responsible for filling pilot
	 * @param lines
	 * @return List Pilots
	 */
	public static ArrayList<Pilot> fillPilots(String[] lines, ArrayList<Lap> laps) {
		ArrayList<Pilot> pilots = new ArrayList<Pilot>();
		boolean pilotExists = false;

		for (int i = 1; i < lines.length; i++) {
			String line = lines[i];

			String[] values = line.split(" ");

			Pilot pilotAdd = new Pilot();
			pilotAdd.setCode(Integer.parseInt(values[PilotEnum.CODE.getValue()]));
			pilotAdd.setName(values[PilotEnum.NAME.getValue()]);
			
			for (Pilot pilot : pilots) {
				if(pilot.getCode() == pilotAdd.getCode())
					pilotExists = true;
			}
			
			if(!pilotExists) {
				ArrayList<Lap> pilotLaps = new ArrayList<Lap>();
				for (Lap lap : laps) {
					if(lap.getPilotCode() == pilotAdd.getCode()) {
						pilotLaps.add(lap);
					}
				}
				pilotLaps.sort((p1, p2) -> p1.getLocalTime().compareTo(p2.getLocalTime()));
				pilotAdd.setLaps(pilotLaps);
				pilots.add(pilotAdd);
			}
		}

		return pilots;
	}
	
	/**
	 * Method responsible for filling result
	 * @param pilots
	 * @return List Result
	 */
	public static ArrayList<Result> fillResults(ArrayList<Pilot> pilots) {
		ArrayList<Result> results = new ArrayList<Result>();
		
		for (Pilot pilot : pilots) {
			Result result = new Result();
			result.setPilotCode(pilot.getCode());
			result.setPilotName(pilot.getName());
			result.setNumberLaps(pilot.getLaps().size());
			
			LocalTime timeSum = null;
			float averageSpeedSum = 0;
			for (Lap lap : pilot.getLaps()) {
				LocalTime localTime = lap.getLocalTime();
				if(timeSum == null) {
					timeSum = localTime;
					result.setBestLap(lap);
				}else
					timeSum = timeSum.plusMinutes(localTime.getMinute())
					                 .plusSeconds(localTime.getSecond())
					                 .plusNanos(localTime.getNano());
				averageSpeedSum += lap.getAverageSpeed();
			}
			result.setAverageSpeedPilot(averageSpeedSum/pilot.getLaps().size());
			result.setTrialLocalTime(timeSum);
			result.setTrialTime(timeSum.format(DateTimeFormatter.ofPattern("mm:ss.nnnnnnn")));
			
			results.add(result);
		}
		
		results.sort((p1, p2) -> p1.getTrialLocalTime().compareTo(p2.getTrialLocalTime()));
		
		for (int i = 0; i < results.size(); i++) {
			Result result = results.get(i);
			result.setPosition(i + 1);
			
			LocalTime timeAfterWinner = results.get(0).getTrialLocalTime();
			LocalTime timeSub = LocalTime.of(
					00,
					result.getTrialLocalTime().minusMinutes(timeAfterWinner.getMinute()).getMinute(),
					result.getTrialLocalTime().minusSeconds(timeAfterWinner.getSecond()).getSecond(),
					result.getTrialLocalTime().minusNanos(timeAfterWinner.getNano()).getNano()
					);
			result.setTimeAfterWinner(timeSub.format(DateTimeFormatter.ofPattern("mm:ss.nnnnnnnnn")));
		}
		
		return results;
	}
}
