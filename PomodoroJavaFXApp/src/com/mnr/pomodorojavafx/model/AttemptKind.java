package com.mnr.pomodorojavafx.model;

public enum AttemptKind {
	FOCUS(25 * 60, "Focus time"),
	BREAK(5 * 60, "Break time");
	
	private int mTotalSeconds;
	private String mDisplayName;

	private AttemptKind(int mTotalSeconds, String displayname) {
		this.mTotalSeconds = mTotalSeconds;
		this.mDisplayName = displayname;
	}

	public int getTotalSeconds() {
		return mTotalSeconds;
	}

	public String getDisplayName(){
		return mDisplayName;
	}
	
}
