package br.com.sisClinicaPUC.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Medico extends Pessoa {
      
	private static final long serialVersionUID = 1L;

	public Medico() {
	}

	@Id
	@GeneratedValue
    @Column(name="id_medico", nullable=false, unique=true)
    private Long id;

	@Column(name="crm", nullable=false, unique=false)
	private String CRM;

	@OneToOne
	private AgendaMedico agendaMedico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCRM() {
		return CRM;
	}

	public void setCRM(String cRM) {
		CRM = cRM;
	}

	public AgendaMedico getAgendaMedico() {
		return agendaMedico;
	}

	public void setAgendaMedico(AgendaMedico agendaMedico) {
		this.agendaMedico = agendaMedico;
	}
	
 }