package uniandes.dpoo.taller1.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Esta clase agrupa toda la información de una librería: las categorías que se
 * usan para clasificar los libros, y del catálogo de libros.
 * 
 * Adicionalmente esta clase es capaz de calcular y hacer búsquedas sobre las
 * categorías y sobre el catálogo de libros.
 */
public class Libreria
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El arreglo con las categorías que hay en la librería
	 */
	/*
	 * TODO Parte 2 - agregar una asociación a la clase Categoria llamada
	 * categorias, que sea un arreglo de Categoria
	 */
	
	public Categoria[] categorias;
	

	/**
	 * Una lista con los libros disponibles en la librería
	 */
	 
	 /* TODO Parte 4 - agregar una asociación a la clase Libro llamada catalogos, que
	 * sea una lista de libros
	 */
	public ArrayList<Libro> catalogo;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye una nueva librería a partir de la información de los parámetros y
	 * de la información contenida en los archivos.
	 * 
	 * @param nombreArchivoCategorias El nombre del archivo CSV que tiene la
	 *                                información sobre las categorías de libros
	 * @param nombreArchivoLibros     El nombre del archivo CSV que tiene la
	 *                                información sobre los libros
	 * @throws IOException Lanza esta excepción si hay algún problema leyendo un
	 *                     archivo
	 */
	public Libreria(String nombreArchivoCategorias, String nombreArchivoLibros) throws IOException
	{
		this.categorias = cargarCategorias(nombreArchivoCategorias);
		// TODO Parte 2 - después de crear el atributo categoria, quite el comentario
		// sobre la línea anterior

		this.catalogo = cargarCatalogo(nombreArchivoLibros);
		// TODO Parte 4 - después de crear el atributo catalogo, quite el comentario
		// sobre la línea anterior
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Retorna las categorías de la librería
	 * 
	 * @return categorias
	 */
	public Categoria[] darCategorias()
	{
		// TODO Parte 2 - completar el método de acuerdo a la documentación
		
		return categorias;
	}

	/**
	 * Retorna el catálogo completo de libros de la librería
	 * 
	 * @return catalogo
	 */
	public ArrayList<Libro> darLibros()
	{
		//PENDIENTE
		// TODO Parte 4 - completar el m�todo de acuerdo a la documentaci�n
		return catalogo;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Carga la información sobre las categorías disponibles a partir de un archivo
	 * 
	 * @param nombreArchivoCategorias El nombre del archivo CSV que contiene la
	 *                                información de las categorías
	 * @return Un arreglo con las categorías que se encontraron en el archivo
	 * @throws IOException Se lanza esta excepción si hay algún problema leyendo del
	 *                     archivo
	 */
	private Categoria[] cargarCategorias(String nombreArchivoCategorias) throws IOException
	{
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

		BufferedReader br = new BufferedReader(new FileReader(nombreArchivoCategorias));
		String linea = br.readLine(); // Ignorar la primera línea porque tiene los títulos

		linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.trim().split(",");
			String nombreCat = partes[0];
			boolean esFiccion = partes[1].equals("true");

			// Crear una nueva categoría y agregarla a la lista
			listaCategorias.add(new Categoria(nombreCat, esFiccion));

			linea = br.readLine();
		}

		br.close();

		// Convertir la lista de categorías a un arreglo
		Categoria[] arregloCategorias = new Categoria[listaCategorias.size()];
		for (int i = 0; i < listaCategorias.size(); i++)
		{
			arregloCategorias[i] = listaCategorias.get(i);
		}

		return arregloCategorias;
	}

	/**
	 * Carga la información sobre los libros disponibles en la librería.
	 * 
	 * Se deben haber cargado antes las categorías e inicializado el atributo
	 * 'categorias'.
	 * 
	 * @param nombreArchivoLibros El nombre del archivo CSV que contiene la
	 *                            información de los libros
	 * @return Una lista con los libros que se cargaron a partir del archivo
	 * @throws IOException Se lanza esta excepción si hay algún problema leyendo del
	 *                     archivo
	 */
	private ArrayList<Libro> cargarCatalogo(String nombreArchivoLibros) throws IOException
	{
		ArrayList<Libro> libros = new ArrayList<Libro>();

		BufferedReader br = new BufferedReader(new FileReader(nombreArchivoLibros));
		String linea = br.readLine(); // Ignorar la primera línea porque tiene los títulos:
										// Titulo,Autor,Calificacion,Categoria,Portada,Ancho,Alto

		linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.trim().split(",");
			String elTitulo = partes[0];
			String elAutor = partes[1];
			double laCalificacion = Double.parseDouble(partes[2]);
			String nombreCategoria = partes[3];
			Categoria laCategoria = buscarCategoria(nombreCategoria);
			String archivoPortada = partes[4];
			int ancho = Integer.parseInt(partes[5]);
			int alto = Integer.parseInt(partes[6]);

			// Crear un nuevo libro
			Libro nuevo = new Libro(elTitulo, elAutor, laCalificacion, laCategoria);
			libros.add(nuevo);

			// Si existe el archivo de la portada, ponérselo al libro
			if (existeArchivo(archivoPortada))
			{
				Imagen portada = new Imagen(archivoPortada, ancho, alto);
				nuevo.cambiarPortada(portada);
			}

			linea = br.readLine();
		}

		br.close();

		return libros;
	}

	/**
	 * Busca una categoría a partir de su nombre
	 * 
	 * @param nombreCategoria El nombre de la categoría buscada
	 * @return La categoría que tiene el nombre dado
	 */
	private Categoria buscarCategoria(String nombreCategoria)
	{
		// TODO Parte 2 - completar el método de acuerdo a la documentación
		
		Categoria catg = null;
		for (int i = 0; i < categorias.length && catg == null ; i++)
		{						
			if (categorias[i].darNombre().equals(nombreCategoria))
			{
				catg = categorias[i];
			}				
		}	
		return catg;
	}

	/**
	 * Verifica si existe el archivo con el nombre indicado dentro de la carpeta
	 * "data".
	 * 
	 * @param nombreArchivo El nombre del archivo que se va a buscar.
	 * @return
	 */
	private boolean existeArchivo(String nombreArchivo)
	{
		File archivo = new File("./data/" + nombreArchivo);
		return archivo.exists();
	}

	/**
	 * Retorna una lista con los libros que pertenecen a la categoría indicada en el
	 * parámetro
	 * 
	 * @param nombreCategoria El nombre de la categoría de interés
	 * @return Una lista donde todos los libros pertenecen a la categoría indicada
	 */
	public ArrayList<Libro> darLibros(String nombreCategoria)
	{
		ArrayList<Libro> seleccionados = new ArrayList<Libro>();

		/*
		 * TODO Parte 2 - recorra el arreglo de categorias, haciendo un recorrido
		 * parcial. Cuando encuentre la categoría con el nombre dado, agregue todos los
		 * libros de esa categoría a la lista de libros que se encuentra en la variable
		 * 'seleccionados'.
		 * 
		 * Para agregar muchos elementos a una lista con facilidad puede utilizar el
		 * método addAll.
		 */		
				
		for (int i = 0; i < catalogo.size(); i++)
		{						
			if (catalogo.get(i).darCategoria().darNombre().equals(nombreCategoria))
			{
				Libro libros = catalogo.get(i);
				
				seleccionados.add(libros);
			}
		}	
		
		return seleccionados;
	}

	/**
	 * Busca un libro a partir de su título
	 * 
	 * @param tituloLibro Título del libro buscado
	 * @return Retorna un libro con el título indicado o null si no se encontró un
	 *         libro con ese título
	 */
	public Libro buscarLibro(String tituloLibro)
	{
		Libro nameLibro = null;
		for (Libro book: catalogo)
		{
			if (book.darTitulo().equals(tituloLibro) == true)
			{
				nameLibro = book;
			}
			
		}
		//LISTO
		// TODO Parte 4 - completar el m�todo de acuerdo a la documentaci�n
		// Debe recorrer la lista de libros (no tiene sentido recorrer las categorias)
		return nameLibro;
	}
	/**
	 * Busca en la librería los libros escritos por el autor indicado.
	 * 
	 * El nombre del autor puede estar incompleto, y la búsqueda no debe tener en
	 * cuenta mayúsculas y minúsculas. Por ejemplo, si se buscara por "ulio v"
	 * deberían encontrarse los libros donde el autor sea "Julio Verne".
	 * 
	 * @param cadenaAutor La cadena que se usará para consultar el autor. No
	 *                    necesariamente corresponde al nombre completo de un autor.
	 * @return Una lista con todos los libros cuyo autor coincida con la cadena
	 *         indicada
	 */
	public ArrayList<Libro> buscarLibrosAutor(String cadenaAutor)
	{
		ArrayList<Libro> librosAutor = new ArrayList<Libro>();

		/*
		 * TODO Parte 2 - recorra el arreglo de categorias, haciendo un recorrido total.
		 * En cada categoría busque los libros que haya en esa categoría y que hayan
		 * sido escritos por el autor indicado. Agregue esos libros a la lista de libros
		 * que se encuentra en la variable 'librosAutor'.
		 * 
		 * Para agregar muchos elementos a una lista con facilidad puede utilizar el
		 * método addAll. ***
		 */
		
		String autor = cadenaAutor.toLowerCase();
		
		for (int i = 0; i < catalogo.size() ;i++)
		{
			if (catalogo.get(i).darAutor().toLowerCase().contains(autor))
			{
				Libro libros_titulo = catalogo.get(i);
				
				librosAutor.add(libros_titulo);
			}
		
		}
		
		return librosAutor;
	}

	
	/*
	 * 
	 * verifica que el nombre nuevo no este repetido en los nombres de la categoria
	 */
	public int  verificarRepeteción(String nombre)
	{
		int si = 1;
		for (int i = 0; i < categorias.length ;i++)
		{
			if ((categorias[i].darNombre().equals(nombre)))
			{
				si = -1;
			}	
		
		}
		return si;
	}
	
	
	
	/**
	 * Cambia el nombre de una categoria
	 * 
	 * @param nuevo nombre de la categoria
	 * @return Una lista con las categorías en las cuales hay al menos un libro del
	 *         autor indicado. Si no hay un libro del autor en ninguna categoría,
	 *         retorna una lista vacía.
	 */
	public int  nuevoNombre(String nombre, String nombreCategoria)
	{
		int si = -1;
		for (int i = 0; i < categorias.length ;i++)
		{
			//System.out.println("paso por aqui");
			if ((categorias[i].darNombre().equals(nombreCategoria)))
			{
				categorias[i].cambiarNombre(nombre);
				//System.out.println("Tambien ");
				si = 1;
			}	
		}
		return si;
	}
	

	/**
	 * 
	 * vefirica que la categoria a la que se le quiere cambiar el nombre esté
	 * @param nombreCategoria
	 * @return
	 */
	public int renombrarCategoria(String nombreCategoria)
	{
		int si = 0; 
		
		for (int i = 0; i < categorias.length ;i++)
		{
				if ((categorias[i].darNombre().equals(nombreCategoria)))
				{
					si = 1;
				}
		}
		return si;
	}

		/*
		 * TODO Parte 2 - recorra el arreglo de categorias, haciendo un recorrido total.
		 * En cada categoría, busque si en esa categoría hay libros que hayan sido
		 * escritos por el autor indicado. Si es así, agregue la categoría a la lista de
		 * categorías que se encuentra en la variable 'resultado'.
		 * 
		 * Para agregar un elemento a una lista puede utilizar el método add.
		 */
		
		

	
	

	
	/**
	 * Busca en qué categorías hay libros del autor indicado.
	 * 
	 * Este método busca libros cuyo autor coincida exactamente con el valor
	 * indicado en el parámetro nombreAutor.
	 * 
	 * @param nombreAutor El nombre del autor
	 * @return Una lista con las categorías en las cuales hay al menos un libro del
	 *         autor indicado. Si no hay un libro del autor en ninguna categoría,
	 *         retorna una lista vacía.
	 */
	public ArrayList<Categoria> buscarCategoriasAutor(String nombreAutor)
	{
		ArrayList<Categoria> resultado = new ArrayList<Categoria>();

		/*
		 * TODO Parte 2 - recorra el arreglo de categorias, haciendo un recorrido total.
		 * En cada categoría, busque si en esa categoría hay libros que hayan sido
		 * escritos por el autor indicado. Si es así, agregue la categoría a la lista de
		 * categorías que se encuentra en la variable 'resultado'.
		 * 
		 * Para agregar un elemento a una lista puede utilizar el método add.
		 */
		
		for (int i = 0; i < categorias.length ;i++)
		{
			if (categorias[i].hayLibroDeAutor(nombreAutor) == true)
			{
				resultado.add(categorias[i]);
			}
		}
				
		return resultado;
	}

	/**
	 * Calcula la calificación promedio calculada entre todos los libros del
	 * catálogo
	 * 
	 * @return Calificación promedio del catálogo
	 */
	public double calificacionPromedio()
	{
		double promedio = 0.0;
		double numbooks = catalogo.size();
		double calificacion = 0.0;
		for (Libro li: catalogo)
		{
			calificacion+= li.darCalificacion();
		}
		promedio = calificacion/numbooks;
		//LISTO
		// TODO Parte 4 - completar el m�todo de acuerdo a la documentaci�n
		// Debe recorrer la lista de libros (no tiene sentido recorrer las categorias)
		return promedio;
	}
	
	/**
	 * Busca cuál es la categoría que tiene más libros
	 * 
	 * @return La categoría con más libros. Si hay empate, retorna cualquiera de las
	 *         que estén empatadas en el primer lugar. Si no hay ningún libro,
	 *         retorna null.
	 */
	public Categoria categoriaConMasLibros()
	{
		// TODO Parte 2 - completar el método de acuerdo a la documentación
		
		Categoria masLibros = null;
		
		int mayor = 0;
		
		for (int i = 0; i < categorias.length ;i++)
		{
			if (categorias[i].contarLibrosEnCategoria() >= mayor)
			{
				mayor = categorias[i].contarLibrosEnCategoria();
				masLibros = categorias[i];
			}
		}

		return masLibros;	
		
		
	}

	/**
	 * Busca cuál es la categoría cuyos libros tienen el mayor promedio en su
	 * calificación
	 * 
	 * @return Categoría con los mejores libros
	 */
	public Categoria categoriaConMejoresLibros()
	{
		// TODO Parte 2 - completar el método de acuerdo a la documentación
		
		double mayor  = 0;
		Categoria nombre = null;
		
		for (int i = 0; i < categorias.length ;i++)
		{
			if (categorias[i].calificacionPromedio() > mayor)
			{
				mayor = categorias[i].calificacionPromedio();
				nombre = categorias[i];
			}		
		}

		return nombre;
	}

	/**
	 * Cuenta cuántos libros del catálogo no tienen portada
	 * 
	 * @return Cantidad de libros sin portada
	 */
	public int contarLibrosSinPortada()
	{
		int numportadas = 0;
		for (Libro numlibros:catalogo)
		{
			if (numlibros.tienePortada() == false)
			{
				numportadas++;
			}
		
		}
		//LISTO
		// TODO Parte 4 - completar el m�todo de acuerdo a la documentaci�n
		// Debe recorrer la lista de libros (no tiene sentido recorrer las categorias)
		return numportadas;
	}


	/**
	 * Consulta si hay algún autor que tenga un libro en más de una categoría
	 * 
	 * @return Retorna true si hay algún autor que tenga al menos un libro en dos
	 *         categorías diferentes. Retorna false en caso contrario.
	 */
	public boolean hayAutorEnVariasCategorias()
	{
		ArrayList<Categoria> libroscategoria = new ArrayList<Categoria>();
		ArrayList<String> namesautor = new ArrayList<String>();
		boolean presente = false;
		for (Categoria cate : categorias)
		{
			
			ArrayList<Libro> namesbook = cate.darLibros();
			for (Libro book: namesbook)
			{
				if((namesautor.contains(book.darAutor()) == false) && (libroscategoria.contains(book.darCategoria())==false))
				{
					namesautor.add(book.darAutor());
					libroscategoria.add(book.darCategoria());
				}
					
				else if((namesautor.contains(book.darAutor()) == true) && (libroscategoria.contains(book.darCategoria())==true))
				{
					namesautor.add(book.darAutor());
					libroscategoria.add(book.darCategoria());
					
				}
				else if ((namesautor.contains(book.darAutor()) == true) && (libroscategoria.contains(book.darCategoria())==false)) 
				
				{
					presente = true;
					
				}
				else {
					namesautor.add(book.darAutor());
					libroscategoria.add(book.darCategoria());
				}
			}
				
		}
		// TODO Parte 4 - completar el m�todo de acuerdo a la documentaci�n
		// Implemente el m�todo como considere conveniente (recorriendo primero las
		// categor�as o los libros)
		return presente;
		}
	

	
}



