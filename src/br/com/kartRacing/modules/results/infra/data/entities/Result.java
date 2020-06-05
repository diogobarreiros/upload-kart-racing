package br.com.kartRacing.modules.results.infra.data.entities;

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
}