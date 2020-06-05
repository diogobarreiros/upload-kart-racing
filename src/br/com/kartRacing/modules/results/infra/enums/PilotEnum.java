package br.com.kartRacing.modules.results.infra.enums;

/**
 * 
 * @author diogo
 *
 */
public enum PilotEnum {

	CODE(1), NAME(3);
	
	private final int value;
	
	PilotEnum(int valueField){
    	value = valueField;
    }
	
    public int getValue(){
        return value;
    }
}
