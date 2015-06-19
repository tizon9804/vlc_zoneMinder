package cctv.zoneminder.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.crypto.spec.DESedeKeySpec;
import javax.swing.JOptionPane;

public class Main implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 917005948119571357L;
	private static final String ERRORNUMERORANGO = "Ingrese un Puerto válido";
	private static final String ERRORDEPERSISTENCIA = "No hay Archivo de configuración, se va a cargar por default Zoneminder";
	private String ip;
	private int puerto;
	private String ruta;
	private ArrayList<Camara> camaras;

	public Main() {
		ip = "239.0.10.1";
		puerto = 5451;
		ruta="rtp://@"+ip+":"+puerto;
		camaras = new ArrayList<Camara>();
	}

	public ArrayList<Camara> crearCamaras(Interfaz interfaz, String ip,String rango){

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
									int port=j;
									String ruta="rtp://@"+ip+":"+port;
									Camara c= new Camara(port, ip, ruta);
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
				}			
				else{
					try{
						int port=Integer.parseInt(range);
						String ruta="rtp://@"+ip+":"+port;
						Camara c= new Camara(port, ip, ruta);
						camaras.add(c);
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
					}
				}

			}
		}
		else if (rango.contains("-")){
			try{
				String[] rangosNivel2= rango.split("-");
				if(rangosNivel2.length==2){
					int min=Integer.parseInt(rangosNivel2[0]);
					int max=Integer.parseInt(rangosNivel2[1]);
					if(min<max){
						for (int j = min; j <= max; j++) {		
							int port=j;
							String ruta="rtp://@"+ip+":"+port;
							Camara c= new Camara(port, ip, ruta);
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
		}	
		else{
			try{
				int port=Integer.parseInt(rango);
				String ruta="rtp://@"+ip+":"+port;
				Camara c= new Camara(port, ip, ruta);
				camaras.add(c);
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(interfaz,ERRORNUMERORANGO);
			}
		}

		return camaras;
	}
	
	public void serializar(Interfaz interfaz){
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream("./zoneminder.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Archivo guardado satisfactoriamente en ./zoneminder.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	          JOptionPane.showMessageDialog(interfaz, "Error al guardar.");
	      }
	}
	
	public static Main deserializar(){
		try
	      {  Main main=null;
	         FileInputStream fileIn = new FileInputStream("./zoneminder.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         main = (Main) in.readObject();
	         in.close();
	         fileIn.close();
	         return main;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         System.out.println(ERRORDEPERSISTENCIA);
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println(ERRORDEPERSISTENCIA);
	         c.printStackTrace();
	         return null ;
	      }
	}

	public static void main(String[] args) {
		Main main = deserializar() ;
		boolean persistencia=true;
		if(main==null){
			main=new Main();
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

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	

}
