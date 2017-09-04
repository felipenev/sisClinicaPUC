package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import br.com.sisClinicaPUC.vo.SituacaoExameEnum;

@Entity
@NamedQueries({
	  @NamedQuery(name = "exame.EXAME_SOLICITADO_POR_MEDICO", query = "select e from Exame e JOIN FETCH e.tipoExameList where e.medico.id = :idMedico")
})
public class Exame implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public static final String EXAME_SOLICITADO_POR_MEDICO = "exame.EXAME_SOLICITADO_POR_MEDICO";

	public Exame() {}
	
	public Exame(Medico medico) {
		this.setMedico(medico);
		this.setTipoExameList(new HashSet<TipoExame>());
		this.setDataSolicitacao(new Date());
		this.setSituacaoExame(SituacaoExameEnum.AGUARDANDO_RESULTADO);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_exame")
    @SequenceGenerator(name="sequence_exame", sequenceName="sequence_exame", allocationSize=1)
    @Column(name="id_exame", nullable=false, unique=true)
    private Long id;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "exame_tipoexame", joinColumns = { @JoinColumn(name = "id_exame", nullable = false, updatable = false) },
										inverseJoinColumns = { @JoinColumn(name = "id_tipoExame", nullable = false, updatable = false) })
//	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    private Set<TipoExame> tipoExameList;

	@ManyToOne
	private Medico medico;
    
    @ManyToOne
    private Paciente paciente;
    
    private String situacaoExame;

    @Column(name="resultado", length=1000)
    private String resultado;
    
    @Column(name="data_solicitacao")
	private Date dataSolicitacao;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<TipoExame> getTipoExameList() {
		return tipoExameList;
	}

	public void setTipoExameList(Set<TipoExame> tipoExameList) {
		this.tipoExameList = tipoExameList;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public SituacaoExameEnum getSituacaoExame() {
		return SituacaoExameEnum.getValor(this.situacaoExame);
	}

	public void setSituacaoExame(SituacaoExameEnum situacaoExame) {
		this.situacaoExame = situacaoExame.getCodigo();
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getexamesSolicitados() {
		List<String> exaList = new ArrayList<String>();
		for (TipoExame te : this.getTipoExameList()) {
			exaList.add(te.getDescricaoExame());
		}
		String retorno = String.join(", ", exaList);
		
		return retorno;
	}
}