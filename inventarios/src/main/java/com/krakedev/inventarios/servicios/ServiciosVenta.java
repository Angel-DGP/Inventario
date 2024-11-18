package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.InventarioException;
@Path("ventas")
public class ServiciosVenta {
	@Path("guardar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearPrv(Venta v) {
		VentasBDD vBDD = new VentasBDD();
		
		try {
			vBDD.registrar(v);
			return Response.ok().build();
		} catch (InventarioException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}	
