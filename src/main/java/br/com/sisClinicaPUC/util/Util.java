package br.com.sisClinicaPUC.util;

import java.text.ParseException;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class Util {

	public static final int TAMANHO_CRM = 6;
	public static final int TAMANHO_CPF = 11;
	public static final int TAMANHO_TELEFONE = 11;
	public static final String MASCARA_CPF = "###.###.###-##";
	public static final String MASCARA_CRM = "####/UU";
	public static final String MASCARA_TELEFONE = "(##)#####-####";

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
	
	public static boolean isCPFValido(String cpf) {
		if(isObjectNotNull(cpf)) {
			return cpf.length() >= TAMANHO_CPF;
		}
		return false;
	}
	
	public static boolean isTelefoneValido(String telefone) {
		if(isObjectNotNull(telefone)) {
			return telefone.length() >= TAMANHO_TELEFONE;
		}
		return false;
	}
	
	public static boolean isCRMValido(String crm) {
		if(isObjectNotNull(crm)) {
			return crm.length() >= TAMANHO_CRM;
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
	public static String removeNoNumbers(String original) {
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
	
	public static String removerCaracteresEspeciais(String value) {
		try {
			if(isStringNotBlankOrNotNull(value)) {
				value = value.replaceAll("[^a-zA-Z0-9\\\\s]", "");
				return value;  
			}
		} catch (Exception e) {  
			throw e;  
		}
		return "";
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
