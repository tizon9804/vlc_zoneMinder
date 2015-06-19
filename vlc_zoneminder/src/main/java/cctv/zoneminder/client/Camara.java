package cctv.zoneminder.client;

import java.io.Serializable;



public class Camara implements Serializable {
	
	private int puerto;
	private String ip;	
	private String ruta;
	
	public Camara(int port,String ip,String ruta) {
		
		this.puerto=port;
		this.ip=ip;
		this.setRuta(ruta);
	}
	
	public int getPuerto() {
		return puerto;
	}
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
