package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sisClinicaPUC.vo.PerfilEnum;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
 
@Entity
public class Usuario implements Serializable{
      
	private static final long serialVersionUID = 1L;

	public Usuario() {}
	
	public Usuario(PerfilEnum perfil) {
		this.setAtivoInativo(SituacaoEnum.ATIVO);
		this.setPerfil(perfil);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_usuario")
    @SequenceGenerator(name="sequence_usuario", sequenceName="sequence_usuario", allocationSize=1, initialValue=1)
	@Column(name="id_usuario", nullable=false, unique=true)
	private int id;
	
	@Column(name="login", nullable=false, unique=false)
	private String login;
	
	@Column(name="senha", nullable=false, unique=false)
	private String senha;
	 
	@Column(name="ultimo_acesso", unique=true)
	@Temporal(TemporalType.DATE)
	private Date ultimoAcesso;
	  
	@Column(name="perfil", nullable=false, unique=false)
	private String perfil;
	
	@Column(name="ativo_inativo", nullable=false)
	private String ativoInativo;
	
	@OneToOne
	private Medico medico;
	
	@OneToOne
	private Recepcionista recepcionista;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
	    return senha;
	}
	
	public void setSenha(String senha) {
	    this.senha = senha;
	}
	
	public Date getUltimoAcesso() {
	    return ultimoAcesso;
	}
	
	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PerfilEnum getPerfil() {
		return PerfilEnum.getValor(this.perfil);
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil.getCodigo();
	}

	public SituacaoEnum getAtivoInativo() {
		return SituacaoEnum.getValor(ativoInativo);
	}

	public void setAtivoInativo(SituacaoEnum ativoInativo) {
		this.ativoInativo = ativoInativo.getCodigo();
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Recepcionista getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}
	
 }