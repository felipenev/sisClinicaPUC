package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DataAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    @Column(name="id_data_atentimento", nullable=false, unique=true)
    private Long id;
	
	@Column(name="data_atendimento", nullable=false)
	private Date data;
	
	@Column(name="horario_inicio_atendimento", nullable=false)
	private Integer horarioInicioAtendimento;
	
	@Column(name="horario_fim_atendimento", nullable=false)
	private Integer horarioFimAtendimento;

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

	public Integer getHorarioInicioAtendimento() {
		return horarioInicioAtendimento;
	}

	public void setHorarioInicioAtendimento(Integer horarioInicioAtendimento) {
		this.horarioInicioAtendimento = horarioInicioAtendimento;
	}

	public Integer getHorarioFimAtendimento() {
		return horarioFimAtendimento;
	}

	public void setHorarioFimAtendimento(Integer horarioFimAtendimento) {
		this.horarioFimAtendimento = horarioFimAtendimento;
	}
	
}
