package com.krakedev.inventarios.entidades;

public class TiposDocumentos {
	private String codigo;
	private String descripcion;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TiposDocumentos() {
		super();
	}
	public TiposDocumentos(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "TiposDocumentos [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
}
