package br.com.sisClinicaPUC.entidade;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import br.com.sisClinicaPUC.vo.SituacaoEnum;


@Entity
@NamedQueries({
	  @NamedQuery(name = "medico.MEDICO_POR_SITUACAO", query = "select m from Medico m where m.ativoInaivo = :situacao order by m.nome")
})
public class Medico extends Pessoa {
      
	private static final long serialVersionUID = 1L;
	
	@Column(length = 500)
	public static final String MEDICO_POR_SITUACAO = "medico.MEDICO_POR_SITUACAO";

	public Medico() {
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
	}
	
	@Column(name="crm", nullable=false, unique=false)
	private String CRM;

//	@OneToMany
//	private Set<AgendaMedico> agendaMedicoList;
	@Transient
	private Set<AgendaMedico> agendaMedicoList;
	
	public String getCRM() {
		return CRM;
	}

	public void setCRM(String cRM) {
		CRM = cRM;
	}

	public Set<AgendaMedico> getAgendaMedicoList() {
		return agendaMedicoList;
	}

	public void setAgendaMedicoList(Set<AgendaMedico> agendaMedicoList) {
		this.agendaMedicoList = agendaMedicoList;
	}

 }