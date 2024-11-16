package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class Producto {
	private String codigo;
	private String nombre;
	private UnidadesMedida unidadMedida;
	private BigDecimal precioVenta;
	private boolean hasIva;
	private BigDecimal coste;
	private Categoria categoria;
	private int stock;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public UnidadesMedida getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadesMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	public boolean isHasIva() {
		return hasIva;
	}
	public void setHasIva(boolean hasIva) {
		this.hasIva = hasIva;
	}
	public BigDecimal getCoste() {
		return coste;
	}
	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", precioVenta="
				+ precioVenta + ", hasIva=" + hasIva + ", coste=" + coste + ", categoria=" + categoria + ", stock="
				+ stock + "]";
	}
	public Producto(String codigo, String nombre, UnidadesMedida unidadMedida, BigDecimal precioVenta, boolean hasIva,
			BigDecimal coste, Categoria categoria, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidadMedida = unidadMedida;
		this.precioVenta = precioVenta;
		this.hasIva = hasIva;
		this.coste = coste;
		this.categoria = categoria;
		this.stock = stock;
	}
	public Producto() {
		super();
	}
	
}
