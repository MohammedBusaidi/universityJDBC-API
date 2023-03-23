import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class File extends APIConsumer {
	public void dumpToFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream("uni.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(uni);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in uni.ser");
		} catch (Exception i) {
			i.printStackTrace();
		}

	}

	public void retriveFromFile() {

	}
}
