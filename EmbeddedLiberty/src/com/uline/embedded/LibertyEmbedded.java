package com.uline.embedded;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;

import com.ibm.wsspi.kernel.embeddable.Server;
import com.ibm.wsspi.kernel.embeddable.ServerBuilder;

public class LibertyEmbedded {

	private String serverName="defaultServer";
	private File userDir;
	private File outputDir;
	private Server embedded;

	public LibertyEmbedded() {
	}
	
	private void setup() throws InterruptedException, ExecutionException {
		if (userDir==null) {
			userDir = new File("C:/wlp/usr/Embedded"); 
		}
		if (outputDir==null) {
			outputDir = new File("C:/wlp/usr/Embedded/servers"); 
		}
		ServerBuilder sb = new ServerBuilder();
		//embedded = sb.setName(serverName).setUserDir(userDir).setOutputDir(outputDir).build();
		embedded = sb.setName(serverName).build();
	}
	
	/**
	 * RunEmbeddedServer
	 **
	 * Default properties are used {create version}
	 * @return boolean true if server is running
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public boolean startEmbeddedServer() throws InterruptedException, ExecutionException {
		setup();
		System.out.println("******************************************************************");
		System.out.println("********  Embedded Liberty Server STARTING");
		System.out.println("******************************************************************");
		Future<Server.Result> startReturnCode = embedded.start("");
		Server.Result resultCode = startReturnCode.get();
		if(embedded.isRunning()) {
			System.out.println("********  Liberty Server is Running.");
			System.out.println("********  Ready to run UNIT TEST ********");
			return true;
		}
		else {
			System.out.println("********  Liberty Server is NOT Running.");
			System.out.println("********  Return Code : " + resultCode.getReturnCode());
			System.out.println("********  Exception   : " + resultCode.getException());
			return false;
		}
	}
	
	/**
	 * RunEmbeddedServer 
	 **
	 * Passed in properties are used with the defaults {create version}+props
	 * @param props
	 * @return boolean true if server is running
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public boolean startEmbeddedServer(String props) throws InterruptedException, ExecutionException {
		setup();
		System.out.println("******************************************************************");
		System.out.println("********  Embedded Liberty Server STARTING");
		System.out.println("******************************************************************");
		Future<Server.Result> startReturnCode = embedded.start("create version " + props);
		Server.Result resultCode = startReturnCode.get();
		if(embedded.isRunning()) {
			System.out.println("********  Liberty Server is Running.");
			System.out.println("********  Ready to run UNIT TEST ********");
			return true;
		}
		else {
			System.out.println("********  Liberty Server is NOT Running.");
			System.out.println("********  Return Code : " + resultCode.getReturnCode());
			System.out.println("********  Exception   : " + resultCode.getException());
			return false;
		}
	}
	
	/**
	 * StopEmbeddedServer
	 **
	 * Stop Server
	 * @return boolean true if server has stopped
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public boolean stopEmbeddedServer() throws InterruptedException, ExecutionException {
		Future<Server.Result> stopReturnCode = embedded.stop();
		Server.Result resultCode = stopReturnCode.get();
		if(embedded.isRunning()) {
			System.out.println("********  Liberty Server has NOT stopped.");
			System.out.println("********  Return Code : " + resultCode.getReturnCode());
			System.out.println("********  Exception   : " + resultCode.getException());
			return false;
		}
		else {
			System.out.println("********  Liberty Server has stopped.");
			System.out.println("******************************************************************");
			System.out.println("********  Embedded Liberty Server STOPPING");
			System.out.println("******************************************************************");
			return true;			
		}
	}
	
	/**
	 * deployWar
	 **
	 * Deploy a war file to the dropins directory on the server
	 * @param warName
	 * @return boolean true if file was deployed
	 */
	public boolean deployWar(String warPath, String warName) {
		File projectWar = new File(warPath + "/" + warName);
		File deployDir = new File("C:/wlp/usr/servers/defaultServer/dropins/" + warName);
		if(projectWar.exists()) { // if warName exist copy file
			try { 
				FileUtils.copyFile(projectWar, deployDir);
				if(deployDir.exists()) {
					projectWar.delete();
					return embedded.isRunning();
				} else { return false; }
			} catch (IOException e) { 
					e.printStackTrace(); 
					return false;
				}
		}	else { return false; }
	}
	
}
