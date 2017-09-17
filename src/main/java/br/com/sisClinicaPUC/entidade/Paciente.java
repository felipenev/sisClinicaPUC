package br.com.sisClinicaPUC.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import br.com.sisClinicaPUC.vo.SituacaoEnum;


@Entity
@NamedQueries({
	  @NamedQuery(name = "paciente.PACIENTE_POR_SITUACAO", query = "select p from Paciente p where p.ativoInaivo = :situacao order by p.nome")
})
public class Paciente extends Pessoa {
      
	private static final long serialVersionUID = 1L;

	public static final String PACIENTE_POR_SITUACAO = "paciente.PACIENTE_POR_SITUACAO";


	public Paciente() {
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
		this.setDataCadastro(new Date());
	}

	@Column(name="data_cadastro", nullable=false, unique=false)
	private Date dataCadastro;

	@Transient
	private String horarioConsulta;
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	public String getHorarioConsulta() {
		return horarioConsulta;
	}

	public void setHorarioConsulta(String horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}
	
 }