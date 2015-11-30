import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InLezen {
	private String temp1 = "", temp = "", input = "", output = "", tempo = "", path = "";
	private int tester = 0;
	private BufferedWriter bw = null;
	private ArrayList<String> s = new ArrayList<String>();
	private ArrayList<String> sTemp = new ArrayList<String>();
	private ArrayList<String> sCheck = new ArrayList<String>();
	private ArrayList<String> bestandSchoon = new ArrayList<String>();
	private UitSchrijven us;

	@SuppressWarnings("unchecked")
	public InLezen(String path) throws ClassNotFoundException, IOException {
		File f = new File(path + "schoneBestanden.obj");
		if (f.exists()) {
			FileInputStream fis = new FileInputStream(path + "schoneBestanden.obj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			bestandSchoon = (ArrayList<String>) ois.readObject();
			fis.close();
			ois.close();
		}
		this.path = path;
	}

	public void setFiles(String inputs, String outputs) {
		input = inputs;
		output = outputs;
	}

	public boolean woordAanwezig(String s1) throws IOException {
		if (s.contains(s1)) {
			tempo = s1;
			veranderRegel();
			return true;
		}

		s.add(s1);
		sTemp.add(s1 + " " + tester);
		sCheck.add(s1);
		return false;
	}

	public void wipe() throws IOException {
		bw = new BufferedWriter(new FileWriter(output.replace("data", "tempo")));
		bw.write("");
		bw.flush();
		bw.close();
		bw = new BufferedWriter(new FileWriter(output));
		bw.write("");
		bw.flush();
		bw.close();
	}

	public void veranderRegel() throws IOException {

		int i = 0;
		String leuk = "";

		for (String gek : s) {
			if (sCheck.get(i).equals(tempo)) {
				sCheck.remove(i);
				sCheck.add(tempo);

				leuk = sTemp.get(i);

				sTemp.remove(i);
				sTemp.add(leuk + " " + tester);
				break;
			}
			i++;
		}
	}

	public void loop(boolean visualsOn) throws IOException {
		bw = new BufferedWriter(new FileWriter(output.replace("data", "tempo")));
		bw.write("");
		bw.flush();
		bw.close();
		bw = new BufferedWriter(new FileWriter(output.replace("data", "tempo"), true));
		for (String gek : sTemp) {
			bw.write(gek + System.getProperty("line.separator"));
			if (visualsOn) {
				System.out.print("\n " + gek);
			}
			bw.flush();
			us.setArray(gek);
			// mf.setTextField(gek);
		}
		bw.close();
	}

	public void textImport() throws IOException {
		verschoon();
		bw = new BufferedWriter(new FileWriter(output, true));
		FileReader f1 = new FileReader(input);
		Scanner s1 = new Scanner(f1);
		s1.useDelimiter("\\s");

		File fw1 = new File(output);
		FileWriter pw1 = new FileWriter(fw1, true);

		while (s1.hasNext()) {
			String result = s1.next();
			if (!result.isEmpty()) {
				tester++;
				result = result.toLowerCase();
				temp = result.replaceAll(",[\\+\\.\\^:]\"", "");
				if (!(woordAanwezig(temp))) {
					bw.write(temp + "#" + System.getProperty("line.separator"));
					bw.flush();
					// System.out.println("APPENDED " + temp + " POSITIE " +
					// positie);
				}
			}
		}
		f1.close();
		s1.close();
		bw.close();
		pw1.close();
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
			BufferedWriter bw = new BufferedWriter(new FileWriter(fvan, true));
			FileReader f1 = new FileReader(fnaar);
			Scanner s1 = new Scanner(f1);
			while (s1.hasNext()) {
				temp1 = s1.next();
				StringBuilder sb = new StringBuilder(temp1);
				for (int index = 0; index < sb.length(); index++) {
					char c = sb.charAt(index);
					if (Character.isAlphabetic(c) || Character.isDigit(c)) {
						bw.write(c);
						bw.flush();
					}

				}
				bw.write(" ");
				bw.flush();
				String s = path + "schoneBestanden.obj";
				FileOutputStream fos = new FileOutputStream(s);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(bestandSchoon);
				fos.flush();
				oos.flush();
				oos.close();

			}
			s1.close();
			f1.close();
			bw.close();
			System.out.println("" + fvan.exists());
		}
	}

	public void setMyFrame(MyFrame my) {
	}

	public void setUitSchrijven(UitSchrijven ui) {
		us = ui;
	}

}
