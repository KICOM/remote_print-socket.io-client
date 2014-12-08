package com.kicomlab.remote_print;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;

public class TrayUpdater implements Runnable {
	
	static int aa=0;

	TrayIcon trayIcon;
	SystemTray tray;
	
	@SuppressWarnings("unused")
	private TrayUpdater(){}
	
	public TrayUpdater(TrayIcon trayIcon, SystemTray tray) {
		this.trayIcon=trayIcon;
		this.tray=tray;
	}

	@Override
	public void run() {
		Service service = new Service();
		NumberToImage formatConverter = new NumberToImage();
		
		while(true){
			String icon = "";
			if(Settings.printer == null) icon = "K";
			else icon = Settings.printer.getName().substring(0,1);
			Image pingTimeAsImage = formatConverter.intToImage(icon);

			trayIcon.setImage(pingTimeAsImage);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
