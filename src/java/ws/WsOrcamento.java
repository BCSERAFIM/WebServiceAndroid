package ws;

import entity.Produto;
import facade.ProdutoFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("produto")

public class WsOrcamento {

    private ProdutoFacade produtoFacade = new ProdutoFacade();

    @Context
    private UriInfo context;

    public WsOrcamento() {
    }

    @GET
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Produto> getlistaProdutoI() {
        List<Produto> lista = produtoFacade.listaProduto();
        return lista;
    }

    @GET
    @Path("/{id}")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Produto getProdutoId(@PathParam("id") Integer id) {
        Produto produto = produtoFacade.consultaProdutoId(id);
        return produto;
    }

    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void inserirProduto(Produto produto) {
        
            produtoFacade.cadastrarProduto(produto);
        
    }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        public void putJson
        (String content
        
        
    

        
        
    

) {
    }
}
