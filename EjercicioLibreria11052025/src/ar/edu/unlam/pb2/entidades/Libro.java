package ar.edu.unlam.pb2.entidades;


public abstract class Libro implements Comparable<Libro>{

	private String titulo;
	private String autor;
	private Integer cantidadPaginas;
	private Double precio;
	private Long ISBN;
	private Boolean estaDisponible;
	
	public Libro(String titulo,String autor,Integer cantidadPaginas,Double precio, Long ISBN,Boolean estaDisponible) {
		this.titulo = titulo;
		this.autor = autor;
		this.cantidadPaginas = cantidadPaginas;
		this.precio = precio;
		this.ISBN = ISBN;
		this.estaDisponible = estaDisponible;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getCantidadPaginas() {
		return cantidadPaginas;
	}

	public void setCantidadPaginas(Integer cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getISBN() {
		return ISBN;
	}

	public void setISBN(Long iSBN) {
		ISBN = iSBN;
	}
	
	
	
	public Boolean getEstaDisponible() {
		return estaDisponible;
	}


	public void setEstaDisponible(Boolean estaDisponible) {
		this.estaDisponible = estaDisponible;
	}


	@Override
	public int compareTo(Libro o) {
		return this.ISBN.compareTo(o.getISBN());
	}
	
	
	
}
