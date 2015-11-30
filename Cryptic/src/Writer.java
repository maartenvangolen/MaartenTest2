import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Writer {
	private File bestand;
	private boolean append;

	public Writer(String location, boolean append) {
		bestand = new File(location);
		this.append = append;
	}

	public Writer(File file, boolean append) {
		bestand = file;
		this.append = append;
	}

	public Writer(String location) {
		bestand = new File(location);
		this.append = false;
	}

	public Writer(File file) {
		bestand = file;
		this.append = false;
	}

	public void bufferedWriter(String text) {
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

	public void bufferedWriter(int number) {
		bufferedWriter("" + number);
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
