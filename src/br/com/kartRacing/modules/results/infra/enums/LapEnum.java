package br.com.kartRacing.modules.results.infra.enums;

public enum LapEnum {

	HOUR(0), NUMBER(4), TIME(5), AVERAGESPEED(6);
	
	private final int value;
	
	LapEnum(int valueField){
    	value = valueField;
    }
	
    public int getValue(){
        return value;
    }
}
