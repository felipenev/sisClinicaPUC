package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;

@Entity
@NamedQueries({
	  @NamedQuery(name = "agendaMedico.AGENDA_MEDICO", query = "select am from AgendaMedico am where am.ativoInaivo = :situacao and am.medico.id = :idMedico and am.data >= :dataAtual"),
	  @NamedQuery(name = "agendaMedico.VERIFICAR_DATA", query = "select am from AgendaMedico am "
	  															+ "where am.ativoInaivo = :situacao "
	  															+ "and am.medico.id = :idMedico "
	  															+ "and am.data = :data "
	  															+ "and ( :horaInicio between am.horarioInicioAtendimento and am.horarioFimAtendimento or :horaFim between am.horarioInicioAtendimento and am.horarioFimAtendimento )")
})
public class AgendaMedico implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String AGENDA_MEDICO = "agendaMedico.AGENDA_MEDICO";
	
	public static final String VERIFICAR_DATA = "agendaMedico.VERIFICAR_DATA";


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

//    @ManyToOne
//	private Medico medico;
	@ManyToOne
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

	public String getApresentacaoCompleta() {
	
		String retornoData = Util.formatoData.format(this.getData());
		String retornoInicioAtendimento = Util.formatoHoraMinuto.format(this.getHorarioInicioAtendimento());
		String retornoFimAtendimento = Util.formatoHoraMinuto.format(this.getHorarioFimAtendimento());
		
		return retornoData + ": " +retornoInicioAtendimento+" - "+retornoFimAtendimento; 
	}
	
 }