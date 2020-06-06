package br.com.kartRacing.modules.results.infra.data.entities;

import java.time.LocalTime;

import br.com.kartRacing.shared.infra.data.entities.Lap;

/**
 * 
 * @author diogo
 *
 */
public class Result {

	private int pilotCode;
	private String pilotName;
	private int position;
	private int numberLaps;
	private String trialTime;
	private LocalTime trialLocalTime;
	private Lap bestLap;
	private float averageSpeedPilot;
	private String timeAfterWinner;
	
	public int getPilotCode() {
		return pilotCode;
	}
	public void setPilotCode(int pilotCode) {
		this.pilotCode = pilotCode;
	}
	public String getPilotName() {
		return pilotName;
	}
	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getNumberLaps() {
		return numberLaps;
	}
	public void setNumberLaps(int numberLaps) {
		this.numberLaps = numberLaps;
	}
	public String getTrialTime() {
		return trialTime;
	}
	public void setTrialTime(String trialTime) {
		this.trialTime = trialTime;
	}
	public LocalTime getTrialLocalTime() {
		return trialLocalTime;
	}
	public void setTrialLocalTime(LocalTime trialLocalTime) {
		this.trialLocalTime = trialLocalTime;
	}
	public Lap getBestLap() {
		return bestLap;
	}
	public void setBestLap(Lap bestLap) {
		this.bestLap = bestLap;
	}
	public float getAverageSpeedPilot() {
		return averageSpeedPilot;
	}
	public void setAverageSpeedPilot(float averageSpeedPilot) {
		this.averageSpeedPilot = averageSpeedPilot;
	}
	public String getTimeAfterWinner() {
		return timeAfterWinner;
	}
	public void setTimeAfterWinner(String timeAfterWinner) {
		this.timeAfterWinner = timeAfterWinner;
	}
}
