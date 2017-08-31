package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	  @NamedQuery(name = "receitaMedica.RECEITA_POR_MEDICO", query = "select rm from ReceitaMedica rm JOIN FETCH rm.medicamentoList where rm.medico.id = :idMedico")
})

public class ReceitaMedica implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public static final String RECEITA_POR_MEDICO = "receitaMedica.RECEITA_POR_MEDICO";

	public ReceitaMedica() {
	}
	
	public ReceitaMedica(Medico medico) {
		this.setMedico(medico);
		this.setDataEmissao(new Date());
		this.setMedicamentoList(new HashSet<Medicamento>());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_receitamedica")
    @SequenceGenerator(name="sequence_receitamedica", sequenceName="sequence_receitamedica", allocationSize=1)
    @Column(name="id_receita", nullable=false, unique=true)
    private Long id;
	
    @Column(name="descricao_receita", length=500)
    private String descricaoReceita;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "receitamedica_medicamento", joinColumns = { @JoinColumn(name = "id_receita", nullable = false, updatable = false) },
//										inverseJoinColumns = { @JoinColumn(name = "id_medicamento", nullable = false, updatable = false) })
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    private Set<Medicamento> medicamentoList;

    @ManyToOne
	private Medico medico;
    
    @ManyToOne
    private Paciente paciente;
    
    @Column(name="data_emissao")
	private Date dataEmissao;

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

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getMedicamentosPrescritos() {
		List<String> medList = new ArrayList<String>();
		for (Medicamento med : this.getMedicamentoList()) {
			medList.add(med.getNomeDeFabrica());
		}
		String retorno = String.join(", ", medList);
		
		return retorno;
	}
}