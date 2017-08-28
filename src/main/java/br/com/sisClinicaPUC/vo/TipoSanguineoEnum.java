package br.com.sisClinicaPUC.vo;

public enum TipoSanguineoEnum {

	A_POSITIVO("A+", "A+"),
	A_NEGATIVO("A-", "A-"),
	B_POSITIVO("B+", "B+"),
	B_NEGATIVO("B-", "B-"),
	AB_POSITIVO("AB+", "AB+"),
	AB_NEGATIVO("AB-", "AB-"),
	O_POSITIVO("O+", "O+"),
	O_NEGATIVO("O-", "O-");
	
	private TipoSanguineoEnum() {
	}
	
	private TipoSanguineoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
// ****************************************************************

	/**
	 * Retorna o objeto através do código.
	 */
	public static TipoSanguineoEnum getValor(String valor){
		if(valor == null){
			return null;
		}
		
		for (TipoSanguineoEnum obj : values()) {
			if(obj.getCodigo().equals(valor)){
				return obj;
			}
		}
		return null;
	}

// ****************************************************************
	
	private String codigo;
	private String descricao;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
