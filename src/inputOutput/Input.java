/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import exceptions.IncorrectInputFileFormat;
import java.io.*;
import java.util.Scanner;
import map.Map;

/**
 *
 * @author Piotrek
 */
public class Input {
    private File mapFile = null;
    private File shipmentsList = null;
    
    public void setMapFile(File file) throws IncorrectInputFileFormat, FileNotFoundException {
        if (checkFileFormat(file)) {
            mapFile = file;
        } else {
            throw new IncorrectInputFileFormat();
        }
    }
    
    public void setShipmentsListFile(File file) {
        shipmentsList = file;
    }
    
    public Map returnMap() throws IOException{
        if (mapFile == null) {
            throw new FileNotFoundException();
        }
        

                
        return null;       
    }
    
    private boolean checkFileFormat(File file) throws FileNotFoundException {
        Scanner reader;
        if (file.exists()) {
            reader = new Scanner(file);
            while (reader.hasNext()) {
                
            }
        } else {
            return false;
        }
        return true;
    }
}
