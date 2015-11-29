import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Writer {
	private File bestand;

	public Writer(String location) {
		bestand = new File(location);
	}

	public Writer(File file) {
		bestand = file;
	}

	public void bufferedWriter(String text, boolean append) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(bestand, append));
			bw.write(text);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void bufferedWriter(String text) {
		bufferedWriter(text, false);
	}

	public void bufferedWriter(int number) {
		bufferedWriter("" + number);
	}

	public void bufferedWriter(int number, boolean append) {
		bufferedWriter("" + number, append);
	}

	public void objectOutputStream(Object object) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bestand));
			oos.writeObject(object);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
