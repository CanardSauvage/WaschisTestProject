package de.waschnick.lib;

public class CswFile {

    public static CswFile fromClasspath(String path) {
        return new CswFile();//CswFile.class.getClassLoader().get
    }


    public String readLine() {
        // FIXME
        return null;
    }
}
