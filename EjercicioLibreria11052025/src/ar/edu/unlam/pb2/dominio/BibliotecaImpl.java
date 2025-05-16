package ar.edu.unlam.pb2.dominio;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unlam.pb2.contratos.Biblioteca;
import ar.edu.unlam.pb2.entidades.Ficcion;
import ar.edu.unlam.pb2.entidades.Libro;
import ar.edu.unlam.pb2.entidades.Prestamo;
import ar.edu.unlam.pb2.entidades.Usuario;
import ar.edu.unlam.pb2.excepciones.LibroNoEncontradoException;
import ar.edu.unlam.pb2.excepciones.LibroRepetidoException;
import ar.edu.unlam.pb2.excepciones.UsuarioNoEncontradoException;
import ar.edu.unlam.pb2.excepciones.UsuarioRepetidoException;

public class BibliotecaImpl implements Biblioteca {

	Set<Libro> libros;
	Set<Usuario> usuarios;
	List<Prestamo> prestamos;
	
	public BibliotecaImpl() {
		this.libros = new TreeSet<>();
		this.usuarios = new TreeSet<>();
		this.prestamos = new ArrayList<>();
	}
	
	
	@Override
	public Boolean agregarLibro(Libro libro) throws LibroRepetidoException {
		
		if(libros.contains(libro)) {
			throw new LibroRepetidoException("Libro repetido");
		}
		
		return libros.add(libro);
	}


	@Override
	public Boolean agregarUsuario(Usuario usuario) throws UsuarioRepetidoException {
		
		if(usuarios.contains(usuario)) {
			throw new UsuarioRepetidoException("Usuario repetido");
		}
		
		return usuarios.add(usuario);
	}


	@Override
	public Libro buscarLibroPorISBN(Long ISBN) {
		
		for(Libro l : this.libros) {
			if(l.getISBN().equals(ISBN)){
				return l;
			} 
		}
		
		return null;
	}
	
	@Override
	public Usuario buscarUsuarioPorDni(Integer dni) {
		
		for(Usuario u : this.usuarios) {
			if(u.getDni().equals(dni)) {
				return u;
			}
		}
		
		return null;
	}
	


	public Set<Libro> obtenerListaConLibrosFiccion() {
		Set<Libro> librosFiccion = new TreeSet<>();
		
		
		for(Libro l : this.libros) {
			if(l instanceof Ficcion) {
				librosFiccion.add(l);
			}
		}
		
		
		return librosFiccion;
	}


	@Override
	public Boolean alquilarLibro(Integer dni, Long ISBN) throws LibroNoEncontradoException, UsuarioNoEncontradoException {
		
		//TODO: Las fechas deberían ser dinámicas (LocalDate.now() + días).
		// Podrías validar si el usuario ya tiene ese libro prestado o un límite de libros, si decidís agregar esa regla
		//Los nombres de las excepciones podrían ser más precisos (el libro puede existir pero no estar disponible, y eso no es exactamente “no encontrado”).
		
		Libro libro = buscarLibroPorISBN(ISBN);
		Usuario usuario = buscarUsuarioPorDni(dni);
		
		if(libro == null) {
			throw new LibroNoEncontradoException("Libro no encontrado");
		}
		
		if(libro.getEstaDisponible() == false) {
			throw new LibroNoEncontradoException("Libro no disponible");
		}
		
		if(usuario == null) {
			throw new UsuarioNoEncontradoException("Usuario no encontrado");
		}
		
		Prestamo nuevoPrestamo = new Prestamo(libro, usuario, LocalDate.of(2025, 05, 12), LocalDate.of(2025, 06, 12),30);
		libro.setEstaDisponible(false);
		return prestamos.add(nuevoPrestamo);
	}


	@Override
	public Boolean devolverLibro(Integer dni, Long ISBN) throws LibroNoEncontradoException, UsuarioNoEncontradoException {
		
		Libro libro = buscarLibroPorISBN(ISBN);
		Usuario usuario = buscarUsuarioPorDni(dni);
		
		if(libro == null) {
			throw new LibroNoEncontradoException("Libro no encontrado");
		}
		
		if(usuario == null) {
			throw new UsuarioNoEncontradoException("Usuario no encontrado");
		}
		
		
		for(Prestamo p : this.prestamos) {
			if(p.getUsuario().equals(usuario) && p.getLibro().equals(libro)) {
				libro.setEstaDisponible(true);
				prestamos.remove(p);
				return true;
			}
		}
		
		return false;
	}


	@Override
	public List<Prestamo> obtenerLibrosPrestadosPorUsuario(Integer dni) {
		List<Prestamo> librosPrestados = new ArrayList<>();
		
		for(Prestamo p : this.prestamos) {
			if(p.getUsuario().getDni().equals(dni)) {
				librosPrestados.add(p);
			}
		}
		
		return librosPrestados;
	}

	


	
	
	
	
}
