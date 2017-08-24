package br.com.sisClinicaPUC.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Recepcionista extends Pessoa {
      
	private static final long serialVersionUID = 1L;

	public Recepcionista() {
	}

	@Id
	@GeneratedValue
    @Column(name="id_recepcionista", nullable=false, unique=true)
    private Long id;

	@Column(name="data_admissao", nullable=false, unique=false)
	private Date dataAdmissao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

 }