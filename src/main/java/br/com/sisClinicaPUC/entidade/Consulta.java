package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import br.com.sisClinicaPUC.vo.SituacaoConsultaEnum;

@Entity
@NamedQueries({
	  @NamedQuery(name = "consulta.PACIENTE_POR_MEDICO_DATA_CONSULTA", query = "select c from Consulta c "
	  																			+ "where c.situacaoConsulta = :situacao "
	  																			+ "and c.medico.id = :idMedico "
	  																			+ "and c.agendaMedico.data = :dataConsulta "
	  																			+ "order by c.paciente.nome")
})
public class Consulta implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public static final String PACIENTE_POR_MEDICO_DATA_CONSULTA = "consulta.PACIENTE_POR_MEDICO_DATA_CONSULTA";
	
	public Consulta() {}
	
	public Consulta(Recepcionista recepcionistaResponsavel) {
		this.setRecepcionistaResponsavel(recepcionistaResponsavel);
		this.setSituacaoConsulta(SituacaoConsultaEnum.MARCADA);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_consulta")
    @SequenceGenerator(name="sequence_consulta", sequenceName="sequence_consulta", allocationSize=1)
    @Column(name="id_consulta", nullable=false, unique=true)
    private Long id;
	
	@ManyToOne
	private Medico medico;
	
	@ManyToOne
	private AgendaMedico agendaMedico;
    
    @ManyToOne
    private Paciente paciente;
    
    @ManyToOne
    private Recepcionista recepcionistaResponsavel;
    
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

	public SituacaoConsultaEnum getSituacaoConsulta() {
		return SituacaoConsultaEnum.getValor(situacaoConsulta);
	}

	public void setSituacaoConsulta(SituacaoConsultaEnum situacaoConsulta) {
		this.situacaoConsulta = situacaoConsulta.getCodigo();
	}

	public AgendaMedico getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}
 
	public boolean isSituacaoCancelada() {
		return SituacaoConsultaEnum.CANCELADA.equals(this.getSituacaoConsulta());
		
	}
}