package cctv.zoneminder.server;

import java.util.ArrayList;

public class Main {
	
	private String username;
	private String password;
	
	private ArrayList<Stream> streams;
	
	public Main(String username, String password) {
		this.username = username;
		this.password = password;
		
		this.streams = new ArrayList<Stream>();
	}
	
	public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.out.println("Usage zmvlcj username password");
            System.exit(1);
        }
        Main main = new Main(args[0], args[1]);
	}
}
