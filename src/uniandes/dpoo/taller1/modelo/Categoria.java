package uniandes.dpoo.taller1.modelo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uniandes.dpoo.taller1.modelo.Libro;

/**
 * Esta clase representa a una categor�a de libros dentro de la librer�a. Cada
 * categor�a tiene un nombre �nico y adem�s sabe si es una categor�a a la que
 * pertenecen libros de ficci�n o no.
 */
public class Categoria
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * Nombre de la categor�a
	 */
	private String nombre;

	/**
	 * Indica si la categor�a corresponde a libros de ficci�n o no
	 */
	private boolean ficcion;

	/**
	 * Lista de libros que hacen parte de la categor�a
	 */
	private ArrayList<Libro> libros;
	/*
	 * TODO Parte 3 - agregar una asociaci�n a la clase Libro llamada libros, que
	 * sea una lista de Libro
	 */

	// ************************************************************************
	// Constructores
	// ************************************************************************

	public Categoria(String nombre, boolean ficcion)
	{
		this.nombre = nombre;
		this.ficcion = ficcion;
		this.libros = new ArrayList<Libro>();
		/*
		 * TODO Parte 3 - inicializa la lista de libros con una lista vac�a. Si esto no
		 * se hace al construir la categor�a, la lista de libros ser�a null y todas las
		 * instrucciones para agregar libros fallar�an.
		 */
	}

	// ************************************************************************
	// M�todos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre de la categor�a
	 * 
	 * @return Categoria
	 */
	public String darNombre()
	{
		return nombre;
	}

	public void cambiarNombre(String nuevoNombre)
	{
		nombre = nuevoNombre;
		
	}
	/**
	 * Consulta si esta es una categor�a de ficci�n o no, con base en el atributo
	 * ficcion.
	 * 
	 * @return ficcion
	 */
	public boolean esFiccion()
	{
		return ficcion;
	}

	/**
	 * Retorna la lista de libros que hacen parte de la categor�a
	 * 
	 * @return libros
	 * 
	 *
	 */
	public ArrayList<Libro> darLibros()
	{
		// cambien el valor de retorno de ArrayList<Libro> a List<Libro>
		//PENDIENTE
		// TODO Parte 3 - completar el m�todo de acuerdo a la documentaci�n
		return libros;
	}

	// ************************************************************************
	// Otros m�todos
	// ************************************************************************

	/**
	 * Agrega un nuevo libro a la categor�a
	 * 
	 * @param nuevoLibro El nuevo libro que se va a agregar.
	 */
	public void agregarLibro(Libro nuevoLibro)
	{
		libros.add(nuevoLibro);
		// LISTO
		// TODO Parte 3 - completar el m�todo de acuerdo a la documentaci�n
	}

	
	
	/**
	 * Cuenta la cantidad de libros en la categor�a
	 * 
	 * @return Cantidad de libros
	 */
	
	public int contarLibrosEnCategoria()
	{
		return libros.size();
		//Set<Libro> numlibros = new HashSet<>();
		//for (Libro book : libros)
		//{
			//if (!numlibros.contains(book))
				//numlibros.add(book);
		//}
		// TODO Parte 3 - completar el m�todo de acuerdo a la documentaci�n
		// En este punto no deber�a tener que hacer ning�n recorrido
		//return numlibros.size();
	}

	/**
	 * Calcula la calificaci�n promedio de los libros que pertenecen a la categor�a
	 * 
	 * @return Calificaci�n promedio
	 */
	public double calificacionPromedio()
	{
		double promedio = 0.0;
		double calificaciones = 0.0;
		int numerocalificaciones = 0;
		while (numerocalificaciones < libros.size())
			{
				calificaciones += libros.get(numerocalificaciones).darCalificacion();
				numerocalificaciones ++;
			}
		promedio = calificaciones/numerocalificaciones;
		
		// TODO Parte 3 - completar el m�todo de acuerdo a la documentaci�n
		// En este punto tendr� que recorrer la lista de libros
		return promedio;
	}

	

	/**
	 * Consulta si en la categor�a hay alg�n libro escrito por el autor indicado.
	 * 
	 * La b�squeda del autor se hace de forma exacta (tiene que ser id�ntico al
	 * valor indicado en el par�metro nombreAutor).
	 * 
	 * @param nombreAutor El nombre del autor para el que se quiere hacer la
	 *                    b�squeda.
	 * @return Retorna true si hay al menos un libro cuyo autor es el autor buscado.
	 *         Retorna false de lo contrario.
	 */
	public boolean hayLibroDeAutor(String nombreAutor)
	{
		boolean exist = false;
		for (Libro book : libros)
			{
				if(book.darAutor().equals(nombreAutor) == true)
				{
					exist = true;
				}
			}
		// TODO Parte 3 - completar el m�todo de acuerdo a la documentaci�n
		return exist;
	}

	
	
	
	/**
	 * Busca en la categor�a los libros escritos por el autor indicado.
	 * 
	 * El nombre del autor puede estar incompleto, y la b�squeda no debe tener en
	 * cuenta may�sculas y min�sculas. Por ejemplo, si se buscara por "ulio v"
	 * deber�an encontrarse los libros donde el autor sea "Julio Verne".
	 * 
	 * @param cadenaAutor La cadena que se usar� para consultar el autor. No
	 *                    necesariamente corresponde al nombre completo de un autor.
	 * @return Una lista con todos los libros cuyo autor coincida con la cadena
	 *         indicada
	 */
	public ArrayList<Libro> buscarLibrosDeAutor(String nombreAutor)
	{
		ArrayList<Libro> librosautor = new ArrayList<Libro>();
		for (Libro li:libros)
		{
			if (li.darAutor().toLowerCase().contains(nombreAutor.toLowerCase()));
			{
				librosautor.add(li);
			}
		
		}
		//PENDIENTE SE PUEDE A�ADIR EL M�TODO EQUALIGNORECASE
		// TODO Parte 3 - completar el m�todo de acuerdo a la documentaci�n
		// Recuerde retornar una lista nueva (no la lista del atributo libros)
		return librosautor;
	}

	// ************************************************************************
	// M�todos sobrecargados
	// ************************************************************************

	/**
	 * Este m�todo permite representar una categor�a como una cadena de caracteres
	 */
	@Override
	public String toString()
	{
		return nombre;
	}

}




