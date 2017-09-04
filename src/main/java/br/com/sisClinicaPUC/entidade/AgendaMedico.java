package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import br.com.sisClinicaPUC.vo.SituacaoEnum;

@Entity
@NamedQueries({
	  @NamedQuery(name = "agendaMedico.AGENDA_MEDICO_POR_SITUACAO", query = "select am from AgendaMedico am where am.ativoInaivo = :situacao")
})
public class AgendaMedico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(length = 500)
	public static final String AGENDA_MEDICO_POR_SITUACAO = "agendaMedico.AGENDA_MEDICO_POR_SITUACAO";

	public AgendaMedico(){
	}
	public AgendaMedico(Medico medico) {
		this.setMedico(medico);
		this.setAtivoInaivo(SituacaoEnum.ATIVO);
	}

	@Id
	@GeneratedValue
    @Column(name="id_agenda_medico", nullable=false, unique=true)
    private Long id;

	@Column(name="data", nullable=false)
	private Date data;
	
	@Column(name="horario_inicio_atendimento", nullable=false)
	private Date horarioInicioAtendimento;
	
	@Column(name="horario_fim_atendimento", nullable=false)
	private Date horarioFimAtendimento;

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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getHorarioInicioAtendimento() {
		return horarioInicioAtendimento;
	}
	public void setHorarioInicioAtendimento(Date horarioInicioAtendimento) {
		this.horarioInicioAtendimento = horarioInicioAtendimento;
	}
	public Date getHorarioFimAtendimento() {
		return horarioFimAtendimento;
	}
	public void setHorarioFimAtendimento(Date horarioFimAtendimento) {
		this.horarioFimAtendimento = horarioFimAtendimento;
	}

 }