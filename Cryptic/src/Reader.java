import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {

	private File bestand;

	public Reader(String l) {
		bestand = new File(l);
	}

	public Object readObject() {
		Object objecten = null;
		try {
			FileInputStream fis = new FileInputStream(bestand);
			ObjectInputStream ois = new ObjectInputStream(fis);
			objecten = ois.readObject();
			fis.close();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objecten;
	}

	public boolean exists() {
		return bestand.exists();
	}
}
