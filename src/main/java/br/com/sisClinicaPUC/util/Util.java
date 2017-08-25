package br.com.sisClinicaPUC.util;

import java.util.Date;

public class Util {

	private static final int TAMANHO_CPF = 11;

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
	
	public static boolean isCPFValido(Integer cpf) {
		if(isIntegerNotNull(cpf)) {
			return String.valueOf(cpf).length() < TAMANHO_CPF;
		}
		return false;
	}
}
