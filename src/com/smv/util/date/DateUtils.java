package com.smv.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

import org.apache.log4j.Logger;

public class DateUtils {
	private static final Logger log = Logger.getLogger(DateUtils.class);
	
	protected static final SimpleDateFormat iso8601DateParser = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	// The Eucalyptus Walrus storage service returns short, non-UTC date time values.
	protected static final SimpleDateFormat iso8601DateParser_Walrus = new SimpleDateFormat(
	"yyyy-MM-dd'T'HH:mm:ss");

	protected static final SimpleDateFormat rfc822DateParser = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

	static {
		iso8601DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
		rfc822DateParser.setTimeZone(new SimpleTimeZone(0, "GMT"));
	}

	public static Date parseIso8601Date(String dateString) throws ParseException {
		ParseException exception = null;
		synchronized (iso8601DateParser) {
			try {
				return iso8601DateParser.parse(dateString);
			} catch (ParseException e) {
				exception = e;
			}
		}
		// Work-around to parse datetime value returned by Walrus
		synchronized (iso8601DateParser_Walrus) {
			try {
				return iso8601DateParser_Walrus.parse(dateString);
			} catch (ParseException e) {
				// Ignore work-around exceptions 
			}
		}
		// Throw original exception if the Walrus work-around doesn't save us.
		throw exception;
	}

	public static String formatIso8601Date(Date date) {
		synchronized (iso8601DateParser) {
			return iso8601DateParser.format(date);
		}
	}

	public static Date parseRfc822Date(String dateString) throws ParseException {
		synchronized (rfc822DateParser) {
			return rfc822DateParser.parse(dateString);
		}
	}

	public static String formatRfc822Date(Date date) {
		synchronized (rfc822DateParser) {
			return rfc822DateParser.format(date);
		}
	}
}
