package Old;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

/**
 * <code>Serialiser</code> class create one file from group of files and provide
 * access to them Stream format:
 * {int_length:filename:DATA}{int_length:filename:DATA
 * }....{int_length:filename:DATA}
 * 
 * @since 1.0
 * @author Vojtech 'Rain' Vladyka
 * 
 */
public class Serialiser {
	private String path;

	/**
	 * Default c'tor
	 * 
	 * @param path
	 *            define where should be data stored or from where will be read
	 */
	public Serialiser(String path) {
		this.path = path;
	}

	/**
	 * Data packer create packed file from file group Stream format:
	 * {int_length:
	 * filename:DATA}{int_length:filename:DATA}....{int_length:filename:DATA}
	 * 
	 * @param filePaths
	 *            String array of paths to files to pack
	 * @param type
	 *        	  d for datafile, t for textfile
	 * @throws IOException
	 *             thrown if files are not accessible or output file cannot be
	 *             written
	 */
	public void pack(String[] filePaths, char type) throws IOException {
		if(type != 'd'&&type != 't'){
			System.err.println("Unsupported data type. Trying as datafile.");
			type = 'd';
		}
		RandomAccessFile w = new RandomAccessFile(path, "rw");
		RandomAccessFile r;
		BufferedReader fr;
		String encoded = "";
		System.out.println(filePaths.length);
		for (int i = 0; i < filePaths.length; i++) {
			File f = new File(filePaths[i]);
			if(type == 'd'){
				r = new RandomAccessFile(filePaths[i], "r");
				byte[] data = new byte[(int) r.length()];
				int index=0;
				while (true) {
					try {
						data[index] = r.readByte();
						index++;
					} catch (EOFException ex) {
						break;
					}
					//w.writeByte(data[index-1]);
				}
				r.close();
				encoded = DatatypeConverter.printBase64Binary(data);
			}
			if(type == 't'){
				fr = new BufferedReader(new FileReader(filePaths[i]));
				String dataStr = "";
				while(fr.ready())
					dataStr = dataStr+fr.readLine()+'\n';
				fr.close();
				encoded = DatatypeConverter.printBase64Binary(dataStr.getBytes());
			}
			
			
			w.writeBytes("{");
			w.writeInt(encoded.length());
			w.writeBytes(":" + f.getName() + ":");
			w.writeBytes(encoded);
			w.writeBytes("}");
		}
		w.close();
		System.out.println("Data was successfully packed");
	}

	/**
	 * Unpacker unpack packed data to specified directory as original data files
	 * 
	 * @param directory
	 *            specifies where should be data unpacked
	 * @throws IOException
	 *             throw if datafile is not accessible or output location is no
	 *             writable
	 */
	public void unpack(String directory) throws IOException {
		miner(directory, 'u');
	}

	/**
	 * getData method provide <code>ByteArrayInputStream</code>
	 * 
	 * @param name
	 *            name of wanted image form datafile
	 * @return <code>ByteArrayInputStream</code> of image
	 * @throws IOException
	 *             throw if datafile is not accessible
	 */
	public ByteArrayInputStream getData(String name) throws IOException {
		return (ByteArrayInputStream) miner(name, 'i');
	}

	/**
	 * Miner method make all hard work for you. Stream format:
	 * {int_length:filename
	 * :DATA}{int_length:filename:DATA}....{int_length:filename:DATA}
	 * 
	 * @param param
	 *            String param such as [u]directory to extract, [i]filename to
	 *            find, [m] nothing
	 * @param mode
	 *            [u] for unpack, [i] for image extraction, [m] map extraction
	 * @return [u]null, [i]ByteArrayInoputStream
	 * @throws IOException
	 */
	private Object miner(String param, char mode){
		if (mode != 'u' && mode != 'i' && mode != 'm') // mode check
			return null;
		RandomAccessFile r;
		try {
			r = new RandomAccessFile(path, "r");
		} catch (FileNotFoundException e) {
			return null;
		}
		RandomAccessFile w = null;
		HashMap<String, Object> map = new HashMap<>();

		while(true){
			try{
				if( r.readByte() != '{'){
					System.err.println("Bad datafile format: opening bracket");
					return null;
				}
				int size = r.readInt();
				if(r.readByte() != ':'){
					System.err.println("Bad datafile format: comma between size and filename");
					return null;
				}
				String filename = "";
				char c;
				while((c=(char) r.readByte())!=':'){
					filename+=c;
				}	
				if(mode=='i'&&!filename.equals(param)){
					r.skipBytes(size+1);
				}
				if(mode == 'm'||mode=='u'||(mode=='i'&&filename.equals(param))){
					String data="";
					while((c=(char) r.readByte())!='}'){
						data+=c;
					}
					byte[] decoded = DatatypeConverter.parseBase64Binary(data);
					if(mode == 'u'){
						w = new RandomAccessFile(param+filename, "rw");
						w.write(decoded);
						w.close();
					}
					if(mode == 'i'){
						r.close();
						return new ByteArrayInputStream(decoded);
					}
					if(mode == 'm'){
						map.put(filename, decoded);
					}
				}

			}catch(EOFException eofex){
				break;
			}catch(IOException iox){
				System.err.println(iox.getCause());
			}
		}
		try {
			r.close();
		} catch (IOException e) {
			System.err.println(e.getCause());
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> mapUnpack() throws IOException {
		return (Map<String, Object>) miner(null, 'm');
	}

	/**
	 * Data packer create packed file from file group Stream format:
	 * {int_length:
	 * filename:DATA}{int_length:filename:DATA}....{int_length:filename:DATA}
	 * 
	 * @param directory
	 *            directory to search
	 * @param key
	 *            key to add
	 * @param type
	 *            d for datafile, t for textfile
	 * @throws IOException
	 */
	public void pack(String directory, String key, char type) throws IOException {
		System.out.println("Files to add to pack:");
		Path f = Paths.get(directory);
		ArrayList<Path> c = new ArrayList<Path>();
		addTree(f, c);
		int count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).toString().contains("." + key)) {
				System.out.println(c.get(i));
				count++;
			}
		}
		String[] filePaths = new String[count];
		count = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).toString().contains("." + key)) {
				filePaths[count] = c.get(i).toString();
				count++;
			}
		}
		pack(filePaths, type);
	}

	private static void addTree(Path directory, Collection<Path> all)
			throws IOException {
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(directory)) {
			for (Path child : ds) {
				all.add(child);
				if (Files.isDirectory(child)) {
					addTree(child, all);
				}
			}
		}
	}
}
