package com.joerichard.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyFile {

	public static void main(String[] args) throws IOException {
		File fromDirectory = new File(args[0] + "/" + args[2]);
		File toDirectory = new File(args[1] + "/" + args[2]);
		
		// Clear to directory of old war file
		if(toDirectory.exists()) {
			toDirectory.delete();
		}
		
		// Copy war file to dropins directory
		FileUtils.copyFile(fromDirectory, toDirectory);
	}

}
