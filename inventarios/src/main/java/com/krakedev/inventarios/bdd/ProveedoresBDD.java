package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TiposDocumentos;
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
			PS = CON.prepareStatement("select pro.identificador,pro.tipo_documento,td.descripcion,pro.nombre,pro.telefono,pro.correo,pro.direccion from proveedores pro , tipo_documento td where pro.tipo_documento = td.codigo and upper(pro.nombre) like ?;");
			PS.setString(1, "%"+subcadena.toUpperCase()+"%");
			ResultSet RS = PS.executeQuery();
			
			while (RS.next()) {
				p = new Proveedor();
				p.setIdentificador(RS.getString("identificador"));
				p.setTipoDocumento(new TiposDocumentos(RS.getString("tipo_documento"), RS.getString("descripcion")));
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
	public void crearPrv(Proveedor prv) throws InventarioException {
		Connection CON = null;
		try {
			CON = ConexionBDD.obtenerConexion();
			PreparedStatement ps = CON
					.prepareStatement("insert into proveedores (identificador,tipo_documento,nombre,telefono,correo,direccion) values (?,?,?,?,?,?);");
			ps.setString(1, prv.getIdentificador());
			ps.setString(2, prv.getTipoDocumento().getCodigo());
			ps.setString(3, prv.getNombre());
			ps.setString(4, prv.getTelefono());
			ps.setString(5, prv.getCorreo());
			ps.setString(6, prv.getDireccion());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InventarioException("Error al crear un proveedor: " + e.getMessage());
		} catch (InventarioException e) {
			throw e;
		} finally {
			if (CON != null) {
				try {
					CON.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
