import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Usuario extends JFrame {

	private JPanel abrirPorta;
	private JTextField ipServidor;
	private JTextField portaServer;
	public Cliente cliente;
	private JTextField PortaServidor;
	public Servidor servidor;
	public static Usuario frame;
	public JTextPane PorcentagemCliente;
	public JTextPane Porcentagem;
	public JTextPane tempoServidor;
	public JTextPane tempoCliente;
	public boolean portaberta;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					                
					frame = new Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Usuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 445);
		abrirPorta = new JPanel();
		abrirPorta.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(abrirPorta);
		abrirPorta.setLayout(null);
		
		ipServidor = new JTextField();
		ipServidor.setBounds(475, 16, 112, 23);
		abrirPorta.add(ipServidor);
		ipServidor.setColumns(10);
		
		JTextPane ipCliente = new JTextPane();
		ipCliente.setBounds(103, 19, 112, 20);
		abrirPorta.add(ipCliente);
		
		portaServer = new JTextField();
		portaServer.setBounds(103, 46, 112, 23);
		abrirPorta.add(portaServer);
		portaServer.setColumns(10);
				
		Porcentagem = new JTextPane();
		Porcentagem.setBounds(21, 112, 37, 23);
		abrirPorta.add(Porcentagem);
		
		JButton btnNewButton_4 = new JButton("Abrir porta");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					servidor=new Servidor(Integer.parseInt(portaServer.getText()), frame);
					portaberta=true;
					btnNewButton_4.setBounds(103, 80, 112, 23);
					abrirPorta.add(btnNewButton_4);
					
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread server= new Thread(servidor);
				server.start();
			}
		});
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(!portaberta) {
					cliente = new Cliente(ipServidor.getText(), Integer.parseInt(PortaServidor.getText()), frame);
					ipCliente.setText(ipServidor.getText());
					Thread client;
	                client = new Thread(cliente);
	                client.start();
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
                
				;
				
			}
		});
		btnNewButton.setBounds(266, 16, 89, 23);
		abrirPorta.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cliente.setCancelado(true);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(266, 112, 89, 23);
		abrirPorta.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pausar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setPausado(true);
			}
		});
		btnNewButton_2.setBounds(266, 46, 89, 23);
		abrirPorta.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Reiniciar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setPausado(false);
			}
		});
		btnNewButton_3.setBounds(266, 78, 89, 23);
		abrirPorta.add(btnNewButton_3);
		
		
		
		JLabel lblIp = new JLabel("SERVIDOR ");
		lblIp.setBounds(10, 0, 63, 14);
		abrirPorta.add(lblIp);
		
		JLabel lblNewLabel = new JLabel("CLIENTE");
		lblNewLabel.setBounds(384, 0, 71, 14);
		abrirPorta.add(lblNewLabel);
		
		JLabel lblIpDoCliente = new JLabel("IP do Cliente:");
		lblIpDoCliente.setBounds(10, 20, 103, 14);
		abrirPorta.add(lblIpDoCliente);
		
		JLabel lblNewLabel_1 = new JLabel("IP do Servidor:");
		lblNewLabel_1.setBounds(384, 20, 103, 14);
		abrirPorta.add(lblNewLabel_1);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 50, 89, 14);
		abrirPorta.add(lblPorta);
		
		PortaServidor = new JTextField();
		PortaServidor.setBounds(475, 46, 112, 23);
		abrirPorta.add(PortaServidor);
		PortaServidor.setColumns(10);
		
		JLabel lblPortaServidor = new JLabel("Porta:");
		lblPortaServidor.setBounds(384, 50, 103, 14);
		abrirPorta.add(lblPortaServidor);
		
		PorcentagemCliente = new JTextPane();
		PorcentagemCliente.setBounds(384, 112, 42, 30);
		abrirPorta.add(PorcentagemCliente);
		
		tempoServidor = new JTextPane();
		tempoServidor.setBounds(82, 112, 63, 30);
		abrirPorta.add(tempoServidor);
		
		tempoCliente = new JTextPane();
		tempoCliente.setBounds(455, 112, 71, 30);
		abrirPorta.add(tempoCliente);
		
		
			
	} 
	
public void setarPorcento(double porcento, JTextPane Porcentagem ) {
	
	DecimalFormat df = new DecimalFormat("0.#");
	String dx = df.format(porcento);
		Porcentagem.setText(dx+"%");
	}


public void setarTempo(double tempo, JTextPane tempoPane) {
	DecimalFormat df = new DecimalFormat("0.##");
	String dx = df.format(tempo);
		tempoPane.setText(dx);
}
}