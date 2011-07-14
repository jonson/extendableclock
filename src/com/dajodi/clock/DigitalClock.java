package com.dajodi.clock;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.Time;
import android.util.AttributeSet;
import android.widget.TextView;

public class DigitalClock extends BaseLinearLayoutClock {
	
	private final static String m12 = "%l:%M %p";
    private final static String m24 = "%k:%M";
    private TextView clock;
    private String format;
    
    private FormatChangeObserver mFormatChangeObserver;
	
	public DigitalClock(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DigitalClock(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		mFormatChangeObserver = new FormatChangeObserver();
		getContext().getContentResolver().registerContentObserver(
                Settings.System.CONTENT_URI, true, mFormatChangeObserver);
		
		setFormat();
		
		clock = new TextView(context);
		this.addView(clock);
	}

	@Override
	public void onTimeChanged(Time now) {
		clock.setText(now.format(format));
	}
	
	/**
     * Pulls 12/24 mode from system settings
     */
    private boolean get24HourMode() {
        return android.text.format.DateFormat.is24HourFormat(getContext());
    }

    private void setFormat() {
        if (get24HourMode()) {
            format = m24;
        } else {
            format = m12;
        }
    }
	
	private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            setFormat();
        }
    }

}
