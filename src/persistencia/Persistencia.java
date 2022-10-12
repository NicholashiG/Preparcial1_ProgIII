package persistencia;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import model.Estudiante;
import model.Programa;
import model.Universidad;


public class Persistencia {

	public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/resources/archivoEstudiantes.txt";
	public static final String RUTA_ARCHIVO_PROGRAMAS_XML = "src/resources/archivoProgramas.xml";
	public static final String RUTA_ARCHIVO_LOG_ESTUDIANTES = "src/resources/logEstudianes.txt";
	public static final String RUTA_ARCHIVO_PROPIEDADES = "src/modalidades.properties";




	public static ArrayList<String> cargarModalidades(){
		ArrayList<String> modalidades = ArchivoUtil.leerPropertiesModalidad(RUTA_ARCHIVO_PROPIEDADES);
		return modalidades;
	}


	public static void cargarDatosArchivos(Universidad universidad) throws FileNotFoundException, IOException {
		
		
		//cargar archivo de clientes
		ArrayList<Estudiante> estudiantesCargados = cargarEstudiantes();
		
		if(estudiantesCargados.size() > 0)
			universidad.getEstudiantes().addAll(estudiantesCargados);
	}
	
	
	


	/**
	 * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
	 *
	 * @throws IOException
	 */
	public static void guardarEstudiantes(ArrayList<Estudiante> listaEstudiantes) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Estudiante estudiante:listaEstudiantes)
		{
			contenido+= estudiante.getNombre()+","+estudiante.getId()+","+estudiante.getNota1()+","+estudiante.getNota2()+","+estudiante.getNota3()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);
		
	}
	
//	----------------------LOADS------------------------
	
	/**
	 * 
	 *
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Estudiante> cargarEstudiantes() throws FileNotFoundException, IOException
	{
		ArrayList<Estudiante> estudiantes =new ArrayList<Estudiante>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Estudiante estudiante = new Estudiante();
			estudiante.setNombre(linea.split(",")[0]);
			estudiante.setId(linea.split(",")[1]);
			estudiante.setNota1(linea.split(",")[2]);
			estudiante.setNota2(linea.split(",")[3]);
			estudiante.setNota3(linea.split(",")[4]);
			estudiantes.add(estudiante);
		}
		return estudiantes;
	}


	public static void guardaRegistroLogEstudiantes(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG_ESTUDIANTES);
	}




	
	

	
	
	//------------------------------------SERIALIZACION  y XML

	public static Universidad cargarRecursoProgramaXML() {
		
		Universidad universidad = null;
		
		try {
			universidad = (Universidad) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_PROGRAMAS_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return universidad;

	}

	
	
	public static void guardarRecursoProgramaXML(Universidad universidad) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_PROGRAMAS_XML, universidad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
