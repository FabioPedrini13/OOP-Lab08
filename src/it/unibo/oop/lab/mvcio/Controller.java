package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {
    private File currentFile = new File(System.getProperty("user.home")
            + System.getProperty("file.separator") + "output.txt");
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    public void setCurrentFile(final File file) {
        this.currentFile = file;
    }
    public File getCurrentFile() {
        return this.currentFile;
    }
    public String getPath() {
        return this.currentFile.getAbsolutePath();
    }
    public void writeOnFile(final String text) {
        try (PrintStream ps = new PrintStream(this.getPath())) {
            ps.println(text);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
