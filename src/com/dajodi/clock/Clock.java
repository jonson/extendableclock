package com.dajodi.clock;

import java.util.TimeZone;

import android.text.format.Time;

/**
 * Represents a clock that has timezone support.
 * 
 * @author jonson
 *
 */
public interface Clock {
	
	public void setTimeZone(TimeZone timeZone);
	
	public void onTimeChanged(Time now);
	
	/**
	 * Hack for multiple inheritance, View is not an interface.
	 * 
	 * @param visibility
	 */
	public void setVisibility(int visibility);
	
}
