package cctv.zoneminder.client;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class PanelControl extends JFrame {

	
	protected static final String CLAVE = "ubung";
	private JButton enviar_button;
	private JButton reproducir;
	private Interfaz interfaz;
	private TextField textIp;
	private Label labelIP;
	private TextField textRango;
	private Label labelRango;
	private JPasswordField password;
	private Label labelPass;
	

	public PanelControl(Interfaz interfazp) {
		this.interfaz = interfazp;			
		setBackground(Color.black);
		setForeground(Color.green);	
		setLayout(new GridLayout(4,1));
		setSize(400, 500);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(interfaz);
		JPanel forma = new JPanel();
		forma.setSize(200,400);
		forma.setLayout(new GridLayout(6,2));	
		forma.setBackground(Color.black);
		forma.setForeground(Color.green);
		textIp = new TextField("239.0.10.1");		
		labelIP = new Label("Ip Multicast:");
		textRango = new TextField("5448-5452");
		labelRango = new Label("Rango Camaras:");
		password = new JPasswordField();		
		labelPass = new Label("Contraseña: ");		
		textIp.setSize(200, 50);
		
		password.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {				
				if(password.getText().equals(CLAVE)){
					enviar_button.setEnabled(true);
				}
				else{
					enviar_button.setEnabled(false);
				}
				
			}
		});
		//#################################################
		enviar_button= new JButton("Enter");		
		enviar_button.setBackground(Color.black);
		enviar_button.setForeground(Color.green);
		enviar_button.setSelected(true);	
		enviar_button.setBorderPainted(false);
		enviar_button.setEnabled(false);
		enviar_button.addMouseListener(new MouseListener() {

			
			public void mouseExited(MouseEvent e) {
				enviar_button.setBackground(Color.BLACK);
				enviar_button.setForeground(Color.green);
			}

			
			public void mouseEntered(MouseEvent e) {
				enviar_button.setBackground(Color.WHITE);
				enviar_button.setForeground(Color.black);
			}

			
			public void mouseClicked(MouseEvent e) {

			}


			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}


			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		enviar_button.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent arg0) {		
				interfaz.agregarCamaras(textIp.getText(),textRango.getText());	
				password.setText("");
				enviar_button.setEnabled(false);
			}
		});	
		
		reproducir = new JButton("Reproducir");
		reproducir.setBackground(Color.black);
		reproducir.setForeground(Color.green);
		reproducir.setSelected(true);	
		reproducir.setBorderPainted(false);		
		reproducir.addMouseListener(new MouseListener() {

			
			public void mouseExited(MouseEvent e) {
				reproducir.setBackground(Color.BLACK);
				reproducir.setForeground(Color.green);
			}

			
			public void mouseEntered(MouseEvent e) {
				reproducir.setBackground(Color.WHITE);
				reproducir.setForeground(Color.black);
			}

			
			public void mouseClicked(MouseEvent e) {

			}


			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}


			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		reproducir.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent arg0) {		
				interfaz.toFront();
				interfaz.reproducir(interfaz.getCamaras());				
			}
		});	
		
		forma.add(new Label());
		forma.add(new Label());
		forma.add(labelIP);
		forma.add(textIp);
		forma.add(new Label());
		forma.add(new Label());
		forma.add(labelRango);
		forma.add(textRango);
		forma.add(new Label());
		forma.add(new Label());
		forma.add(labelPass);
		forma.add(password);				
		add(forma);
		add(enviar_button);	
		add(reproducir);	
		JPanel lista = new JPanel();
		lista.setBackground(Color.black);
		lista.setSize(400, 1000);
		add(lista);
		
		
		addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent e) {
				onDispose();
				
			}
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void onDispose() {
		super.dispose();
		try{
		dispose();
		if(interfaz!=null){
		interfaz.onDispose();
		}
		}
		catch(Exception e){
			
		}
		
	}
	

}
