package tray;

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
		NumberToImage formatConverter = new NumberToImage();
		
		while(true){
			print();
			Image pingTimeAsImage = formatConverter.intToImage();

			trayIcon.setImage(pingTimeAsImage);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	void print(){
		System.out.println(""+(++aa));
	}

}
