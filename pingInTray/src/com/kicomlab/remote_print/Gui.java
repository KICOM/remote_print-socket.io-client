package com.kicomlab.remote_print;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;

public class Gui implements ActionListener{
	 
	TrayIcon trayIcon;
	 SystemTray tray;
	 
	public Gui(){
		
		if (!SystemTray.isSupported()) {
			//TODO make a msg box
			System.out.println("SystemTray is not supported");
			return;
		}
		else
		{	
			MenuItem configure = new MenuItem("Configure");
			MenuItem exitItem = new MenuItem("Exit");

			configure.addActionListener(this);
			exitItem.addActionListener(this);
			
			configure.setActionCommand("Configure");
			exitItem.setActionCommand("Exit");
			
			PopupMenu mainMenu = new PopupMenu();
			mainMenu.add(configure);
			mainMenu.add(exitItem);

			String currentDir = System.getProperty("user.dir");
			Image image = new ImageIcon(currentDir+"\\src\\tray\\test.jpg").getImage();
			trayIcon = new TrayIcon(image, "tray icon",mainMenu);
			trayIcon.setImageAutoSize(true);
			tray = SystemTray.getSystemTray();
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				//TODO msg box
				System.out.println("Failed to add the tray icon");
			}

			startUpdater();

		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String action = event.getActionCommand();

		if(action.equals("Exit"))
		{
			//TODO msg box or don't bother printing a message
			System.out.println("Exiting");
			System.exit(0);

		}
		
		if(action.equals("Configure"))
		{
			//TODO msg box or don't bother printing a message
			System.out.println("Configure");
			ConfigurationGui configurer = new ConfigurationGui();
			configurer.setVisible(true);

		}
		
	}

	public void startUpdater(){

		PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, null);
		if (pss.length == 0)
			throw new RuntimeException("No printer services available.");
		Settings.printer = pss[0];
		for(PrintService p : pss){
			System.out.println(p.getName());
			Settings.printer_list.addElement(p);
		}

		Thread updater = new Thread(new TrayUpdater(trayIcon,tray));
		updater.start();   
	}

}

