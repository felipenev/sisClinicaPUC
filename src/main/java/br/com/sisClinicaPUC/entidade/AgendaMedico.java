package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.sisClinicaPUC.vo.SituacaoEnum;

@Entity
@NamedQueries({
	  @NamedQuery(name = "agendaMedico.AGENDA_MEDICO_POR_SITUACAO", query = "select am from AgendaMedico am where am.ativoInaivo = :situacao")
})
public class AgendaMedico implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String AGENDA_MEDICO_POR_SITUACAO = "agendaMedico.AGENDA_MEDICO_POR_SITUACAO";

	public AgendaMedico() {
		this.setDataAtendimento(new DataAtendimento());
		this.setMedico(new Medico());
	}

	@Id
	@GeneratedValue
    @Column(name="id_agenda_medico", nullable=false, unique=true)
    private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="agendaMedico", cascade = { CascadeType.REMOVE })
    private DataAtendimento dataAtendimento;

    @OneToOne
	private Medico medico;
    
    @Column(name="ativo_inativo", nullable=false, unique=false)
	private String ativoInaivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DataAtendimento getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(DataAtendimento dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public SituacaoEnum getAtivoInaivo() {
		return SituacaoEnum.getValor(this.ativoInaivo);
	}

	public void setAtivoInaivo(SituacaoEnum ativoInaivo) {
		this.ativoInaivo = ativoInaivo.getCodigo();
	}

 }