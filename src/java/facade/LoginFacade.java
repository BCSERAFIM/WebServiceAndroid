package facade;


import entity.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;
import dao.LoginDao;
import java.io.Serializable;


@Stateless
public class LoginFacade implements Serializable{

    LoginDao loginDao = new LoginDao();
    Usuario usuario = new Usuario();

    public Usuario verificarLogin(Usuario usuario) {

        try {
            usuario = loginDao.consultarUsuario(usuario.getLogin(), usuario.getSenha());

        } catch (Exception e) {
            String mensagem = e.getMessage();
            System.out.println(mensagem);
        }

        return usuario;
    }

   

}
