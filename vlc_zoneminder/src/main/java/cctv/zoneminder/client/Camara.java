package cctv.zoneminder.client;

import java.io.Serializable;

public class Camara implements Serializable {
	
	private static final String ZM_PATH = "/zm/cgi-bin/nph-zms?mode=mpeg&format=asf&monitor=";
	private static final String ZM_AUTH = "&user=streaming&pass=9jHdjIyKT0Sh184Hy36Yqah";
	
	private static final long serialVersionUID = 774648931592861756L;	

	private String path;
	private int monitor;
	
	public Camara(String host, int monitor) {
		this.monitor = monitor;
		this.path = host + ZM_PATH + this.monitor + ZM_AUTH;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public int getMonitor() {
		return this.monitor;
	}
}