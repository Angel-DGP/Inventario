package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.InventarioException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {
	public void registrar(Venta v) throws InventarioException {
		Connection CON = null;
		ResultSet clave = null;
		int codigoCabecera = 0;
		BigDecimal totalSinIva = new BigDecimal(0.0);
		BigDecimal iva = new BigDecimal(0.0);
		BigDecimal total = new BigDecimal(0.0);
		
		try {
			CON = ConexionBDD.obtenerConexion();
			PreparedStatement ps2 = null;
			PreparedStatement psA = null;
			PreparedStatement ps = CON.prepareStatement(
					"insert into cabecera_venta(fecha,total_sin_iva,iva,total) values (?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new Date(new java.util.Date().getTime()));
			ps.setBigDecimal(2, totalSinIva);
			ps.setBigDecimal(3, iva);
			ps.setBigDecimal(4, total);
			ps.executeUpdate();
			clave = ps.getGeneratedKeys();

			if (clave.next()) {
				codigoCabecera = clave.getInt(1);
			}
			ArrayList<DetalleVenta> dv = v.getDetalles();
			DetalleVenta deV;
			BigDecimal ivaIterado = new BigDecimal(0.0);
			for (int i =0; i<dv.size();i++) {
				deV = dv.get(i);
				ps2	= CON.prepareStatement(
						"insert into detalles_venta(cabecera_venta,producto,cantidad,precio_venta,subtotal,subtotal_mas_iva) values (?,?,?,?,?,?);");
				ps2.setInt(1, codigoCabecera);
				ps2.setString(2, deV.getProducto().getCodigo());
				ps2.setInt(3, deV.getCantidad());
				ps2.setBigDecimal(4, deV.getProducto().getPrecioVenta());
				BigDecimal subtotal = deV.getProducto().getPrecioVenta().multiply(new BigDecimal(deV.getCantidad()));
				ps2.setBigDecimal(5, subtotal);
				totalSinIva = totalSinIva.add(subtotal);
				if(deV.getProducto().isHasIva()==true) {
					ivaIterado = subtotal.subtract(subtotal.multiply(new BigDecimal(1.12)));
					if(ivaIterado.signum()<0) {
						ivaIterado = ivaIterado.multiply(new BigDecimal(-1));
					}
					iva = iva.add(ivaIterado);
					ps2.setBigDecimal(6, subtotal.multiply(new BigDecimal(1.12)));
				}
				else {
					ps2.setBigDecimal(6, subtotal);
				}
				
				ps2.executeUpdate();
				}
			psA = CON.prepareStatement(
					"update cabecera_venta set total_sin_iva = ?, iva =?,total=?");
			psA.setBigDecimal(1, totalSinIva);
			psA.setBigDecimal(2, iva);
			total = totalSinIva.add(iva);
			psA.setBigDecimal(3, total);
			psA.executeUpdate();
			
			System.out.println("Codigo generado>>" + codigoCabecera);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InventarioException("Error al crear un pedido: " + e.getMessage());
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
