package br.com.sisClinicaPUC.view;

  import javax.faces.application.FacesMessage;
  import javax.faces.bean.ManagedBean;
  import javax.faces.bean.ViewScoped;
  import javax.faces.context.FacesContext;

import br.com.sisClinicaPUC.entidade.Usuario;
import br.com.sisClinicaPUC.persistencia.UsuarioDAO;
   
   
@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {
   
        private UsuarioDAO usuarioDAO = new UsuarioDAO();
        private Usuario usuario = new Usuario();
        
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
        	
			return "home";
        }
        
        public Usuario getUsuario() {
              return usuario;
        }
   
        public void setUsuario(Usuario usuario) {
              this.usuario = usuario;
        }
}