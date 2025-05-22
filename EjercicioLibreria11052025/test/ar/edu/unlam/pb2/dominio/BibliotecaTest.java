package ar.edu.unlam.pb2.dominio;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.pb2.entidades.*;
import ar.edu.unlam.pb2.excepciones.LibroNoEncontradoException;
import ar.edu.unlam.pb2.excepciones.LibroRepetidoException;
import ar.edu.unlam.pb2.excepciones.UsuarioNoEncontradoException;
import ar.edu.unlam.pb2.excepciones.UsuarioRepetidoException;

public class BibliotecaTest {

	public BibliotecaImpl biblioteca;
	
	@Before
	public void init() {
		this.biblioteca = new BibliotecaImpl();
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaCuandoAgregoUnLibroDeFiccionObtengoUnLibroDeFiccion() throws LibroRepetidoException {
		Libro libroFiccion = new Ficcion("La Metamorfosis", "Franz Kafka", 100, 18500.0, 987654L, true,"Novela", false);
		Boolean agregarLibroFiccion = biblioteca.agregarLibro(libroFiccion);
		assertTrue(agregarLibroFiccion);	
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaCuandoAgregoUnLibroDeNoFiccionObtengoUnLibroDeFiccion() throws LibroRepetidoException {
		Libro libroNoFiccion = new NoFiccion("Meditaciones", "Marco Aurelio", 320, 20000.0, 921758L, true,"Filosofia", false);
		Boolean agregarLibroFiccion = biblioteca.agregarLibro(libroNoFiccion);
		assertTrue(agregarLibroFiccion);
	}
	
	@Test(expected = LibroRepetidoException.class)
	public void dadoQueTengoUnaBibliotecaNoPuedoTener2LibrosConElMismoISBN() throws LibroRepetidoException {
		Libro libroFiccion = new Ficcion("El Tunel", "Ernesto Sabato", 190, 15750.0, 987123L, true,"Novela", false);
		Libro libroNoFiccion = new NoFiccion("Sobre la Brevedad de la vida", "Seneca", 160, 21500.0, 987123L, true,"Filosofia", false);
		
		biblioteca.agregarLibro(libroFiccion);
		biblioteca.agregarLibro(libroNoFiccion);
	}
	
	
	@Test
	public void dadoQueTengoUnaBibliotecaCuandoAgregoUnUsuarioObtengoUnUsuario() throws UsuarioRepetidoException {
		Usuario usuario = new Usuario(1234, "Jorge");
		Boolean agregarUsuario = biblioteca.agregarUsuario(usuario);
		assertTrue(agregarUsuario);
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaCuandoBuscoUnLibroPorSuISBNObtengoElMismoLibro() throws LibroRepetidoException {
		Libro libro = new Ficcion("El Aleph", "Jorge Luis Borges", 200, 20000.0, 912345L, true,"Novela", false);
		biblioteca.agregarLibro(libro);
		Libro buscarLibro = biblioteca.buscarLibroPorISBN(libro.getISBN());
		assertEquals(libro.getISBN(), buscarLibro.getISBN());
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaCuandoBuscoUnUsuarioPorDniLoEncuentro() throws UsuarioRepetidoException {
		Usuario usuario = new Usuario(98765, "Daniel");
		biblioteca.agregarUsuario(usuario);
		Usuario buscarUsuario = biblioteca.buscarUsuarioPorDni(usuario.getDni());
		assertEquals(usuario, buscarUsuario);
	}
	
	
	@Test
	public void dadoQueTengoUnaBibliotecaCuandoAgrego3LibrosObtengoUnaListaConLos3Libros() throws LibroRepetidoException {
		Libro libro1 = new Ficcion("El Aleph", "Jorge Luis Borges", 200, 20000.0, 912345L, true,"Novela", false);
		Libro libro2 = new Ficcion("La Metamorfosis", "Franz Kafka", 100, 18500.0, 987654L, true, "Novela", false);
		Libro libro3 = new Ficcion("El Extranjero", "Albert Camus", 210, 24500.0, 987650L, true, "Novela", false);
		Libro libro4 = new NoFiccion("Sobre la Brevedad de la vida", "Seneca", 160, 21500.0, 987123L, true, "Filosofia", false);
		
		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarLibro(libro3);
		biblioteca.agregarLibro(libro4);
		
		Set<Libro> obtenerLibros = biblioteca.obtenerListaConLibrosFiccion();
		Integer cantidadLibrosEsperados = 3;
		assertEquals(cantidadLibrosEsperados, obtenerLibros.size(),0.0);
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaPuedoAlquilarLibros() throws LibroRepetidoException, LibroNoEncontradoException, UsuarioNoEncontradoException, UsuarioRepetidoException {
		Libro libro1 = new Ficcion("El Aleph", "Jorge Luis Borges", 200, 20000.0, 912345L, true, "Novela", false);
		Libro libro2 = new NoFiccion("Sobre la Brevedad de la vida", "Seneca", 160, 21500.0, 987123L, true, "Filosofia", false);
		Usuario usuario1 = new Usuario(12345, "Martina");
		Usuario usuario2 = new Usuario(23468, "Facundo");
		
		
		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarUsuario(usuario1);
		biblioteca.agregarUsuario(usuario2);
		
		Boolean alquilarLibro1 = biblioteca.alquilarLibro(usuario1.getDni(), libro1.getISBN(), null);
		Boolean alquilarLibro2 = biblioteca.alquilarLibro(usuario2.getDni(), libro2.getISBN(), null);
		
		assertTrue(alquilarLibro1);
		assertTrue(alquilarLibro2);
	}
	
	@Test (expected = LibroNoEncontradoException.class)
	public void dadoQueTengoUnaBibliotecaNoPuedoAlquilarElMismoLibro2Veces() throws LibroRepetidoException, UsuarioRepetidoException, LibroNoEncontradoException, UsuarioNoEncontradoException {
		Libro libro = new Ficcion("Crimen y Castigo", "Fiodor Dostoevsky", 700, 35000.0, 91264L, true, "Novela", false);
		Usuario usuario = new Usuario(765432, "Franco");
		
		biblioteca.agregarLibro(libro);
		biblioteca.agregarUsuario(usuario);
		
		biblioteca.alquilarLibro(usuario.getDni(), libro.getISBN(), null);
		biblioteca.alquilarLibro(usuario.getDni(), libro.getISBN(), null);
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaPuedoDevolverUnLibroQueAlquile() throws LibroRepetidoException, UsuarioRepetidoException, LibroNoEncontradoException, UsuarioNoEncontradoException {
		Libro libroFiccion = new Ficcion("El Idiota", "Fiodor Dostoevsky", 620, 26500.0, 909873L, true, "Novela", false);
		Libro libroNoFiccion = new NoFiccion("Mitos Nordicos", "Neil Gaiman", 300, 23000.0, 897342L, true, "Mitologia", false);
		Usuario usuario1 = new Usuario(12345, "Martina");
		Usuario usuario2 = new Usuario(23468, "Facundo");
		
		biblioteca.agregarLibro(libroFiccion);
		biblioteca.agregarLibro(libroNoFiccion);
		biblioteca.agregarUsuario(usuario1);
		biblioteca.agregarUsuario(usuario2);
		
		biblioteca.alquilarLibro(usuario1.getDni(), libroFiccion.getISBN(), null);
		biblioteca.alquilarLibro(usuario2.getDni(), libroNoFiccion.getISBN(), null);
		
		Boolean devolverLibroFiccion = biblioteca.devolverLibro(usuario1.getDni(), libroFiccion.getISBN());
		Boolean devolverLibroNoFiccion = biblioteca.devolverLibro(usuario2.getDni(), libroNoFiccion.getISBN());
		
		assertTrue(devolverLibroFiccion);
		assertTrue(devolverLibroNoFiccion);
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaPuedoObtenerLosLibrosPrestadosPorUnUsuario() throws LibroRepetidoException, UsuarioRepetidoException, LibroNoEncontradoException, UsuarioNoEncontradoException {
		Libro libro1 = new Ficcion("El Aleph", "Jorge Luis Borges", 200, 20000.0, 912345L, true, "Novela", false);
		Libro libro2 = new NoFiccion("Sobre la Brevedad de la vida", "Seneca", 160, 21500.0, 987123L, true, "Filosofia", false);
		Libro libro3 = new Ficcion("El Idiota", "Fiodor Dostoevsky", 620, 26500.0, 909873L, true, "Novela", false);
		Libro libro4 = new NoFiccion("El arte de amar", "Erich Fromm", 230, 24000.0, 978322L, true, "Psicologia", false);
		Libro libro5 = new Ficcion("La Nausea", "Jean-Paul Sastre", 290, 20000.0, 991257L, true, "Filosofia", false);
		Usuario usuario1 = new Usuario(87921, "Marcos");
		Usuario usuario2 = new Usuario(79810, "Luna");
		
		
		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarLibro(libro3);
		biblioteca.agregarLibro(libro4);
		biblioteca.agregarLibro(libro5);
		biblioteca.agregarUsuario(usuario1);
		biblioteca.agregarUsuario(usuario2);
		biblioteca.alquilarLibro(usuario1.getDni(), libro1.getISBN(), null);
		biblioteca.alquilarLibro(usuario1.getDni(), libro3.getISBN(), null);
		biblioteca.alquilarLibro(usuario1.getDni(), libro5.getISBN(), null);
		biblioteca.alquilarLibro(usuario2.getDni(), libro2.getISBN(), null);
		biblioteca.alquilarLibro(usuario2.getDni(), libro4.getISBN(), null);
		
		List<Prestamo> obtenerLibrosUsuario1 = biblioteca.obtenerLibrosPrestadosPorUsuario(usuario1.getDni());
		List<Prestamo> obtenerLibrosUsuario2 = biblioteca.obtenerLibrosPrestadosPorUsuario(usuario2.getDni());
		
		Integer librosUsuario1Esperados = 3;
		Integer librosUsuario2Esperados = 2;
		
		assertEquals(librosUsuario1Esperados, obtenerLibrosUsuario1.size(),0.0);
		assertEquals(librosUsuario2Esperados, obtenerLibrosUsuario2.size(),0.0);
	}
	
	@Test
	public void dadoQueTengoUnaBibliotecaPuedoObtenerLosPrestamosAtrasados() throws LibroRepetidoException, UsuarioRepetidoException, LibroNoEncontradoException, UsuarioNoEncontradoException {
		Libro libro1 = new Ficcion("Ficiones", "Jorge Luis Borges", 200, 23000.0, 912345L, true, "Cuentos", false);
		Libro libro2 = new NoFiccion("Asi hablo Zaratustra", "Friedrich Nietzsche", 160, 24500.0, 922123L, true, "Filosofia", false);
		Usuario usuario1 = new Usuario(87921, "Carlos");
		Usuario usuario2 = new Usuario(79810, "Sofia");
		
		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarUsuario(usuario1);
		biblioteca.agregarUsuario(usuario2);
		biblioteca.alquilarLibro(usuario1.getDni(), libro1.getISBN(),30, LocalDate.of(2025, 04, 20));
		biblioteca.alquilarLibro(usuario2.getDni(), libro2.getISBN(),15,LocalDate.of(2025, 05, 01));
		
		Set<Prestamo> obtenerPrestamos = biblioteca.obtenerPrestamosAtrasados();		
		Integer prestamosEsperados = 2;
		
		assertEquals(prestamosEsperados, obtenerPrestamos.size(),0.0);
		
	}
	
	
}
