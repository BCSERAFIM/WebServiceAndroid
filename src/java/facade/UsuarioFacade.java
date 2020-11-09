package facade;


import entity.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import dao.UsuarioDao;


public class UsuarioFacade {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private List<Usuario> lista;

    public void cadastrarUsuario(Usuario usuario) {
        usuario.setLogin(usuario.getLogin());
        usuario.setSenha(usuario.getSenha());

        try {

            usuarioDao.inserirUsuario(usuario);
            FacesMessage msg = new FacesMessage("Usuario inserido com Sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    
    public List<Usuario> listaUsuario(){
        try{
            lista = usuarioDao.listarUsuario();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
        return lista;
        
}
    
    public void atualizarUsuario (Usuario login){
        
        login.setSenha(login.getSenha());
        try{
             usuarioDao.atualizarUsuario(login);
        FacesMessage msg = new FacesMessage("Usuario atualizado com sucesso");
        FacesContext.getCurrentInstance().addMessage(null, msg);
 
        }catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
     }
 }
    
    public void deletarUsuario (Usuario login){
        try{
            
            usuarioDao.deletarUsuario(login.getLogin());
            FacesMessage msg = new FacesMessage("Usuario deletado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch (Exception e) {
            String mensagem = e.getMessage();
            FacesMessage msg = new FacesMessage(mensagem);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
    }
    
}
