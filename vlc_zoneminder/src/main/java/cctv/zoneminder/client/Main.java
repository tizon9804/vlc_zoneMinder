package cctv.zoneminder.client;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	private static final String ERRORNUMERORANGO = "Ingrese un Puerto válido";
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

	public static void main(String[] args) {
		Main main = new Main();
		Interfaz interfaz = new Interfaz(main);
		interfaz.setVisible(true);
		PanelControl control = new PanelControl(interfaz);
		control.setVisible(true);
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	

}
