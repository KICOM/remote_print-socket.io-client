package com.kicomlab.remote_print;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ConfigurationGui extends JFrame{

	private static final long serialVersionUID = 1L;

	public ConfigurationGui(){

		setTitle("Configure Remote Printer");
		setSize(350, 240);

		ConfigurationPanel panel = new ConfigurationPanel();
		add(panel, BorderLayout.CENTER);

	}

	class ConfigurationPanel extends JPanel implements ActionListener, ListSelectionListener
	{

		private JList list = null;

		private static final long serialVersionUID = 1L;

		public ConfigurationPanel() {
			list = new JList(Settings.printer_list);
			JScrollPane jsp = new JScrollPane(list);
			list.addListSelectionListener((ListSelectionListener) this);
			add(BorderLayout.CENTER, jsp);

			JButton btnShow = new JButton("신청서 보기");
			add(btnShow, BorderLayout.SOUTH);
			btnShow.setActionCommand("btnShow");
			btnShow.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if ("btnShow".equals(e.getActionCommand())) {
				System.out.println("btnShow");
				DocFrame df = new DocFrame();
				df.setVisible(true);
			}
		}

		boolean flag = true;
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(flag) {
				Settings.printer = Settings.printer_list.get(list.getSelectedIndex());
			} // end if
			flag = !flag;
		} // valueChanged
	}
}
