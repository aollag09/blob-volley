package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import serveur.Serveur;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu(){
		this.setLayout(new BorderLayout());

		JLabel titreLab = new JLabel("Blob-Volley");
		titreLab.setFont(new Font("Arial", Font.BOLD, 25));

		this.add(titreLab, BorderLayout.NORTH);

		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(1,2));

		JPanel serveur = new JPanel();
		serveur.setLayout(new BorderLayout());
		serveur.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		JPanel titreServeur = new JPanel();
		titreServeur.setLayout(new FlowLayout());

		JLabel titreServeurLab = new JLabel("Hoster une partie :");
		titreServeur.add(titreServeurLab);

		serveur.add(titreServeur, BorderLayout.NORTH);

		JPanel corpsServeur = new JPanel();
		corpsServeur.setLayout(new FlowLayout());

		JLabel port = new JLabel("Port : ");
		corpsServeur.add(port);

		final JFormattedTextField lePort = new JFormattedTextField(NumberFormat.getIntegerInstance());
		lePort.setPreferredSize(new Dimension(70, 20));
		corpsServeur.add(lePort);

		serveur.add(corpsServeur, BorderLayout.CENTER);

		JButton validServeur = new JButton("Hoster la partie");
		validServeur.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					Main.port = Integer.parseInt(lePort.getText());
				} catch (Exception ee){
					Main.port = 12345;
				}
				Serveur.launchServeur();
			}

		});
		serveur.add(validServeur, BorderLayout.SOUTH);

		menu.add(serveur);

		JPanel client = new JPanel();
		client.setLayout(new BorderLayout());
		client.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		JPanel titreClient = new JPanel();
		titreClient.setLayout(new FlowLayout());

		JLabel titreClientLab = new JLabel("Rejoindre une partie :");
		titreClient.add(titreClientLab);

		client.add(titreClient, BorderLayout.NORTH);

		JPanel corpsClient = new JPanel();
		corpsClient.setLayout(new FlowLayout());

		JLabel ip = new JLabel("IP : ");
		corpsClient.add(ip);

		JTextField lIp = new JTextField();
		lIp.setPreferredSize(new Dimension(100, 20));
		corpsClient.add(lIp);

		JLabel port2 = new JLabel("Port : ");
		corpsClient.add(port2);

		JFormattedTextField lePort2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		lePort2.setPreferredSize(new Dimension(70, 20));
		corpsClient.add(lePort2);

		client.add(corpsClient, BorderLayout.CENTER);

		JButton validerClient = new JButton("Rejoindre la Partie");
		client.add(validerClient, BorderLayout.SOUTH);

		menu.add(client);

		this.add(menu, BorderLayout.CENTER);
	}

}
