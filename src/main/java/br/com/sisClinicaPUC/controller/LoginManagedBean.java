package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sisClinicaPUC.entidade.Usuario;
import br.com.sisClinicaPUC.persistencia.UsuarioDAO;
import br.com.sisClinicaPUC.util.Util;
import br.com.sisClinicaPUC.util.ValidacaoException;
import br.com.sisClinicaPUC.vo.PerfilEnum;
   
   
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginManagedBean extends ValidacaoException implements Serializable{
   
	private static final long serialVersionUID = 1L;

	private static final Long LOGIN_TESTE_MEDICO = 1L;
	private static final Long LOGIN_TESTE_RECEPCIONISTA = 10L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario = new Usuario();
    
    public LoginManagedBean() {}
        
        public String envia() {
              
              usuario = usuarioDAO.getUsuario(new Usuario());
              if (usuario == null) {
                    usuario = new Usuario();
                    FacesContext.getCurrentInstance().addMessage(
                               null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                           "Erro no Login!"));
                    return null;
              } else {
                    return "/main";
              }
              
        }
   
        public String irTelaPrincipal() {

        	try {
        		Usuario usrLogin = new Usuario(); 
        		usrLogin = usuarioDAO.getUsuario(this.getUsuario());
				if(Util.isObjectNotNull(usrLogin)) {
					
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usrLogin);
					
					return "home";
				}else {
					throw new Exception();
				}
        		
			} catch (Exception e) {
				this.tratarMensagemErro(null, "MSG998");
				return null;
			}
        	
//        	HttpSession session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(). //request.getSession();
        	
//        	ExternalContext contextoExterno = FacesContext.getCurrentInstance().getExternalContext();
//        	Map<String, Object> mapaSessao = contextoExterno.getSessionMap();
//        	Usuario usuario = (Usuario) mapaSessao.get("usuario");
        	
        	//TODO:FIX ME - Terminar login
//        	Medico med = new Medico();
//        	med.setId(LOGIN_TESTE_MEDICO);
//        	med.setNome("medico 1 teste");
        	
        	
//        	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("medico", med);
        	
//        	Recepcionista recep = new Recepcionista();
//        	recep.setId(LOGIN_TESTE_RECEPCIONISTA);
//        	recep.setNome("recepcionista 1");
//        	
//        	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("recepcionista", recep);
//        	
//			return "home";
        }

        public String sair() {
        	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        	
        	return "/index.xml?faces-redirect=true";
        }
        
        
        /**
         * Verifica se o perfil e administrador
         * 
         * @return
         */
        public boolean isUsuarioAdmin() {
        	return PerfilEnum.ADMINISTRADOR.equals(this.getUsuarioSessao().getPerfil());
        }
        /**
         * Verifica se o perfil e Medico
         * 
         * @return
         */
        public boolean isUsuarioMedico() {
        	return PerfilEnum.MEDICO.equals(this.getUsuarioSessao().getPerfil());
        }
        /**
         * Verifica se o perfil e Recepcionista
         * 
         * @return
         */
        public boolean isUsuarioRecepcionista() {
        	return PerfilEnum.RECEPCIONISTA.equals(this.getUsuarioSessao().getPerfil());
        }
        
        /**
         * Recupera o usuario da sessao
         * 
         * @return
         */
        private Usuario getUsuarioSessao() {
        	return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		}

		public Usuario getUsuario() {
              return usuario;
        }
   
        public void setUsuario(Usuario usuario) {
              this.usuario = usuario;
        }
}