package cctv.zoneminder.server;

public class Stream {
	
	private final static String ZONEMINDER_PATH = "http://localhost/cgi-bin/nph-zms?mode=jpeg&monitor=$camera&scale=100&buffer=0&user=streaming&pass=YcrSM_9G8qGN4-V4r8";
	
	
	public Stream(String monitorId, String user, String pass) {
		
	}
	
	private static String formatRtpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{dst=");
        sb.append(serverAddress);
        sb.append(",port=");
        sb.append(serverPort);
        sb.append(",mux=ts}");
        return sb.toString();
    }
}
