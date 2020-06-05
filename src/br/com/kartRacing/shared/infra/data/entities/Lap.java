package br.com.kartRacing.shared.infra.data.entities;

/**
 * 
 * @author diogo
 *
 */
public class Lap {

	private int pilotCode;
	private String hour;
	private int number;
	private String time;
	private float averageSpeed;
	
	public int getPilotCode() {
		return pilotCode;
	}
	public void setPilotCode(int pilotCode) {
		this.pilotCode = pilotCode;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public float getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
}
