package com.krakedev.inventarios.entidades;

public class UnidadesMedida {
	private String codigo;
	private String descripcion;
	private CategoriasUnidadesMedida categoria;
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
	public CategoriasUnidadesMedida getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriasUnidadesMedida categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "UnidadesMedida [codigo=" + codigo + ", descripcion=" + descripcion + ", categoria=" + categoria + "]";
	}
	public UnidadesMedida(String codigo, String descripcion, CategoriasUnidadesMedida categoria) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}
	public UnidadesMedida() {
		super();
	}
	public UnidadesMedida(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	
}
