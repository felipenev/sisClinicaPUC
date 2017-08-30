package br.com.sisClinicaPUC.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.sisClinicaPUC.vo.SexoEnum;
import br.com.sisClinicaPUC.vo.SituacaoEnum;

@MappedSuperclass
public abstract class Pessoa implements Serializable{
      
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
    @Column(name="id", nullable=false, unique=true)
    private Long id;
	@Column(length = 500)
	private String nome;
	@Column(length = 500)
	private String sexo;
	private String tipoSanguineo;
	private Long RG;
	private String CPF;
	@Column(length = 500)
	private String endereco;
	@Column(length = 500)
	private String bairro;
	@Column(length = 500)
	private String cidade;
	@Column(length = 2)
	private String UF;
	@Column(name="data_nascimento")
	private Date dataNascimento;
	@Column(length = 500)
	private String telefone;
	@Column(name="ativo_inativo")
	private String ativoInaivo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Long getRG() {
		return RG;
	}
	public void setRG(Long RG) {
		this.RG = RG;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public SituacaoEnum getAtivoInaivo() {
		return SituacaoEnum.getValor(this.ativoInaivo);
	}

	public void setAtivoInaivo(SituacaoEnum ativoInaivo) {
		this.ativoInaivo = ativoInaivo.getCodigo();
	}
	
	public SexoEnum getDescricaoSexo(){
		return SexoEnum.getValor(this.sexo);
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	
}