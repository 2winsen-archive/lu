package lv.parcels;

import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

public class Automatization {

	public static final int TIMEOUT = 5000;

	public static final String INPUT_FILE_NAME = "parcels.in";
	public static final String OUTPUT_FILE_NAME = "parcels.out";

	public static void run(String programName, String testsDir, String resultsDir, JList list) {
		int sucesses = 0;
		int fails = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(programName + "\n\n");

		File folder = new File(testsDir);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				prepareTestFile(listOfFiles[i]);
				Process process = exceuteParcels(programName);
				File res = new File(OUTPUT_FILE_NAME);
				try {
					int time = 0;
					while (!res.exists() && time < TIMEOUT) {
						Thread.sleep(10);
						time += 10;
					}
					Thread.sleep(200);
					if (process != null) {
						process.destroy();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (compareFiles(res, getNecessaryResultFile(listOfFiles[i], resultsDir))) {
					sucesses++;
					documentResults(list, listOfFiles[i].getName() + " " + "SUCCESS");
					sb.append(listOfFiles[i].getName() + " " + "SUCCESS" + "\n");
				} else {
					fails++;
					documentResults(list, listOfFiles[i].getName() + " " + "FAILURE");
					sb.append(listOfFiles[i].getName() + " " + "FAILURE" + "\n");
				}
				rollBackTestFile(listOfFiles[i]);
			}
		}
		if (fails == 0) {
			documentResults(list, "TESTS SUCCESSFUL");
			sb.append("TESTS SUCCESSFUL");
		} else {
			documentResults(list, "TESTS FAILED");
			sb.append("TESTS FAILED");
		}
		try {
			FileUtils.writeStringToFile(new File("report.txt"), sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void prepareTestFile(File file) {
		try {
			FileUtils.copyFileToDirectory(file, new File("."));
		} catch (IOException e) {
			e.printStackTrace();
		}

		File copiedFile = new File(file.getName());
		copiedFile.renameTo(new File(INPUT_FILE_NAME));
	}

	public static Process exceuteParcels(String name) {
		try {
			Runtime rt = Runtime.getRuntime();
			Process process = rt.exec(name);
			return process;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public static void rollBackTestFile(File file) {
		File parcelsIn = new File(INPUT_FILE_NAME);
		if (parcelsIn.exists())
			parcelsIn.delete();
		File parcelsOut = new File(OUTPUT_FILE_NAME);
		if (parcelsOut.exists())
			parcelsOut.delete();
	}

	public static boolean compareFiles(File result, File necessaryResult) {
		String resultString = null;
		try {
			resultString = StringUtils.trim(FileUtils.readFileToString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String necessaryResultString = null;
		try {
			necessaryResultString = StringUtils.trim(FileUtils.readFileToString(necessaryResult));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return StringUtils.equalsIgnoreCase(resultString, necessaryResultString);
	}

	public static void documentResults(JList list, String text) {
		DefaultListModel model = (DefaultListModel) list.getModel();
		model.addElement(text);
	}

	public static File getNecessaryResultFile(File file, String dir) {
		int number = getFileNameNumber(file);
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (number == getFileNameNumber(listOfFiles[i]))
					return listOfFiles[i];
			}
		}
		return null;
	}

	/**
	 * Getting number from file name for instance parcels.o6 will return 6
	 * 
	 * @param file
	 * @return
	 */
	public static int getFileNameNumber(File file) {
		return Integer.valueOf(FilenameUtils.getExtension(file.getName()).substring(1));
	}
	
}
