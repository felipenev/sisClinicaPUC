package br.com.sisClinicaPUC.vo;

public enum SituacaoConsultaEnum {

	MARCADA("0", "Consulta marcada"),
	EM_ATENDIMENTO("1", "Em atendimento"),
	FINALIZADA("2", "Finalizada");
	

	
	private SituacaoConsultaEnum() {
	}
	
	private SituacaoConsultaEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
// ****************************************************************

	/**
	 * Retorna o objeto através do código.
	 */
	public static SituacaoConsultaEnum getValor(String valor){
		if(valor == null){
			return null;
		}
		
		for (SituacaoConsultaEnum obj : values()) {
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
