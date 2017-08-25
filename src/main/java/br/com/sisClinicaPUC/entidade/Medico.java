package br.com.sisClinicaPUC.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import br.com.sisClinicaPUC.vo.SituacaoEnum;


@Entity
@NamedQueries({
	  @NamedQuery(name = "medico.MEDICO_POR_SITUACAO", query = "select m from Medico m where m.ativoInaivo = :situacao")
})
public class Medico extends Pessoa {
      
	private static final long serialVersionUID = 1L;
	
	public static final String MEDICO_POR_SITUACAO = "medico.MEDICO_POR_SITUACAO";

	public Medico() {
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
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