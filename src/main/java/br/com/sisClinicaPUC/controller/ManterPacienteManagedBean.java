package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.sisClinicaPUC.entidade.Paciente;
import br.com.sisClinicaPUC.persistencia.PacienteDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.vo.SituacaoEnum;
   
@Named
@ViewScoped
public class ManterPacienteManagedBean extends AbstractMangedBean<Paciente> implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private PacienteDAO pacienteDAO= new PacienteDAO();
    private Paciente paciente = new Paciente();
    private Paciente pacienteExclusao = new Paciente();
    private List<Paciente> pacienteList = new ArrayList<Paciente>();
    
    private Date dataAtual = new Date();
    
    public ManterPacienteManagedBean() {}

    @PostConstruct
	public void init() {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! inicializei o ManagedBean de Paciente !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	this.setPaciente(new Paciente());
		this.setPacienteExclusao(new Paciente());
		carregarPacienteAtivoList();
	}
    
    public void inserirAlterar() {
    	if(Util.isValueNotBlankOrNotEmpty(this.getPaciente().getId())) {
    		alterar(this.getPaciente());
    	}else {
    		inserir();
    	}
    }
    
    @Override
    public void inserir() {
    	if(validarCampos()) {
    		boolean inclusao = this.getPacienteDAO().inserir(this.getPaciente());
    		if (inclusao) {
    			init();
    			this.tratarMensagemSucesso(null);
    		}
    	}
	}
	
	@Override
	public void alterar(Paciente paciente) {
		if(validarCampos()) {
			boolean alteracao = this.getPacienteDAO().alterar(paciente);
			if (alteracao) {
				init();
				this.tratarMensagemSucesso(null);
			}
		}
		
	}

	@Override
	public void excluir() {
		//Exclusao logica
		this.getPacienteExclusao().setAtivoInaivo(SituacaoEnum.INATIVO);
		boolean exclusao = this.getPacienteDAO().alterar(this.getPacienteExclusao());
		if(exclusao) {
			init();
			this.tratarMensagemSucesso(null);
		}
		
	}
	
	@Override
	public void excluir(Paciente paciente) {}

	public void carregarAlteracao(Paciente pacienteAlterar) {
		this.setPaciente(new Paciente());
		carregarPacienteAtivoList();
		Paciente pacAlterar = Util.cloneSerializable(pacienteAlterar);
		this.setPaciente(pacAlterar);
		//TODO:Verificar quando criar validacao por CPF
	}
	
	/**
	 * Valida os campos obrigatorios
	 */
	public boolean validarCampos() {
		boolean valid = true;

		if(!Util.isStringNotBlankOrNotNull(this.getPaciente().getNome())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isDateNotNull(this.getPaciente().getDataCadastro())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getPaciente().getSexo())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getPaciente().getRG())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(Util.isObjectNotNull(this.getPaciente().getRG()) && this.getPaciente().getRG() < 0) {
			this.tratarMensagemErro(null, "MSG015");
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getPaciente().getCPF())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isCPFValido(this.getPaciente().getCPF())) {
			this.tratarMensagemErro(null, "MSG006");
			valid = false;
		}
		if(Util.isCPFValido(this.getPaciente().getCPF()) && isPacienteCadastrado(this.getPaciente())) {
			this.tratarMensagemErro(null, "MSG017");
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getPaciente().getEndereco())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getPaciente().getBairro())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getPaciente().getCidade())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isStringNotBlankOrNotNull(this.getPaciente().getUF())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isDateNotNull(this.getPaciente().getDataNascimento())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isObjectNotNull(this.getPaciente().getTelefone())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		if(!Util.isTelefoneValido(this.getPaciente().getTelefone())) {
			this.tratarMensagemErro(null);
			valid = false;
		}
		
		return valid;
	}

	/**
	 * Verifca se existe paciente com o cpf
	 * 
	 * @param pac
	 * @return
	 */
	private boolean isPacienteCadastrado(Paciente pac) {
		return this.getPacienteDAO().findByCPFAtivo(pac);
	}

	/**
	 * Carrega os pacientes ativos que podem ser apresentados
	 * 
	 */
	private void carregarPacienteAtivoList() {
		this.setPacienteList(new ArrayList<Paciente>());
		this.getPacienteList().addAll(this.getPacienteDAO().getPacienteAtivoList());
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Paciente getPacienteExclusao() {
		return pacienteExclusao;
	}

	public void setPacienteExclusao(Paciente pacienteExclusao) {
		this.pacienteExclusao = pacienteExclusao;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

}