package br.com.sisClinicaPUC.controller;

  import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sisClinicaPUC.entidade.Medico;
import br.com.sisClinicaPUC.entidade.Usuario;
import br.com.sisClinicaPUC.persistencia.UsuarioDAO;
   
   
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginManagedBean implements Serializable{
   
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario = new Usuario();
    
    public LoginManagedBean() {}
        
        public String envia() {
              
              usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
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
        	
        	//TODO:FIX ME - Terminar login
        	Medico med = new Medico();
        	med.setId(2L);
        	med.setNome("medico 1 teste");
        	
        	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("medico", med);
        	
			return "home";
        }
        
        public Usuario getUsuario() {
              return usuario;
        }
   
        public void setUsuario(Usuario usuario) {
              this.usuario = usuario;
        }
}