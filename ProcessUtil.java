package com.teknotry.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * ProcessUtil Class have method that execute external programs or commands and returns the output.
 * 
 * @author : Surendra (teknotry@gmail.com)
 * @version : 1.0
 * 
 */
public class ProcessUtil {

	public static String runCommandWithExec(String commandStr, Long timeOut) {
		String output = "";
		ProcessBuilder builder = null;
		Process process = null;
		BufferedReader stdInput = null;
		String line = "";

		try {

			String[] parameters = commandStr.split(" |=");

			List<String> parameterList = Arrays.asList(parameters);
			/*builder = new ProcessBuilder(parameterList)
					.redirectErrorStream(true);
			process = builder.start();*/

			process = Runtime.getRuntime().exec(commandStr);
			process.waitFor();
			long now = System.currentTimeMillis();
			long timeoutInMillis = timeOut;
			long finish = now + timeoutInMillis;
			while (isAlive(process)) {
				Thread.sleep(5);
				if (System.currentTimeMillis() > finish) {
					process.destroy();
					output = "Timedout: Process Got timed out after: " + timeoutInMillis
							+ " Milisecond";
				}

			}

			stdInput = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			int lineno=0;
			while ((line = stdInput.readLine()) != null) {

				//System.out.println("OUTPUT OF COMMAND: "+line);
				if(lineno==0){
					output = output + line;
				}else{
					
					output = output+"\n" + line;
				}
				lineno++;
			}

		} catch (Exception e) {
			output="Exception occured in running command: "+commandStr+" "+e.toString();
			return output;
		}
		return output;
	}
	
	

	/**
	 * @author Surendra (teknotry@gmail.com)
	 * @param commandStr
	 *            : command to be executed
	 * @param timeOut
	 *            : Timeout period for execution of command
	 * @returns : output of the command after execution
	 */
	public static String runCommandWithExec(String commandStr,String[] envs, Long timeOut) {
		String output = "";
		ProcessBuilder builder = null;
		Process process = null;
		BufferedReader stdInput = null;
		String line = "";

		try {

			String[] parameters = commandStr.split(" |=");

			List<String> parameterList = Arrays.asList(parameters);
			/*builder = new ProcessBuilder(parameterList)
					.redirectErrorStream(true);
			process = builder.start();*/

			process = Runtime.getRuntime().exec(commandStr,envs);
			process.waitFor();
			long now = System.currentTimeMillis();
			long timeoutInMillis = timeOut;
			long finish = now + timeoutInMillis;
			while (isAlive(process)) {
				Thread.sleep(5);
				if (System.currentTimeMillis() > finish) {
					process.destroy();
					output = "Timedout: Process Got timed out after: " + timeoutInMillis
							+ " Milisecond";
				}

			}

			stdInput = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			int lineno=0;
			while ((line = stdInput.readLine()) != null) {

				//System.out.println("OUTPUT OF COMMAND: "+line);
				if(lineno==0){
					output = output + line;
				}else{
					
					output = output+"\n" + line;
				}
				lineno++;
			}

		} catch (Exception e) {
			output="Exception occured in running command: "+commandStr+" "+e.toString();
			return output;
		}
		return output;
	}
	
	
	
	
	
	
	
	
	/**
	 * @author Surendra (teknotry@gmail.com)
	 * @param commandStr
	 *            : command to be executed
	 * @param timeOut
	 *            : Timeout period for execution of command
	 * @returns : output of the command after execution
	 */
	public static String runCommand(String commandStr, Long timeOut) {
		String output = "";
		ProcessBuilder builder = null;
		Process process = null;
		BufferedReader stdInput = null;
		String line = "";

		try {

			String[] parameters = commandStr.split(" |=");

			List<String> parameterList = Arrays.asList(parameters);
			builder = new ProcessBuilder(parameterList)
					.redirectErrorStream(true);
			process = builder.start();

			//process = Runtime.getRuntime().exec(commandStr);
			//process.waitFor();
			long now = System.currentTimeMillis();
			long timeoutInMillis = timeOut;
			long finish = now + timeoutInMillis;
			while (isAlive(process)) {
				Thread.sleep(5);
				if (System.currentTimeMillis() > finish) {
					process.destroy();
					output = "Timedout: Process Got timed out after: " + timeoutInMillis
							+ " Milisecond";
				}

			}

			stdInput = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			int lineno=0;
			while ((line = stdInput.readLine()) != null) {

				//System.out.println("OUTPUT OF COMMAND: "+line);
				if(lineno==0){
					output = output + line;
				}else{
					
					output = output+"\n" + line;
				}
				lineno++;
			}
			stdInput.close();

		} catch (Exception e) {
			output="Exception occured in running command: "+commandStr+" "+e.toString();
			return output;
		}
		return output;
	}

	public static boolean isAlive(Process p) {
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}

	}

	/**
	 * @author : Surendra (teknotry@gmail.com)
	 * @param commandStr
	 *            : Command to be executed
	 * @return The output of the command in String format.If not executed within
	 *         10000 mili-second then It will timeout.
	 */
	public static String executeCommand(String commandStr) {

		Long timeOut = 10000L;
		return runCommand(commandStr, timeOut);
	}

	
	
	/**
	 * @author : Surendra (teknotry@gmail.com)
	 * @param commandStr
	 *            : Command to be executed
	 * @return The output of the command in String format.If not executed within
	 *         10000 mili-second then It will timeout.
	 */
	public static String executeCommandWithExec(String commandStr) {

		Long timeOut = 10000L;
		return runCommandWithExec(commandStr, timeOut);
	}

	public static String executeCommandWithExec(String commandStr,String[] envs) {

		Long timeOut = 10000L;
		return runCommandWithExec(commandStr,envs, timeOut);
	}

	
	
	
	/**
	 * @author : Surendra (teknotry@gmail.com)
	 * @param commandStr
	 *            : Command to be executed.
	 * @param timeOut
	 *            : The time after which the process will forcefully exit.
	 * @return : The output of the command in String format.If not executed
	 *         within timeout period then It will timeout.
	 */
	public static String executeCommand(String commandStr, Long timeOut) {

		return runCommand(commandStr, timeOut);

	}

}
