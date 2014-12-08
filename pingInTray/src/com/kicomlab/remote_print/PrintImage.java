package com.kicomlab.remote_print;

import java.awt.*;
import java.awt.print.Printable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc; 
import javax.print.DocFlavor; 
import javax.print.DocPrintJob; 
import javax.print.PrintService; 
import javax.print.PrintServiceLookup; 
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;

public class PrintImage {

	public PrintImage() {
		// TODO Auto-generated constructor stub
	}
	
	public void printer(){
		try {
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet(); 
			pras.add(new Copies(1));
			pras.add(MediaSizeName.ISO_A4);
			pras.add(new MediaPrintableArea((float)0.0,(float)0.0, 210, 297, MediaPrintableArea.MM));

			System.out.println("Printing to " + Settings.printer);
			DocPrintJob job = Settings.printer.createPrintJob();
			DocFrame pp = new DocFrame();
			pp.setVisible(true);
			Doc doc = new SimpleDoc(pp.getCanvas(), DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
			job.print(doc, pras);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}  