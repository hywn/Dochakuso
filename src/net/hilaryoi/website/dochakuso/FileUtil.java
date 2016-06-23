package net.hilaryoi.website.dochakuso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class FileUtil {

	public static String getText(String path) throws IOException {

		return getText(new File(path));

	}

	public static String getText(File file) throws IOException {

		StringBuilder builder = new StringBuilder();

		// bufferedreader because windows is stupid
		Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(file), "UTF-8"));

		while (scanner.hasNextLine()) {
			builder.append(scanner.nextLine());
			builder.append("\n");

		}

		scanner.close();

		return builder.toString();

	}

	public static void write(String text, String path) throws IOException {

		File file = new File(path);

		// only writes if there is no existing file

		// FileWriter writer = new FileWriter(file, false);

		// bufferedwriter because windows is stupid

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

		writer.write(text);

		writer.close();

	}

}
