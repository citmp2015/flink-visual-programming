package org.tuberlin.de.deployment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A helper class to execute shell commands in the jetty environment.
 * Used in the deployment process, e.g. for the maven invocation.
 */
public class ExecuteShell {

    /**
     * Executes the given shell command in the current folder, that is the projects base folder (e.g. where the pom.xml lives)
     * @param command The command to execute
     * @return The output of the command execution
     */
    public static String executeCommand(String command) {

        try {
            return execute(Runtime.getRuntime().exec(command));
        } catch (IOException e) {
            return e.getLocalizedMessage();
        }
    }

    /**
     * Executes the given shell command in a directory specified by the folder parameter.
     * @param command The command to execute
     * @param folder The folder where the command should be executed
     * @return The output of the command execution
     */
    public static String executeCommand(String command, File folder) {
        try {
            return execute(Runtime.getRuntime().exec(command, null, folder));
        } catch (IOException e) {
            return e.getLocalizedMessage();
        }
    }

    private static String execute(Process process) {
        StringBuilder output = new StringBuilder();

        try {
            process.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}