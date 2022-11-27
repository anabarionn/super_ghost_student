package studentCode;

import professorCode.IOManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MyIOManager<T> implements IOManager<T> {
    //a File object to store the path to the file
    private File pathToFile;

    //constructor initializes the path to the file to null
    public MyIOManager() {
        pathToFile = null;
    }

    @Override
    public void setPath(String path) throws FileNotFoundException, IllegalArgumentException {
        //creating a File object with the given path
        File file = new File(path);
        //checking if the file does not exist
        if (!file.exists()) {
            //throwing an exception because the path is not found
            throw new FileNotFoundException("File does not exist");
        }
        //otherwise checking if file is a directory
        else if (file.isDirectory()) {
            //throwing an exception because the path is a directory
            throw new IllegalArgumentException("Path is a directory");
        } else {
            //else setting file as pathToFile
            pathToFile = file;
        }
    }

    @Override
    public String getPath() {
        //returning empty string if path is not set
        if (pathToFile == null) {
            return "";
        }
        //else returning the path of the file
        return pathToFile.getPath();
    }

    @Override
    public T read() throws IOException, ClassNotFoundException, IllegalStateException {
        //throwing an exception if path is not set
        if (pathToFile == null) {
            throw new IllegalStateException("Path is not set");
        }
        //else creating an ObjectInputStream with the pathToFile
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathToFile));
        //reading the object stored in the file (this will throw IOException, ClassNotFoundException automatically if
        // the file is corrupted or the object is not of the correct type, so we don't need to do it manually)
        T object = (T) in.readObject();
        //closing the stream
        in.close();
        //returning the object
        return object;
    }

    @Override
    public void write(T object) throws IOException, IllegalStateException {
        //throwing an exception if path is not set
        if (pathToFile == null) {
            throw new IllegalStateException("Path is not set");
        }
        //else creating an ObjectOutputStream with the pathToFile
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathToFile));
        //writing the object to the file. this will throw IOException if the file is corrupted, so we don't need to do
        // it manually
        out.writeObject(object);
        //closing the stream
        out.close();
    }
}
