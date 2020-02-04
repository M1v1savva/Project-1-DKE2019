package build;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Log {
	private static ArrayList<String> filenames = new ArrayList<>();
	private static ArrayList<PrintWriter> files = new ArrayList<>(); 

	public static void add(String filename) throws IOException, UnsupportedEncodingException {
		if (filenames.indexOf(filename) != -1)
			throw new IOException();
		PrintWriter outp = new PrintWriter(filename, "UTF-8");
		filenames.add(filename);
		files.add(outp);
	}

	public static void write(String filename, String comment, Object obj, boolean newLine) throws IOException {
		if (filenames.indexOf(filename) == -1)
			throw new IOException();
		int id = filenames.indexOf(filename);
		PrintWriter outp = files.get(id);
		outp.print(comment + obj.toString());
		if (newLine)
			outp.println();
		else
			outp.print(" ");
	}

	public static void delete(String filename) throws IOException {
		if (filenames.indexOf(filename) == -1)
			throw new IOException();
		int id = filenames.indexOf(filename);
		PrintWriter outp = files.get(id);
		outp.close();
		filenames.remove(id);
		files.remove(id);
	}
}