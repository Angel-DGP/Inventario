package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadesMedida;
import com.krakedev.inventarios.excepciones.InventarioException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductoBDD {
	public ArrayList<Producto> obtenerProducto(String subcadena) throws InventarioException {
		ArrayList<Producto> prds = new ArrayList<Producto>();
		Connection CON = null;
		PreparedStatement PS = null;
		Producto p = null;
		Categoria c = null;
		UnidadesMedida udm = null;
		try {
			CON = ConexionBDD.obtenerConexion();
			PS = CON.prepareStatement("select p.codigo_producto, p.nombre as nombre_producto, udm.codigo_udm as nombre_udm, udm.descripcion as descripcion_udm, cast(p.precio_venta as decimal(6,2)), p.tiene_iva, cast(p.coste as decimal(6,2)), p.categoria_fk as codigo_categoria, cat.nombre as nombre_categoria, p.stock from producto p, unidades_medida udm, categorias cat where p.udm = udm.codigo_udm and p.categoria_fk = cat.codigo_cat and upper(p.nombre) like ?;");
			PS.setString(1, "%"+subcadena.toUpperCase()+"%");
			ResultSet RS = PS.executeQuery();
			
			while (RS.next()) {
				c = new Categoria(RS.getInt("codigo_categoria"),RS.getString("nombre_categoria"));
				udm = new UnidadesMedida(RS.getString("nombre_udm"), RS.getString("descripcion_udm"));
				p = new Producto(RS.getString("codigo_producto"), RS.getString("nombre_producto"), udm, RS.getBigDecimal("precio_venta"), RS.getBoolean("tiene_iva"), RS.getBigDecimal("coste"), c, RS.getInt("Stock"));
				prds.add(p);
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

		return prds;
	}
	public void crearPrd(Producto prd) throws InventarioException {
		Connection CON = null;
		try {
			CON = ConexionBDD.obtenerConexion();
			PreparedStatement ps = CON.prepareStatement("insert into producto(codigo_producto,nombre,udm,precio_venta,tiene_iva,coste,categoria_fk,stock) values (?,?,?,?,?,?,?,?);");
			ps.setString(1, prd.getCodigo());
			ps.setString(2, prd.getNombre());
			ps.setString(3, prd.getUnidadMedida().getCodigo());
			ps.setBigDecimal(4, prd.getPrecioVenta());
			ps.setBoolean(5, prd.isHasIva());
			ps.setBigDecimal(6, prd.getCoste());
			ps.setInt(7, prd.getCategoria().getCodigo());
			ps.setInt(8, prd.getStock());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InventarioException("Error al crear un producto: " + e.getMessage());
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
