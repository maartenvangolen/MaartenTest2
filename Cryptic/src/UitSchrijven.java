import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UitSchrijven {

	private String temp = "", input = "", output = "", key = "amsterdam", path = "";
	private ArrayList<String> s = new ArrayList<String>();
	private ArrayList<String> sTemp = new ArrayList<String>();
	private ArrayList<String> rij1;
	private ArrayList<String> rij2 = new ArrayList<String>();
	private MyFrame mf;

	public UitSchrijven(String path) {
		rij1 = getAlphabet();
		this.path = path;
	}

	public void setFiles(String inputs, String outputs) {
		input = inputs;
		output = outputs;
	}

	public void setArray(String in) {
		s.add(in);
	}

	private int randomGetal(int i) {
		return 1 + (int) (i * Math.random());
	}
	
	private ArrayList<String> getAlphabet(){
		ArrayList<String> rij1 = new ArrayList<String>();
		String[] temp = "! a b c d e f g h i j k l m n o p q r s t u v w x y z".split("\\s+");
		for (int i = 0; i <= 26; i++) {
			rij1.add(temp[i]);
		}
		return rij1;
	}

	@SuppressWarnings("resource")
	public void coderen(boolean visualsOn) throws IOException {
		FileReader f1 = new FileReader(path + "data.txt");
		Scanner s1 = new Scanner(input);
		s1.useDelimiter("\\s");
		ArrayList<String> sCheck = new ArrayList<String>();
		int i = 0, y = 0;
		String temps = "", in = "";
		while (s1.hasNext()) {
			// maakt de input zin aan per woord
			temps = s1.next();
			in = temps;
			sCheck.add(temps);
			if (visualsOn) {
				System.out.println("*you speak upon the rabbits:" + temps + "*");
			}
		}
		s1.close();

//		s1 = new Scanner(f1);
//		s1.useDelimiter("\r\n");
//		sTemp.clear();
//		// maakt de sleutel aan per regel
//		// sTemp bestaat uit één woord + heel veel losse getallen:hoi 334 346 12
//		while (s1.hasNext()) {
//
//			temp = s1.next();
//			temps = temp.replaceAll("[0-9]", "").replace(" ", "");
//			s.add(temp);
//			sTemp.add(temps);
//			if (visualsOn) {
//				System.out.println("*you feed your hamsters:" + temps + "*");
//			}
//
//		}
		
		createKey(f1 , visualsOn);

		while (sCheck.size() > i) {
			if (visualsOn) {
				System.out.println("\n*the little buggers have found this:" + sCheck.get(i) + "*");
			}
			y = sTemp.indexOf(sCheck.get(i));
			if (!(y == -1)) {
				s.add("" + y);
				// hier worden alle getallen
				String[] lol = s.get(y).split("\\s+");
				if (visualsOn) {
					System.out.println(lol[randomGetal(lol.length - 1)]);
				}
				mf.setTextField(mf.getTextField() + " " + lol[randomGetal(lol.length - 1)]);
			} else {
				// -------------------------onbekend-woord-coderen------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new FileReader(path + "key/vic.txt"));
				String line;
				Scanner d1 = new Scanner(br);
				Scanner d2 = new Scanner(sCheck.get(i));
				Scanner d3 = new Scanner(key);
				// System.out.println(in);
				d1.useDelimiter("\\s");
				d2.useDelimiter("\\s*");
				d3.useDelimiter("\\s*");
				String temp = "", tamp = "", tomp = "";
				in = "";
				int q = 0;
				int w = 0;
				int e = 0;
				int r = 0;

				while (d2.hasNext()) {
					temp = d2.next();
					if (!d3.hasNext()) {
						d3.close();
						d3 = new Scanner(key);
						d3.useDelimiter("\\s*");
					}
					tamp = d3.next();
					e = rij1.indexOf(temp);
					w = rij1.indexOf(tamp);
					q = e + w;
					if (q >= 27) {
						q = q - 26;
					}
					tomp = rij1.get(q);

					if (visualsOn) {
						System.out.println(e + " + " + w + " = " + q + " ---- " + tomp);
					}
					rij2.clear();
					while ((line = br.readLine()) != null) {

						String[] kaas = line.split("\\s+");
						for (String stempor : kaas) {
							rij2.add(stempor);
						}
						w = rij2.indexOf(tomp);
						if (w != -1) {
							e = Integer.parseInt(rij2.get(rij2.size() - 4));

							r = w - (rij2.size() - 4);
							switch (r) {
							case 0:
							case 1:
								in += "0" + e;
								break;
							case 2:
								e += 20;
								in += e;
								break;
							case 3:
								e += 60;
								in += e;
								break;
							default:
								break;
							}
							// System.out.println(e);

							break;
						}
					}

					br.close();
					br = new BufferedReader(new FileReader(path + "key/vic.txt"));
				}
				mf.setTextField(mf.getTextField() + " 0" + in);
				// ----------------------------------------------------------------------------------------------------------------------------------

			}

			i++;
		}
		if (visualsOn) {
			System.out.println("\n*they left this behind when they started walking*\n" + mf.getTextField());
		}

		f1.close();
		s1.close();
	}

	@SuppressWarnings("resource")
	public void translate(boolean visualsOn) throws IOException {
		FileReader f1 = new FileReader(path + "data.txt");
		Scanner s1 = new Scanner(input);
		s1.useDelimiter("\\s");
		ArrayList<String> sCheck = new ArrayList<String>();
		int i = 0;
		String temps = "";
		while (s1.hasNext()) {
			// maakt de input zin aan per woord
			temps = s1.next();
			sCheck.add(temps);
			if (visualsOn) {
				System.out.println("*the mice recall :" + temps + "*");
			}
		}
		s1.close();

//		s1 = new Scanner(f1);
//		s1.useDelimiter("\r\n");
//		sTemp.clear();
//		s.clear();
//		// maakt de sleutel aan per regel
//		// sTemp bestaat uit één woord + heel veel losse getallen:hoi 334 346 12
//		while (s1.hasNext()) {
//
//			temp = s1.next();
//			temps = temp.replaceAll("[0-9]", "").replace(" ", "");
//			s.add(temp);
//			sTemp.add(temps);
//			if (visualsOn) {
//				System.out.println("*your mice are named: " + temps + "*");
//			}
//
//		}

		createKey(f1 , visualsOn);
		
		
		while (sCheck.size() > i) {
			if (visualsOn) {
				System.out.println("\n*you repeat the mice: " + sCheck.get(i) + "*");
			}
			boolean stop = false;
			if (sCheck.get(i).startsWith("0")) {
				// -----------------------------------------------------------------------------------------------------------------
				BufferedReader br = new BufferedReader(new FileReader(path + "key/vic.txt"));
				String line;
				Scanner d1 = new Scanner(br);
				Scanner d2 = new Scanner(sCheck.get(i));
				Scanner d3 = new Scanner(key);
				d1.useDelimiter("\\s");
				d2.useDelimiter("\\s*");
				d3.useDelimiter("\\s*");
				String block = "", tamp = "", tomp = "";
				// String in = "";
				int q = 0;
				int w = 0;
				int e = 0;
				int r = 0;
				d2.next();
				rij2.clear();
				// in deze loop word de vic sleutel ingelezen
				while ((line = br.readLine()) != null) {
					// # # 2 6
					// 7 $ b p
					// 5 e c q
					// 2 t d $
					// 1 a f u
					// 8 $ g v
					// 0 o h w
					// 3 n j x
					// 6 r k y
					// 9 i l z
					// 4 s m $
					if (!line.startsWith("#")) {

						String[] kaas = line.split("\\s+");
						for (String stempor : kaas) {
							rij2.add(stempor);
						}
					}
				}
				String woord = "";

				// hier word per 2 getallen de bijbehorende code aangemaakt
				while (d2.hasNext()) {
					block = d2.next() + d2.next();

					w = rij2.indexOf("" + block.charAt(1));
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					// -om-een-of-andere-reden-word-r-48-getallen-groter-dan-het-eigenlijk-is
					// -er-is-hier-geen-duidelijke-reden-voor,-mogelijk-dat-dit-niet-een-
					// -consistent-getal-is,-waardoor-de-code-voortrurend-moet-worden-aangepast
					// -liefst-dit-op-lossen-in-latere-versies
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					r = (block.charAt(0)) - 48;

					if (!(w == -1)) {
						switch (r) {
						case 0:
							tamp = rij2.get((w + 1));
							break;
						case 2:
							tamp = rij2.get((w + 2));
							break;
						case 6:
							tamp = rij2.get((w + 3));
							break;
						default:
							break;
						}
						// System.out.println(r + "==" + block.charAt(0));
						// in += tamp;
						if (!d3.hasNext()) {
							d3.close();
							d3 = new Scanner(key);
							d3.useDelimiter("\\s*");
						}
						tomp = d3.next();
						e = rij1.indexOf("" + tamp);
						w = rij1.indexOf("" + tomp);
						q = e - w;
						if (q <= 0) {
							q = q + 26;
						}
						tomp = rij1.get(q);
						if (visualsOn) {
							System.out.println(e + " - " + w + " = " + q + " ---- " + tomp);
						}
						woord += tomp;
					}

				}
				if (!woord.equals("")) {
					if (visualsOn) {
						System.out.println("*They remembered!:" + woord + "*");
					}
					mf.setTextField(mf.getTextField() + " " + woord);
					woord = "";
					// rij2.clear();
					// br.close();
					// br = new BufferedReader(new FileReader(
					// "C:/Users/Maarten/Desktop/raddestijds/key/vic.txt"));
					// ------------------------------------------------------------------
				}
			} else {

				for (String w : s) {
					String[] lol = w.split("\\s+");
					for (String e : lol) {
						if (e.equals(sCheck.get(i))) {
							if (visualsOn) {
								System.out.println("*They remembered!:" + lol[0] + "*");
							}
							mf.setTextField(mf.getTextField() + " " + lol[0]);
							stop = true;
							break;
						}
					}
					if (stop) {
						break;
					}
				}
			}

			i++;
		}
		if (visualsOn) {
			System.out.println("\n*the mice wrap up their story with:*\n" + mf.getTextField());
		}

		f1.close();
		s1.close();
	}
	
	private void createKey(FileReader f1 , boolean visualsOn){
		String temps;
		Scanner s1 = new Scanner(f1);
		s1.useDelimiter("\r\n");
		sTemp.clear();
		s.clear();
		// maakt de sleutel aan per regel
		// sTemp bestaat uit één woord + heel veel losse getallen:hoi 334 346 12
		while (s1.hasNext()) {

			temp = s1.next();
			temps = temp.replaceAll("[0-9]", "").replace(" ", "");
			s.add(temp);
			sTemp.add(temps);
			if (visualsOn) {
				System.out.println("*your mice are named: " + temps + "*");
			}

		}
	}

	public void setMyFrame(MyFrame my) {
		mf = my;
	}
}
