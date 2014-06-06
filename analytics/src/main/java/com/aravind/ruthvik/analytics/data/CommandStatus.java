package com.aravind.ruthvik.analytics.data;

public enum CommandStatus {
	
	FATAL(3),
	SEVERE(2),
	SUCCESS(0),
	WARNING(1),
	;
	
	private int errorCode;
	
	private CommandStatus(int errorCode){
		this.errorCode = errorCode;
	}
	
	public CommandStatus getCommandStatusByErrorCode(int errorCode) {
		for (CommandStatus aStatus : values()) {
			if (aStatus.errorCode == errorCode) {
				return aStatus;
			}
		}
		return null;
	}
	
	public int getErrorCode(){
		return this.errorCode;
	}
}
