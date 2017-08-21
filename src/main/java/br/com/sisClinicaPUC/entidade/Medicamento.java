package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.sisClinicaPUC.vo.SituacaoEnum;
 
@Entity
public class Medicamento implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public Medicamento() {
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
	}
	
	@Id
	@GeneratedValue
    @Column(name="id_medicamento", nullable=false, unique=true)
    private Integer id;
	
    @Column(name="nome_generico", nullable=false, unique=false)
    private String nomeGenerico;
      
	@Column(name="nome_de_fabrica", nullable=false, unique=false)
	private String nomeDeFabrica;
	 
	@Column(name="nome_fabricante", nullable=false, unique=false)
	private String nomeFabricante;
	
	@Column(name="ativo_inativo", nullable=false, unique=false)
	private String ativoInaivo;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeGenerico() {
		return nomeGenerico;
	}

	public void setNomeGenerico(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}

	public String getNomeDeFabrica() {
		return nomeDeFabrica;
	}

	public void setNomeDeFabrica(String nomeDeFabrica) {
		this.nomeDeFabrica = nomeDeFabrica;
	}

	public String getNomeFabricante() {
		return nomeFabricante;
	}

	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}

	public SituacaoEnum getAtivoInaivo() {
		return SituacaoEnum.getValor(this.ativoInaivo);
	}

	public void setAtivoInaivo(SituacaoEnum ativoInaivo) {
		this.ativoInaivo = ativoInaivo.getCodigo();
	}

 }