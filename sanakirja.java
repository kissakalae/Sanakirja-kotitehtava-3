import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class sanakirja {
	
		/*public static HashMap<String, String> deserialisoiXML() throws IOException {
	
	
		XMLDecoder decoder = null;	
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("sanakirja.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found");
		} HashMap<String, String> deserialisoituKirja = (HashMap) decoder.readObject();
		System.out.println(deserialisoituKirja);
		return deserialisoituKirja;
		}  */

    private static HashMap<String, String> deserialisoiXML() throws IOException {
        FileInputStream filunInput = new FileInputStream("sanakirja.xml");
        XMLDecoder decoder = new XMLDecoder(filunInput);
        HashMap<String, String> deserialisoituKirja = (HashMap) decoder.readObject();
        decoder.close();
        filunInput.close();
        return deserialisoituKirja;
    } 
	
	public static void serialisoiXML(HashMap<String, String> sanakirja) throws IOException {
        FileOutputStream filunOutput = new FileOutputStream("sanakirja.xml");
        XMLEncoder encoder = new XMLEncoder(filunOutput);
        encoder.writeObject(sanakirja);
        encoder.close();
        filunOutput.close();
    }
	
	// MAIN //
	
	public static void main(String[] args) throws IOException {
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		
		int luku = 0;
		
		HashMap <String, String> sanakirja = new HashMap<String, String>();
		Scanner sana = new Scanner(System.in);
		Scanner numero = new Scanner(System.in);	
		
		
		File sanaKirjasto = new File ("sanakirja.xml");
		if (sanaKirjasto.exists()) {
			System.out.println("Tiedosto l�ytyi."
					+ "\n L�ydetyt sanat: ");
			sanakirja = new HashMap<String, String>();
			
			for (int i = 0; i < suomi.length; i++) {
				sanakirja.put(suomi[i], englanti[i]);
				} 		
			System.out.println(sanakirja);	
			sanakirja = deserialisoiXML();
			
		} else {
			
			for (int i = 0; i < suomi.length; i++) {
				sanakirja.put(suomi[i], englanti[i]);
				} 
			
			System.out.println("Sanakirjaa ei l�ytynyt."
					+ "\n Luodaan uusi sanakirja..");
		}
		
		System.out.println(sanakirja + "\n");
		
		while(true) {
		System.out.println("\n" + "Haluaisitko k��nt�� sanan vai lis�t� sanoja?"
				+ " \n 1. K��nt��"
				+ " \n 2. Lis�t�"
				+ " \n 0. Lopettaa");
		luku = numero.nextInt();
		if (luku == 1) {
			System.out.println("Mink� sanan haluaisit tiet��?");
			String input = sana.next();
			if (sanakirja.containsKey(input)) {
				System.out.print("Sanan " + input + " k��nn�s on " + sanakirja.get(input) + ".");
			} else System.out.print("Sanaa ei viel� l�ydy sanakirjasta.");
			
			} else if (luku == 2) {
				System.out.print("Anna sana suomeksi: ");
				String suomiSana = sana.next();
				System.out.print("Anna sana englanniksi: ");
				String englantiSana = sana.next();
				sanakirja.put(suomiSana, englantiSana);
			} else if (luku == 0) {
				serialisoiXML(sanakirja);
				System.exit(0);
 			} else {
				System.out.println("V��r� arvo. Valitse uudestaan.");
			}
	//	System.out.print("\n" + sanakirja + "\n");
	}
	}
}