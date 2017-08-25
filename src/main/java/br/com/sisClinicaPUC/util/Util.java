package br.com.sisClinicaPUC.util;

import java.text.ParseException;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class Util {

	private static final int TAMANHO_CPF = 11;
	public static final String MASCARA_CPF = "###.###.###-##";

	public static boolean isStringNotBlankOrNotNull(String str) {
		return str != null && !str.isEmpty() && !"null".equals(str) && !"".equals(str);
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
	
	public static boolean isObjectNotNull(Object vlr) {
		return vlr != null;
	}
	
	public static boolean isCPFValido(Long cpf) {
		if(isObjectNotNull(cpf)) {
			return String.valueOf(cpf).length() >= TAMANHO_CPF;
		}
		return false;
	}
	
	/**
	 * Baseado na String original retorna uma String apenas com os caracteres
	 * numéricos.
	 * 
	 * @param original
	 * @return
	 */
	public static String removerNoNumbers(String original) {
		if (original != null) {
			StringBuffer buf = new StringBuffer();
			int len = original.length();
			char ch;

			for (int i = 0; i < len; i++) {
				ch = original.charAt(i);

				if (Character.isDigit(ch)) {
					buf.append(ch);
				}
			}

			return buf.toString();
		} else {
			return null;
		}
	}

	public static String formatarString(String mask, String value) throws ParseException {  
        try {  
        	if(isStringNotBlankOrNotNull(value)) {
	            MaskFormatter formatter = new MaskFormatter(mask);  
	            formatter.setValueContainsLiteralCharacters(false);  
	            return formatter.valueToString(value);  
        	}
        } catch (ParseException e) {  
            throw e;  
        }
        return "";
    }  
      
    public static String desformatarString(String mask, String value) throws ParseException {  
        try {
        	if(isStringNotBlankOrNotNull(value)) {
        		MaskFormatter unformatter = new MaskFormatter(mask);  
        		unformatter.setValueContainsLiteralCharacters(false);  
        		return unformatter.stringToValue(value).toString();  
        	}
        } catch (ParseException e) {  
            throw e;  
        }
        return "";
    }  
}
