import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public <T> List<T> readListFromFile(Class<T> c, String fileName) {
		List<T> listz = new ArrayList<T>();
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis);) {
			listz = (List<T>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listz;
	}

	void writeToFile(String fileName, List<?> list) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(list);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
