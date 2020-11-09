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

        } catch (Exception e) {

            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }

    }

    public List<Usuario> listaUsuario() {
        try {
            lista = usuarioDao.listarUsuario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public void atualizarUsuario(Usuario login) {

        login.setSenha(login.getSenha());
        try {
            usuarioDao.atualizarUsuario(login);

        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }
    }

    public void deletarUsuario(Integer login) {
        try {

            usuarioDao.deletarUsuario(login);

        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }
    }

    public Usuario consultaUsuario(Integer login) {
        try {

            Usuario usuario = usuarioDao.consultarUsuario(login);
            return usuario;
           
        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
            return null;

        }
    }

}
