package br.com.sisClinicaPUC.vo;

public enum TipoPesquisaHistoricoEnum {

	MEDICAMENTOS_PRESCRITOS("1", "Medicamentos prescritos"),
	EXAMES_SOLICITADOS("2", "Exames solicitados"),
	RESULTADOS_OBTIDOS("3", "Resultados obtidos"),
	CONSULTAS("4", "Consultas");

	private TipoPesquisaHistoricoEnum() {}
	
	private TipoPesquisaHistoricoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
// ****************************************************************

	/**
	 * Retorna o objeto através do código.
	 */
	public static TipoPesquisaHistoricoEnum getValor(String valor){
		if(valor == null){
			return null;
		}
		
		for (TipoPesquisaHistoricoEnum obj : values()) {
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
