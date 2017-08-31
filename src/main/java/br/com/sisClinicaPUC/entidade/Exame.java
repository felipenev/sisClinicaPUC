package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.sisClinicaPUC.vo.SituacaoExameEnum;

@Entity
public class Exame implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public Exame() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_exame")
    @SequenceGenerator(name="sequence_exame", sequenceName="sequence_exame", allocationSize=1)
    @Column(name="id_exame", nullable=false, unique=true)
    private Long id;
	
    @ManyToOne
    private TipoExame tipoExame;
    
    private String situacaoExame;

    @Column(name="resultado", length=500)
    private String resultado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public SituacaoExameEnum getSituacaoExame() {
		return SituacaoExameEnum.getValor(this.situacaoExame);
	}

	public void setSituacaoExame(SituacaoExameEnum situacaoExame) {
		this.situacaoExame = situacaoExame.getCodigo();
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}