package ar.edu.unlam.pb2.entidades;

public class Ficcion extends Libro{

	private String genero;
	private Boolean esParteDeSaga;
	
	
	public Ficcion(String titulo, String autor, Integer cantidadPaginas, Double precio, Long ISBN, Boolean estaDisponible,String genero,Boolean esParteDeSaga) {
		super(titulo, autor, cantidadPaginas, precio, ISBN, estaDisponible);
		this.genero = genero;
		this.esParteDeSaga = esParteDeSaga;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public Boolean getEsParteDeSaga() {
		return esParteDeSaga;
	}


	public void setEsParteDeSaga(Boolean esParteDeSaga) {
		this.esParteDeSaga = esParteDeSaga;
	}
	
	

}
