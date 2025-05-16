package ar.edu.unlam.pb2.entidades;

import java.time.LocalDate;

public class Prestamo {

	private Libro libro;
	private Usuario usuario;
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private Integer diasPrestamo;
	
	public Prestamo(Libro libro, Usuario usuario,LocalDate fechaPrestamo,LocalDate fechaDevolucion,Integer diasPrestamo) {
		this.libro = libro;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.diasPrestamo = diasPrestamo;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Integer getDiasPrestamo() {
		return diasPrestamo;
	}

	public void setDiasPrestamo(Integer diasPrestamo) {
		this.diasPrestamo = diasPrestamo;
	}
	
	
	
}
