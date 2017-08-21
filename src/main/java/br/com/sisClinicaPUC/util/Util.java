package br.com.sisClinicaPUC.util;

public class Util {

	public static boolean isStringNotBlankOrNotNull(String str) {
		return str != null && !str.isEmpty();
	}
	
	public static boolean isValueNotBlankOrNotEmpty(Object vlr) {
		return vlr != null && isStringNotBlankOrNotNull(vlr.toString());
	}
}
