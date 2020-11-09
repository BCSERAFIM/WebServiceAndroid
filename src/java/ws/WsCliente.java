package ws;

import entity.Cliente;
import facade.ClienteFacade;
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


@Path("cliente")

public class WsCliente {

    private ClienteFacade clienteFacade = new ClienteFacade();

    @Context
    private UriInfo context;

    public WsCliente() {
    }

    @GET
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Cliente> getlistaProdutoI() {
        List<Cliente> lista = clienteFacade.listaCliente();
        return lista;
    }

    @GET
    @Path("/{cpf}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Cliente getClienteCpf(@PathParam("cpf") String cpf) {
        Cliente cliente = clienteFacade.consultaCpf(cpf);
        return cliente;
    }

    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void inserirCliente(Cliente cliente) {

        clienteFacade.cadastraCliente(cliente);

    }

    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void atualizaCliente(Cliente cliente){
        
        clienteFacade.atualizarCliente(cliente);
    }
    @DELETE
    @Path("/{id}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void deletarCliente(@PathParam("id") Integer id){
        
        clienteFacade.deletarCliente(id);
    }
}
