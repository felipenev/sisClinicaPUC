package br.com.sisClinicaPUC.vo;

public enum OperacaoEnum {

	CONSULTAR("1", "consultar"),
	INCLUIR("2", "incluir"),
	ALTERAR("3", "alterar"),
	EXCLUIR("4", "excluir");

	
	private OperacaoEnum() {
	}
	
	private OperacaoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
// ****************************************************************

	/**
	 * Retorna o objeto através do código.
	 */
	public static OperacaoEnum getValor(String valor){
		if(valor == null){
			return null;
		}
		
		for (OperacaoEnum obj : values()) {
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
