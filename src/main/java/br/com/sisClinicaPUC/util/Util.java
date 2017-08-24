package br.com.sisClinicaPUC.util;

import java.util.Date;

public class Util {

	public static boolean isStringNotBlankOrNotNull(String str) {
		return str != null && !str.isEmpty();
	}
	
	public static boolean isValueNotBlankOrNotEmpty(Object vlr) {
		return vlr != null && isStringNotBlankOrNotNull(vlr.toString());
	}

	public static boolean isDateNotNull(Date data) {
		return data != null;
	}

	public static boolean isIntegerNotNull(Integer vlr) {
		return vlr != null;
	}
}
