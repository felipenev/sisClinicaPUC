package br.com.sisClinicaPUC.vo;

public enum SituacaoExameEnum {

	AGUARDANDO_RESULTADO("0", "Aguardando resultado do laboratório"),
	AGUARDANDO_ANALISE_MEDICO("1", "Aguardando análise do médico responsável"),
	AGUARDANDO_RETORNO_PACIENTE("2", "Aguardando retorno do paciente"),
	ENTREGAR_RESULTADO_PACIENTE("3", "Entregar resultado para paciente");
	
	private SituacaoExameEnum() {}
	
	private SituacaoExameEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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
