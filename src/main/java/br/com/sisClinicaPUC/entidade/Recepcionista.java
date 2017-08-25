package br.com.sisClinicaPUC.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.sisClinicaPUC.vo.SituacaoEnum;


@Entity
@NamedQueries({
	  @NamedQuery(name = "recepcionista.RECEPCIONISTA_POR_SITUACAO", query = "select r from Recepcionista r where r.ativoInaivo = :situacao")
})
public class Recepcionista extends Pessoa {
      
	private static final long serialVersionUID = 1L;

	public static final String RECEPCIONISTA_POR_SITUACAO = "recepcionista.RECEPCIONISTA_POR_SITUACAO";

	public Recepcionista() {
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
	}

	@Column(name="data_admissao", nullable=false, unique=false)
	private Date dataAdmissao;
	
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

 }