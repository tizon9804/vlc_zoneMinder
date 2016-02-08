package cctv.zoneminder.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.crypto.spec.DESedeKeySpec;
import javax.swing.JOptionPane;

public class Main implements Serializable {

	private static final long serialVersionUID = 917005948119571357L;
	private static final String ERRORNUMERORANGO = "Ingrese un Puerto válido";
	private static final String ERRORDEPERSISTENCIA = "No hay Archivo de configuración, se va a cargar por default Zoneminder";
	private static final String RUTASER = "./zoneminder";
	private static FileInputStream fileIn;
	
	private ArrayList<Camara> camaras;
	private String rutaserializacion;

	public Main(String rutaser) {
		camaras = new ArrayList<Camara>();
		rutaserializacion=rutaser;
	}

	public ArrayList<Camara> crearCamaras(Interfaz interfaz, String host, String rango){

		if(rango.contains(",")){
			String[] rangos = rango.split(",");
			for (int i = 0; i < rangos.length; i++) {
				String range = rangos[i];
				if (range.contains("-")){
					try{
						String[] rangosNivel2= range.split("-");
						if(rangosNivel2.length==2){
							int min=Integer.parseInt(rangosNivel2[0]);
							int max=Integer.parseInt(rangosNivel2[1]);
							if(min<max){
								for (int j = min; j <= max; j++) {	
									int monitor=j;
									Camara c= new Camara(host, monitor);
									camaras.add(c);
								}
							}	

							else{
								JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
								return camaras;
							}
						}
						else{
							JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
							return camaras;
						}
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
						return camaras;
					}
				} else {
					try{
						int monitor = Integer.parseInt(range);
						Camara c= new Camara(host, monitor);
						camaras.add(c);
					} catch(Exception e){
						JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
					}
				}
			}
		} else if (rango.contains("-")){
			try {
				String[] rangosNivel2= rango.split("-");
				if(rangosNivel2.length==2){
					int min=Integer.parseInt(rangosNivel2[0]);
					int max=Integer.parseInt(rangosNivel2[1]);
					if(min<max){
						for (int j = min; j <= max; j++) {		
							int monitor=j;
							Camara c= new Camara(host, monitor);
							camaras.add(c);
						}
					}	

					else {
						JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
						return camaras;
					}
				} else {
					JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
					return camaras;
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
				return camaras;
			}
		} else {
			try {
				int monitor = Integer.parseInt(rango);
				Camara c= new Camara(host, monitor);
				camaras.add(c);
			} catch(Exception e){
				JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
			}
		}

		return camaras;
	}

	public void serializar(Interfaz interfaz){
		try
		{
			FileOutputStream fileOut = new FileOutputStream(rutaserializacion);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Archivo guardado satisfactoriamente en "+ rutaserializacion);
		}catch(IOException i)
		{
			i.printStackTrace();
			JOptionPane.showMessageDialog(interfaz, "Error al guardar.");
		}
	}

	public static Main deserializar(FileInputStream file){
		Main main = null;
		
		try {  
			fileIn = file == null ? new FileInputStream(RUTASER) : file;
		
			ObjectInputStream in = new ObjectInputStream(fileIn);
			main = (Main) in.readObject();
			
			in.close();
			fileIn.close();	
		} catch(IOException i) {
			i.printStackTrace();
			System.out.println(ERRORDEPERSISTENCIA);
		} catch(ClassNotFoundException c) {
			System.out.println(ERRORDEPERSISTENCIA);
			c.printStackTrace();
		}
		return main;
	}

	public static void main(String[] args) {
		String rutaser = RUTASER;
		if(args.length == 1){
			try {
				fileIn = new FileInputStream(args[0]);
				rutaser=args[0];
				System.out.println("Se cargo parametro de configuracion por consola");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fileIn=null;
			}
		}
		
		Main main = deserializar(fileIn) ;
		boolean persistencia=true;
		
		if(main == null){
			main=new Main(rutaser);
			persistencia=false;
		}
		
		Interfaz interfaz = new Interfaz(main);
		if(!persistencia){
			JOptionPane.showMessageDialog(interfaz, ERRORDEPERSISTENCIA);
		}
		interfaz.setVisible(true);
		PanelControl control = new PanelControl(interfaz);
		control.setVisible(true);
	}

	public ArrayList<Camara> getCamaras() {
		return camaras;
	}

	public void setCamaras(ArrayList<Camara> camaras) {
		this.camaras = camaras;
	}
}
