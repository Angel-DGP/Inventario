package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductoBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.excepciones.InventarioException;

@Path("productos")
public class ServiciosProducto {
	@Path("buscar/{subCadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subCadena") String subCadena) {
		ArrayList<Producto> prds= new ArrayList<Producto>();
		ProductoBDD pBDD = new ProductoBDD();
		
		try {
			prds = pBDD.obtenerProducto(subCadena);
			return Response.ok(prds).build();
		} catch (InventarioException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearPrv(Producto prd) {
		ProductoBDD pBDD = new ProductoBDD();
		
		try {
			pBDD.crearPrd(prd);;
			return Response.ok().build();
		} catch (InventarioException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
