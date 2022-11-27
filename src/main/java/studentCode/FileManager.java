package studentCode;

import professorCode.AbstractFileMonitor;
import professorCode.FileTextReader;
import professorCode.FileTextWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileManager extends AbstractFileMonitor implements FileTextReader, FileTextWriter {

    String filePath;

    public FileManager(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public void setFilePath(String path) {
        filePath = path;
        if (filePath == null || filePath.equals("") || filePath.trim().equals("")) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getFilePath() throws IllegalStateException {
        return filePath;
    }

    @Override
    public void writeToFile(String string, String path) throws IOException, IllegalArgumentException {


        BufferedWriter myWriter = new BufferedWriter(new FileWriter(path));

        if (string == null || string.equals("") || string.trim().equals("")) {
            myWriter.close();
            throw new IllegalArgumentException();
        }
        else{

            myWriter.write(string);
            myWriter.newLine();

        }
        myWriter.flush();
        myWriter.close();


    }

    @Override
    public String readText(String path) throws IOException {
        String ot = "";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            ot += st + "\n";
        }
        br.close();
        return ot;
    }

    @Override
    public Set<String> getAllLines(String path) throws IOException {
        Set<String> set = new HashSet<String>();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            set.add(st);
        }
        br.close();

        return set;
    }

    @Override
    public String getLastLine(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        String nextLine;
        while ((nextLine = br.readLine()) != null) {
            line = nextLine;
        }
        br.close();
        return line;
    }
}
