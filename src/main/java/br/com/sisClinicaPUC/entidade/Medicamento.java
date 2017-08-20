package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
public class Medicamento implements Serializable{
      
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    @Column(name="id_medicamento", nullable=false, unique=true)
    private int id;
	
    @Column(name="nome_generico", nullable=false, unique=false)
    private String nomeGenerico;
      
	@Column(name="nome_de_fabrica", nullable=false, unique=false)
	private String nomeDeFabrica;
	 
	@Column(name="nome_fabricante", nullable=false, unique=false)
	private String nomeFabricante;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

 }