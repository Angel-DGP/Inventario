package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.InventarioException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	public ArrayList<Proveedor> buscar(String subcadena) throws InventarioException {
		ArrayList<Proveedor> pvs = new ArrayList<Proveedor>();
		Connection CON = null;
		PreparedStatement PS = null;
		Proveedor p = null;
		try {
			CON = ConexionBDD.obtenerConexion();
			PS = CON.prepareStatement("select identificador,tipo_documento,nombre,telefono,correo,direccion from proveedores where upper(nombre) like ?;");
			PS.setString(1, "%"+subcadena.toUpperCase()+"%");
			ResultSet RS = PS.executeQuery();
			
			while (RS.next()) {
				p = new Proveedor();
				p.setIdentificador(RS.getString("identificador"));
				p.setTipoDocumento((RS.getString("tipo_documento")));
				p.setNombre((RS.getString("nombre")));
				p.setTelefono((RS.getString("telefono")));
				p.setCorreo((RS.getString("correo")));
				p.setDireccion((RS.getString("direccion")));

				pvs.add(p);
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

		return pvs;
	}
}
