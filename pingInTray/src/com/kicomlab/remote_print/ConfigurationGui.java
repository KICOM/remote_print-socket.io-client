package com.kicomlab.remote_print;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConfigurationGui extends JFrame{

	private static final long serialVersionUID = 1L;

	public ConfigurationGui(){

		setTitle("Configure Ping In Tray");
		setSize(300, 100);
		ConfigurationPanel panel = new ConfigurationPanel();
		add(panel, BorderLayout.CENTER);

	}

	class ConfigurationPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ConfigurationPanel() {
			final JButton b3 = new JButton("Print GOOD");
			add(b3, BorderLayout.SOUTH);
			b3.setActionCommand("GOOD");
			b3.addActionListener(this); 
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if ("GOOD".equals(e.getActionCommand())) {
				System.out.println("GOOD");
				//Service.send("WWW");
			}
		}
	}
}
