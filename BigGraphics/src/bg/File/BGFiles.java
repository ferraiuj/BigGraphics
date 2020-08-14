package bg.File;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.jacob.serialize.serialization.array.Array;
import com.jacob.serialize.serialization.array.arraytypes.ArrayInt;
import com.jacob.serialize.serialization.database.DataBase;
import com.jacob.serialize.serialization.object.Obj;

public class BGFiles {

	private FileWriter writer;
	private FileOutputStream output;
	private OutputStreamWriter writeOutput;
	private BufferedWriter buffer; 
	private DataBase database;
	private Array array;
	private Obj object;
	private static final File FOLDER = new File("F:\\Users\\Jacob\\workspace\\BigGraphics");
	private static final String EXTENSION = ".*\\.pd";
	private static final String FILE = "File.pd";
	private static final String path = "PlayerData.pd";
	
	public BGFiles() {
	
	}
	public void createDatabase(int[] pixels) {
		database = new DataBase(path);
		array = new ArrayInt(path, pixels);
		object = new Obj(path);
		
		object.addArray(array);
		database.addObject(object);
		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);
		
	}
	protected static void saveToFile(String path, byte[] data) {
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writePixelArrayToFile(int[] pixels) {
		for(int pixel = 0; pixel < pixels.length; pixel++) {
			
		}
	}
	public List<String> listAllFilesWith(File folder) {

		List<String> paths = new ArrayList<String>();

		for (final File f : folder.listFiles()) {

			if (f.isFile()) {
				if (f.getName().matches(EXTENSION)) {
					paths.add(f.getName());
				}
			}
		}
		return paths;
	}

	public void listAllFiles(final File folder) {

		for (final File f : folder.listFiles()) {
			System.out.println(f.getName() + System.getProperty("user.dir"));
		}
	}

	public String getFullDirectory() {

		String path = System.getProperty("user.dir");
		return path;
	}
}
