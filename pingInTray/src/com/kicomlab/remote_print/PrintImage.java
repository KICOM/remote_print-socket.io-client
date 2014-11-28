package com.kicomlab.remote_print;

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
import javax.print.attribute.standard.Copies; 

public class PrintImage { 

	public PrintImage() {
		// TODO Auto-generated constructor stub
	}
	
	public void printer(){
		try {
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet(); 
			pras.add(new Copies(1)); 
			PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras); 
			if (pss.length == 0) 
				throw new RuntimeException("No printer services available."); 
			PrintService ps = pss[0];
			for(PrintService p : pss){
				System.out.println(p.getName()); 
			}
			System.out.println("Printing to " + ps); 
			DocPrintJob job = ps.createPrintJob(); 
			FileInputStream fin;
			fin = new FileInputStream("C:\\Users\\JerryPark\\Desktop\\asd.png");
			Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, null); 
			job.print(doc, pras); 
			fin.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}  