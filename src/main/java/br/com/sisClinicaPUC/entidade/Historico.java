package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import br.com.sisClinicaPUC.vo.OperacaoEnum;
import br.com.sisClinicaPUC.vo.TipoPesquisaHistoricoEnum;

@Entity
@NamedQueries({
	  @NamedQuery(name = "historico.HISTORICO_POR_TIPO", query = "select h from Historico h where h.tipoPesquisaHistoricoEnum = :tipoPesquisaHistoricoEnum")
})
public class Historico implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String HISTORICO_POR_TIPO = "historico.HISTORICO_POR_TIPO";

	public Historico(){
		this.setDataHoraOperacao(new Date());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_historico")
    @SequenceGenerator(name="sequence_historico", sequenceName="sequence_historico", allocationSize=1)
    @Column(name="id_historico", nullable=false, unique=true)
    private Long id;
	
	@Column
	private Date dataHoraOperacao;
	
	@Column 
	private String operacao;
	
	@Column
	private String tipoPesquisaHistoricoEnum;
	
	//Usuario
	@Column
	private Usuario usuario;
	
//	@Column
//	private Medico medico;
//	
//	@Column
//	private Paciente paciente;
//	
//	@Column
//	private Recepcionista recepcionista;
	
	//Exame
	@Column
	private Exame exame;
	
//	@Column
//	private String exameSolicitadoList;//Lista de exame solicitado
	
//	@Column
//	private String situacaoExame;
//	
//	@Column
//	private String resultadoExame;
//	
//	@Column
//	private Date dataSolicitacaoExame;
	
	//ReceitaMedica - medicamento
	@Column
	private ReceitaMedica receitaMedica;
	
//	@Column
//	private String descricaoReceitaMedica;
//	
//	@Column
//	private String medicamentoSolicitadoList;//Lista de medicamento solicitado.
//	
//	@Column(name="data_emissao_receita_medica")
//	private Date dataEmissaoReceitaMedica;

	//Consulta
	@Column
	private Consulta consulta;
	
//	@Column
//	private AgendaMedico agendaMedico;
//	
//	@Column
//	private String situacaoConsulta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHoraOperacao() {
		return dataHoraOperacao;
	}

	public void setDataHoraOperacao(Date dataHoraOperacao) {
		this.dataHoraOperacao = dataHoraOperacao;
	}

	public OperacaoEnum getOperacao() {
		return OperacaoEnum.getValor(operacao);
	}

	public void setOperacao(OperacaoEnum operacao) {
		this.operacao = operacao.getCodigo();
	}
	
	public TipoPesquisaHistoricoEnum getTipoPesquisaHistoricoEnum() {
		return TipoPesquisaHistoricoEnum.getValor(tipoPesquisaHistoricoEnum);
	}

	public void setTipoPesquisaHistoricoEnum(TipoPesquisaHistoricoEnum tipoPesquisaHistoricoEnum) {
		this.tipoPesquisaHistoricoEnum = tipoPesquisaHistoricoEnum.getCodigo();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public ReceitaMedica getReceitaMedica() {
		return receitaMedica;
	}

	public void setReceitaMedica(ReceitaMedica receitaMedica) {
		this.receitaMedica = receitaMedica;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
 }