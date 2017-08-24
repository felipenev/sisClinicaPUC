package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DataAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    @Column(name="id_data_atentimento", nullable=false, unique=true)
    private Long id;
	
	@Column(name="data", nullable=false)
	private Date data;
	
	@Column(name="horario_inicio_atendimento", nullable=false)
	private Date horarioInicioAtendimento;
	
	@Column(name="horario_fim_atendimento", nullable=false)
	private Date horarioFimAtendimento;

	@ManyToOne
	@JoinColumn(name="id_agenda_medico", nullable=false)
	private AgendaMedico agendaMedico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHorarioInicioAtendimento() {
		return horarioInicioAtendimento;
	}

	public void setHorarioInicioAtendimento(Date horarioInicioAtendimento) {
		this.horarioInicioAtendimento = horarioInicioAtendimento;
	}

	public Date getHorarioFimAtendimento() {
		return horarioFimAtendimento;
	}

	public void setHorarioFimAtendimento(Date horarioFimAtendimento) {
		this.horarioFimAtendimento = horarioFimAtendimento;
	}

	public AgendaMedico getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}

}
