import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InLezen {
	private String input = "", path = "";
	private int wordCounter = 0;
	private ArrayList<String> aanwezigeWoorden = new ArrayList<String>();
	private ArrayList<String> sTemp = new ArrayList<String>();
	private ArrayList<String> sCheck = new ArrayList<String>();
	private ArrayList<String> bestandSchoon = new ArrayList<String>();
	private UitSchrijven us;

	public InLezen(String path) {
		Reader r = new Reader(path + "schoneBestanden.obj");

		if (r.exists()){
			bestandSchoon = (ArrayList<String>) r.readObject();
		}
		this.path = path;
	}

	public void bijwerken(ArrayList<String> input, boolean isVisual) {
		wipe();
		for (String s : input) {
			setFiles(s);
			try {
				textImport();
			} catch (IOException e) {
				e.printStackTrace();
			}
			loop(isVisual);
		}
	}

	public void setFiles(String inputs) {
		input = inputs;
	}

	public boolean woordAanwezig(String woord) throws IOException {

		if (aanwezigeWoorden.contains(woord)) {
			// tempo = woord;
			// tempo vervangen door parameter woord -> aanwezigWoord in
			// veranderRegel
			veranderRegel(aanwezigeWoorden.size(), woord);
			return true;
		}

		aanwezigeWoorden.add(woord);
		sTemp.add(woord + " " + wordCounter);
		sCheck.add(woord);
		return false;
	}

	public void wipe() {
		System.out.println("[" + path + "data.txt" + "]");
		Writer wr = new Writer(path + "tempo.txt");
		wr.bufferedWriter("");
		wr = new Writer(path + "data.txt");
		wr.bufferedWriter("");
	}

	public void veranderRegel(int lengte, String aanwezigWoord) throws IOException {
		String boekWoord = "";
		for (int i = 0; i < lengte; i++) {
			if (sCheck.get(i).equals(aanwezigWoord)) {
				sCheck.remove(i);
				sCheck.add(aanwezigWoord);

				boekWoord = sTemp.get(i);

				sTemp.remove(i);
				sTemp.add(boekWoord + " " + wordCounter);
				break;
			}
		}
	}

	public void loop(boolean visualsOn) {
		Writer wr = new Writer(path + "data.txt");
		wr.bufferedWriter("");
		wr = new Writer(path + "data.txt", true);
		for (String gek : sTemp) {
			wr.bufferedWriter(gek + System.getProperty("line.separator"));
			if (visualsOn) {
				System.out.print("\n " + gek);
			}
			us.setArray(gek);
			// mf.setTextField(gek);
		}
	}

	public void textImport() throws IOException {
		verschoon();
		Writer wr = new Writer(path + "data.txt", true);

		Scanner s1 = new Scanner(new FileReader(input));
		s1.useDelimiter("\\s");

		while (s1.hasNext()) {
			String result = s1.next();
			if (!result.isEmpty()) {
				wordCounter++;
				result = result.toLowerCase();
				String temp = result.replaceAll(",[\\+\\.\\^:]\"", "");
				if (!(woordAanwezig(temp))) {
					wr.bufferedWriter(temp + "#" + System.getProperty("line.separator"));
					// System.out.println("APPENDED " + temp + " POSITIE " +
					// positie);
				}
			}
		}
		s1.close();
	}

	public void verschoon() throws IOException {
		if (!bestandSchoon.contains(input)) {
			bestandSchoon.add(input);
			File fvan = new File(input);
			File fnaar = new File(path + "temp.txt");
			fnaar.createNewFile();

			// fnaar.delete();
			if (fnaar.delete()) {
				System.out.print("ja1");
				fvan.renameTo(fnaar);
			} else {
				System.out.println("nee1");
			}
			Writer writer = new Writer(fvan, true);
			Scanner scanner = new Scanner(new FileReader(fnaar));
			while (scanner.hasNext()) {

				writer.bufferedWriter(cleanString(scanner.next()));
				writer.bufferedWriter(" ");

				writer = new Writer(path + "schoneBestanden.obj");
				writer.objectOutputStream(bestandSchoon);
System.out.println("Schoon bestand aangemaakt");
			}
			scanner.close();
			System.out.println("" + fvan.exists());
		}
	}

	private String cleanString(String mixedString) {
		String cleaned = "";
		StringBuilder sb = new StringBuilder(mixedString);
		for (int index = 0; index < sb.length(); index++) {
			char c = sb.charAt(index);
			if (Character.isAlphabetic(c) || Character.isDigit(c)) {
				cleaned = cleaned + c;
			}
		}
		return cleaned;
	}

	public void setUitSchrijven(UitSchrijven ui) {
		us = ui;
	}

}
