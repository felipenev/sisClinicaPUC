package br.com.sisClinicaPUC.vo;

public enum SituacaoEnum {

	ATIVO("0", "Ativo"),
	INATIVO("1", "Inativo");

	
	private SituacaoEnum() {
	}
	
	private SituacaoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
// ****************************************************************

	/**
	 * Retorna o objeto através do código.
	 */
	public static SituacaoEnum getValor(String valor){
		if(valor == null){
			return null;
		}
		
		for (SituacaoEnum obj : values()) {
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
