package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetalleVenta {
	private int codigo;
	private Venta cabeceraVenta;
	private Producto producto;
	private int cantidad;
	private BigDecimal precioVenta;
	private BigDecimal subtotal;
	private BigDecimal subtotalMasIva;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Venta getCabeceraVenta() {
		return cabeceraVenta;
	}
	public void setCabeceraVenta(Venta cabeceraVenta) {
		this.cabeceraVenta = cabeceraVenta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getSubtotalMasIva() {
		return subtotalMasIva;
	}
	public void setSubtotalMasIva(BigDecimal subtotalMasIva) {
		this.subtotalMasIva = subtotalMasIva;
	}
	public DetalleVenta(int codigo, Venta cabeceraVenta, Producto producto, int cantidad,
			BigDecimal precioVenta, BigDecimal subtotal, BigDecimal subtotalMasIva) {
		super();
		this.codigo = codigo;
		this.cabeceraVenta = cabeceraVenta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.subtotal = subtotal;
		this.subtotalMasIva = subtotalMasIva;
	}
	@Override
	public String toString() {
		return "DetalleVenta [codigo=" + codigo + ", cabeceraVenta=" + cabeceraVenta + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", subtotal=" + subtotal
				+ ", subtotalMasIva=" + subtotalMasIva + "]";
	}
	public DetalleVenta() {
		super();
	}
	
}
