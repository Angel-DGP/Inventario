package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.TiposDocumentos;
import com.krakedev.inventarios.excepciones.InventarioException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class TipoDocumentoBDD {
	public ArrayList<TiposDocumentos> recuperar() throws InventarioException {
		ArrayList<TiposDocumentos> tds = new ArrayList<TiposDocumentos>();
		Connection CON = null;
		PreparedStatement PS = null;
		TiposDocumentos tD = null;
		try {
			CON = ConexionBDD.obtenerConexion();	
			PS = CON.prepareStatement("select codigo,descripcion from tipo_documento;");
			ResultSet RS = PS.executeQuery();
			
			while (RS.next()) {
				tD = new TiposDocumentos();
				tD.setCodigo(RS.getString("codigo"));
				tD.setDescripcion(RS.getString("descripcion"));
				tds.add(tD);
			}

		} catch (InventarioException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InventarioException("Error al consultar: " + e.getMessage());
		} finally {
			if (CON != null) {
				try {
					CON.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return tds;
	}
}	
