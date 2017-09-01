package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Exame implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public Exame() {}
	
	public Exame(Medico medico) {
		this.setMedico(medico);
		this.setExameSolicitadoList(new HashSet<ExameSolicitado>());
		this.setDataSolicitacao(new Date());
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_solicitacao_exame")
    @SequenceGenerator(name="sequence_solicitacao_exame", sequenceName="sequence_solicitacao_exame", allocationSize=1)
    @Column(name="id_solicitacao_exame", nullable=false, unique=true)
    private Long id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    private Set<ExameSolicitado> exameSolicitadoList;

	@ManyToOne
	private Medico medico;
    
    @ManyToOne
    private Paciente paciente;
    
    @Column(name="data_solicitacao")
	private Date dataSolicitacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ExameSolicitado> getExameSolicitadoList() {
		return exameSolicitadoList;
	}

	public void setExameSolicitadoList(Set<ExameSolicitado> exameSolicitadoList) {
		this.exameSolicitadoList = exameSolicitadoList;
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

}