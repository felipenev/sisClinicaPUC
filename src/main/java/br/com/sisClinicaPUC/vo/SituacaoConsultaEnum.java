package br.com.sisClinicaPUC.vo;

public enum SituacaoConsultaEnum {

	MARCADA("0", "Marcada", "success"),
	CANCELADA("1", "Cancelada", "danger");
	
	private SituacaoConsultaEnum() {}
	
	private SituacaoConsultaEnum(String codigo, String descricao, String severity) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.severity = severity;
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
	private String severity;
	
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

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
	
}