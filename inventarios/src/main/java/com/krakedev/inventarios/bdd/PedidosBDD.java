package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.excepciones.InventarioException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar(Pedido pd) throws InventarioException {
		Connection CON = null;
		ResultSet clave = null;
		int codigoCabecera = 0;
		try {
			CON = ConexionBDD.obtenerConexion();
			PreparedStatement ps = CON.prepareStatement(
					"insert into cabecera_pedido (proveedor,fecha,estado) values (?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2 = null;
			ps.setString(1, pd.getProveedor().getIdentificador());
			ps.setDate(2, new Date(new java.util.Date().getTime()));
			ps.setString(3, "S");
			ps.executeUpdate();
			clave = ps.getGeneratedKeys();

			if (clave.next()) {
				codigoCabecera = clave.getInt(1);
			}
			ArrayList<DetallePedido> dp = pd.getDetalles();
			DetallePedido deP;
			for (int i =0; i<dp.size();i++) {
				deP = dp.get(i);
				ps2	= CON.prepareStatement(
						"insert into detalle_pedido (cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida)\r\n"
						+ "values (?,?,?,?,?);");
				ps2.setInt(1, codigoCabecera);
				ps2.setString(2, deP.getProducto().getCodigo());
				ps2.setInt(3, deP.getCantidadSolicitada());
				ps2.setBigDecimal(4, deP.getProducto().getPrecioVenta().multiply(new BigDecimal(deP.getCantidadSolicitada())));
				ps2.setInt(5, deP.getCantidadRecibida());
				ps2.executeUpdate();
				}
			
			System.out.println("Codigo generado>>" + codigoCabecera);
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
