package br.com.sisClinicaPUC.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.sisClinicaPUC.vo.SituacaoEnum;


@Entity
@NamedQueries({
	  @NamedQuery(name = "paciente.PACIENTE_POR_SITUACAO", query = "select p from Paciente p where p.ativoInaivo = :situacao")
})
public class Paciente extends Pessoa {
      
	private static final long serialVersionUID = 1L;

	@Column(length = 500)
	public static final String PACIENTE_POR_SITUACAO = "paciente.PACIENTE_POR_SITUACAO";

	public Paciente() {
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
		this.setDataCadastro(new Date());
	}

	@Column(name="data_cadastro", nullable=false, unique=false)
	private Date dataCadastro;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
 }