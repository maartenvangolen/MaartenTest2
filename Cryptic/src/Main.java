import java.io.File;

public class Main {
	// TEST
	// TEST2
	// TEST3
	public static void main(String[] arg) throws Exception {
		String path = System.getProperty("user.home") + "/Desktop/raddestijds/";
		path = path.replaceAll("\\\\", "/");

		System.out.println("_-_-_-_-_-_-_-_-_-_-STARTED-_-_-_-_-_-_-_-_-_-_-_");
		MyFrame mf = new MyFrame();
		InLezen in = new InLezen(path);
		UitSchrijven us = new UitSchrijven(path);
		mf.setInLezen(in);
		mf.setUitSchrijven(us);
		in.setUitSchrijven(us);
		us.setMyFrame(mf);

		int aantalBestanden = 1;
		boolean excists = true;
		while (excists) {
			File f = new File(path + "/boek" + aantalBestanden + ".txt");
			if (f.exists()) {
				System.out.println("Boek" + aantalBestanden);
				aantalBestanden++;
			} else {
				excists = false;
			}

		}

		for (int i = 1; i < aantalBestanden; i++) {
			mf.setFiles(path + "boek" + i + ".txt", path + "data.txt");
		}
	}
}
