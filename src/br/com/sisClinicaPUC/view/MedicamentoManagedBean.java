package br.com.sisClinicaPUC.view;

  import javax.faces.application.FacesMessage;
  import javax.faces.bean.ManagedBean;
  import javax.faces.bean.ViewScoped;
  import javax.faces.context.FacesContext;

import br.com.sisClinicaPUC.entidade.Medicamento;
import br.com.sisClinicaPUC.entidade.Usuario;
import br.com.sisClinicaPUC.persistencia.MedicamentoDAO;
import br.com.sisClinicaPUC.persistencia.UsuarioDAO;
   
   
@ManagedBean(name = "medicamentoMB")
@ViewScoped
public class MedicamentoManagedBean {
   
        private MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        private Medicamento medicamentoBean = new Medicamento();
        
        public MedicamentoManagedBean() {
        	
		}
        
        public String inserir() {
              
              Boolean inclusao = medicamentoDAO.inserir(medicamentoBean);
              if (inclusao) {
            	  medicamentoBean = new Medicamento();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário não encontrado!","Erro no Login!"));
                    return null;
              } else {
                    return "/main";
              }
              
              
        }
   
}