package ar.edu.unlam.pb2.contratos;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.pb2.entidades.Libro;
import ar.edu.unlam.pb2.entidades.Prestamo;
import ar.edu.unlam.pb2.entidades.Usuario;
import ar.edu.unlam.pb2.excepciones.LibroNoEncontradoException;
import ar.edu.unlam.pb2.excepciones.LibroRepetidoException;
import ar.edu.unlam.pb2.excepciones.UsuarioNoEncontradoException;
import ar.edu.unlam.pb2.excepciones.UsuarioRepetidoException;

public interface Biblioteca {

	Boolean agregarLibro(Libro libro) throws LibroRepetidoException;
	Boolean agregarUsuario(Usuario usuario) throws UsuarioRepetidoException;
	Libro buscarLibroPorISBN(Long ISBN);
	Usuario buscarUsuarioPorDni(Integer dni);
	Set<Libro> obtenerListaConLibrosFiccion();
	Boolean alquilarLibro(Integer dni, Long ISBN, Integer diasPrestamo) throws LibroNoEncontradoException, UsuarioNoEncontradoException;
	Boolean devolverLibro(Integer dni, Long isbn) throws LibroNoEncontradoException, UsuarioNoEncontradoException;
	List<Prestamo> obtenerLibrosPrestadosPorUsuario(Integer dni);
	Set<Prestamo> obtenerPrestamosAtrasados();
	
	
}
