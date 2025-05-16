package ar.edu.unlam.pb2.entidades;

public class NoFiccion extends Libro{

	private String categoria;
	private Boolean tieneReferenciasBibliograficas;
	
	public NoFiccion(String titulo, String autor, Integer cantidadPaginas, Double precio, Long ISBN, Boolean estaDisponible,String categoria,Boolean tieneReferenciasBibliograficas) {
		super(titulo, autor, cantidadPaginas, precio, ISBN, estaDisponible);
		this.categoria = categoria;
		this.tieneReferenciasBibliograficas = tieneReferenciasBibliograficas;	
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Boolean getTieneReferenciasBibliograficas() {
		return tieneReferenciasBibliograficas;
	}

	public void setTieneReferenciasBibliograficas(Boolean tieneReferenciasBibliograficas) {
		this.tieneReferenciasBibliograficas = tieneReferenciasBibliograficas;
	}
	
	

}
