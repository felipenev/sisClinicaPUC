package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.sisClinicaPUC.vo.SituacaoConsultaEnum;

@Entity
public class Consulta implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public Consulta() {}
	
	public Consulta(Recepcionista recepcionistaResponsavel) {
		this.setRecepcionistaResponsavel(recepcionistaResponsavel);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_consulta")
    @SequenceGenerator(name="sequence_consulta", sequenceName="sequence_consulta", allocationSize=1)
    @Column(name="id_consulta", nullable=false, unique=true)
    private Long id;
	
	@ManyToOne
	private Medico medico;
    
    @ManyToOne
    private Paciente paciente;
    
    @ManyToOne
    private Recepcionista recepcionistaResponsavel;
    
    @Column(name="data_consulta")
	private Date dataConsulta;
    
    private String situacaoConsulta; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Recepcionista getRecepcionistaResponsavel() {
		return recepcionistaResponsavel;
	}

	public void setRecepcionistaResponsavel(Recepcionista recepcionistaResponsavel) {
		this.recepcionistaResponsavel = recepcionistaResponsavel;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public SituacaoConsultaEnum getSituacaoConsulta() {
		return SituacaoConsultaEnum.getValor(situacaoConsulta);
	}

	public void setSituacaoConsulta(SituacaoConsultaEnum situacaoConsulta) {
		this.situacaoConsulta = situacaoConsulta.getCodigo();
	}
 
}