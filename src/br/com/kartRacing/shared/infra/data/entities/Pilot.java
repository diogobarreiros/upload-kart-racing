package br.com.kartRacing.shared.infra.data.entities;

import java.util.ArrayList;

/**
 * 
 * @author diogo
 *
 */
public class Pilot {

	private int code;
	private String name;
	private ArrayList<Lap> laps;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Lap> getLaps() {
		return laps;
	}
	public void setLaps(ArrayList<Lap> laps) {
		this.laps = laps;
	}
}
