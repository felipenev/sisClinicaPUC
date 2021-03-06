package br.com.sisClinicaPUC.vo;

public enum SituacaoExameEnum {

	AGUARDANDO_RESULTADO("0", "Aguardando resultado do laboratório", "warning"),
	AGUARDANDO_ANALISE_MEDICO("1", "Aguardando análise do médico responsável", "danger"),
	ARQUIVAR_EXAME("2", "Arquivado", "info"),
	ENTREGAR_RESULTADO_PACIENTE("3", "Entregar resultado para paciente", "success");
	
	private SituacaoExameEnum() {}
	
	private SituacaoExameEnum(String codigo, String descricao, String severity) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.severity = severity;
	}
	
// ****************************************************************

	/**
	 * Retorna o objeto através do código.
	 */
	public static SituacaoExameEnum getValor(String valor){
		if(valor == null){
			return null;
		}
		
		for (SituacaoExameEnum obj : values()) {
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
