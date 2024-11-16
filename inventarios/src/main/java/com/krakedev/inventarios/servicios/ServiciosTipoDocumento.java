package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.TipoDocumentoBDD;
import com.krakedev.inventarios.entidades.TiposDocumentos;
import com.krakedev.inventarios.excepciones.InventarioException;
@Path("tiposdocumento")
public class ServiciosTipoDocumento {
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar() {
		ArrayList<TiposDocumentos> tdms= new ArrayList<TiposDocumentos>();
		TipoDocumentoBDD tBDD =new TipoDocumentoBDD();
		
		try {
			tdms = tBDD.recuperar();
			return Response.ok(tdms).build();
		} catch (InventarioException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
