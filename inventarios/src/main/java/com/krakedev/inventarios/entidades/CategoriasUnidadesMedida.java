package com.krakedev.inventarios.entidades;

public class CategoriasUnidadesMedida {
	private String codigo;
	private String nombre;
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
	@Override
	public String toString() {
		return "CategoriasUnidadesMedida [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	public CategoriasUnidadesMedida(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public CategoriasUnidadesMedida() {
		super();
	}
	
}
