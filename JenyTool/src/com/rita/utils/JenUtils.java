package com.rita.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rita.conts.Commanconstants;

public class JenUtils {

	volatile StringBuilder pathResultBuilder = new StringBuilder();

	public String copy(String sourcePath, String destinationPath) {

		try {
			Path src = Paths.get(sourcePath);
			Path dest = Paths.get(destinationPath);
			return Files.copy(src, dest).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Commanconstants.ERROR;
		}
	}

	public String search(String name) {

		for (File fs : File.listRoots()) {
			SearchTh searchTh = new SearchTh(fs.getAbsolutePath(), name);
			Thread th = new Thread(searchTh);
			th.start();
		}

		return pathResultBuilder.toString();

	}

	/**
	 * @param path
	 * @param fileName
	 * @return
	 */
	public String creat(String path, String fileName) {

		File file = new File(path + fileName);
		String result = "";

		if (!file.exists()) {

			if (file.getAbsolutePath().endsWith(".")) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				file.mkdir();
			}

			result = Commanconstants.FILE_CREATED;

		} else {
			result = Commanconstants.FILE_ALEADY_EXISIT;
		}

		return result;
	}

	
	
	
	/**
	 * @param path
	 * @return
	 */
	public String delete(String path) {
		File f = new File(path);

		if (f.delete()) {
			return Commanconstants.DELETE_JOB;
		} else {
			return Commanconstants.NOT_DELETED;
		}

	}

	
	/**
	 * @param path
	 * @param filename
	 * @param pathBuilder
	 * @return
	 */
	private String searchAlgo(String path, String filename, StringBuilder pathBuilder) {

		File f = new File(path);

		File[] files = f.listFiles();

		if (files != null)
			for (File file : files) {

				if (file.isDirectory()) {
					searchAlgo(file.getAbsolutePath(), filename, pathBuilder);
				}

				if (file.isFile()) {
					if (file.getAbsolutePath().toUpperCase().contains(filename.toUpperCase())) {
						System.out.println(pathBuilder.toString());
						pathBuilder.append("\n" + file.getAbsolutePath());
					}
				}

			}

		return pathBuilder.toString();

	}

	class SearchTh implements Runnable {

		String drivePath = "";
		String fileName = "";
		StringBuilder pathBuilder = new StringBuilder();

		public SearchTh(String drivePath, String fileName) {
			this.drivePath = drivePath;
			this.fileName = fileName;
		}

		@Override
		public void run() {
			pathResultBuilder.append(searchAlgo(drivePath, fileName, pathBuilder));

		}

	}

}
