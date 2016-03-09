/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assn5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author petersenrr
 */
public class Assn5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        String fileName = "processlist.txt";
        
        FileReader fileReader = new FileReader(fileName);
        
        BufferedReader bufferReader = new BufferedReader(fileReader);
        
        ArrayList<Process> processArray = new ArrayList<>();
        
        String line;
        
        while((line = bufferReader.readLine()) !=null)
        {
            String[] lines = line.trim().split(" ");
            Process temp = new Process(Double.parseDouble(lines[0]), Double.parseDouble(lines[1]), 0,0,0);
            processArray.add(temp);
        }
        
//        FirstCome fcfs = new FirstCome(processArray);
//        fcfs.calculateFirstCome();
//        
//        ShortestJob shortest = new ShortestJob(processArray);
//        shortest.calcuateShortestJob();
        
        SRTF shortestReman = new SRTF(processArray);
        shortestReman.CalculateSRTF();
        
        
//        for(int i = 0; i < processArray.size(); i++)
//        {
//            System.out.print(processArray.get(i).getSubmit() + " ");
//            System.out.println(processArray.get(i).getDuration());
//        }
        
        // TODO code application logic here
    }
    
}
