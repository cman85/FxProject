package sample.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FileUtil{

	private static Map<String, String> users;
	private static File HOME_FILE = new File(System.getProperty("user.home") + File.separator + "MediaWatcher");
	private static File usersFile;

	public static void init() throws IOException{
		if(!HOME_FILE.exists()){
			HOME_FILE.mkdir();
			Files.setAttribute(HOME_FILE.toPath(), "dos:hidden", true);
		}
		usersFile = new File(HOME_FILE, "users.mdw");
		if(!usersFile.exists())
			usersFile.createNewFile();
		users = readKeyValue(readFile(usersFile));
	}

	public static void close(){

	}

	public static String readFile(File file){
		Scanner scanner;
		try{
			scanner = new Scanner(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return "";
		}
		StringBuilder builder = new StringBuilder();

		while(scanner.hasNextLine()){
			builder.append(scanner.nextLine());
		}
		return builder.toString();
	}

	public static Map<String, String> readKeyValue(String string){
		Map<String, String> map = new HashMap<String, String>();
		String[] pairs = string.split(";");

		for(String pair : pairs){
			String[] vals = pair.split(":");
			if(vals.length == 2)
				map.put(vals[0], vals[1]);
		}

		return map;
	}

	public static boolean isValid(String user, String pass){
		if(user == null || user.isEmpty() || pass == null || pass.isEmpty())
			return false;
		String pw = users.get(user);
		if(pw != null)
			return pw.equals(pass);
		return false;
	}

	public static void addUser(String user, String pass) throws IOException{
		if(users.containsKey(user))
			return;
		users.put(user, pass);
		FileWriter fw = new FileWriter(usersFile.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
		for(Entry<String, String> userCombo : users.entrySet()){
			bw.write(userCombo.getKey() + ":" + userCombo.getValue() + ";\n");
		}
		bw.close();
	}
}
