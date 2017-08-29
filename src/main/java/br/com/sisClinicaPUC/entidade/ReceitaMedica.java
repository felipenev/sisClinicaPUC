package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	  @NamedQuery(name = "receitaMedica.RECEITA_POR_MEDICO", query = "select rm from ReceitaMedica rm where rm.medico.id = :idMedico")
})
public class ReceitaMedica implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public static final String RECEITA_POR_MEDICO = "receitaMedica.RECEITA_POR_MEDICO";

	public ReceitaMedica() {
	}
	
	@Id
	@GeneratedValue
    @Column(name="id_receita", nullable=false, unique=true)
    private Long id;
	
    @Column(name="descricao_receita", nullable=false, unique=false)
    private String descricaoReceita;
      
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy="agendaMedico")
    private Set<Medicamento> medicamentoList;

    @OneToOne
	private Medico medico;
    
    @ManyToOne
    private Paciente paciente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}

	public Set<Medicamento> getMedicamentoList() {
		return medicamentoList;
	}

	public void setMedicamentoList(Set<Medicamento> medicamentoList) {
		this.medicamentoList = medicamentoList;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
  
 }