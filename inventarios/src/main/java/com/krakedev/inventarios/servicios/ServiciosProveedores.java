package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.InventarioException;

@Path("proveedores")
public class ServiciosProveedores {
	@Path("buscar/{subCadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subCadena") String subCadena) {
		ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
		ProveedoresBDD pBDD =new ProveedoresBDD();
		
		try {
			proveedores = pBDD.buscar(subCadena	);
			return Response.ok(proveedores).build();
		} catch (InventarioException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
