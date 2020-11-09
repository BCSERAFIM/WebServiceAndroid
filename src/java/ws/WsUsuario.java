package ws;

import entity.Produto;
import entity.Usuario;
import facade.ProdutoFacade;
import facade.UsuarioFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("usuario")

public class WsUsuario {

    private UsuarioFacade usuarioFacade = new UsuarioFacade();

    @Context
    private UriInfo context;

    public WsUsuario() {
    }

    @GET
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Usuario> getlistaUsuario() {
        List<Usuario> lista = usuarioFacade.listaUsuario();
        return lista;
    }

    @GET
    @Path("/{login}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Usuario getUsuarioId(@PathParam("login") Integer login) {
        Usuario usuario = usuarioFacade.consultaUsuario(login);
        return usuario;
    }

    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void inserirUsuario(Usuario usuario) {

        usuarioFacade.cadastrarUsuario(usuario);

    }

    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void atualizaUsuario(Usuario usuario){
        
       usuarioFacade.atualizarUsuario(usuario);
    }
    @DELETE
    @Path("/{login}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void deletarProduto(@PathParam("login") Integer login){
        
        usuarioFacade.deletarUsuario(login);
    }
}
