/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assn5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * @author petersenrr
 */
public class ShortestJob 
{
    
    private ArrayList<Process> processArray;
    private ArrayList<Process> tempArray = new ArrayList<>();
    private double turnAround;
    private double wait;
    private double response;
    
    public ShortestJob(ArrayList<Process> processArray)
    {
        this.processArray = processArray;
    }
    
    public void calcuateShortestJob()
    {
        int shortestTimeIndex = 0;
        double startTime = processArray.get(0).getSubmit();
        double nextTime = 0;
        System.out.println("clock\trun\tresp\tt.resp\twait\tt.wait\tta\tt.ta");
        int size = processArray.size();
        ArrayList<Process> remainingArray = new ArrayList<>(processArray);
        
        for(int i = 0; i < size; i++)
        {
            
            shortestTimeIndex = shortestCheck(startTime, remainingArray, shortestTimeIndex);

            
            double turnAroundTemp;
            double waitTemp;
            double responseTemp;
            double duration = processArray.get(shortestTimeIndex).getDuration();
            double submit = processArray.get(shortestTimeIndex).getSubmit();
            
            
            if(i == 0)
            {
                nextTime = duration + submit;
            }
            else if(i < size -1)
            {
                if(nextTime < processArray.get(1).getSubmit() && (nextTime + duration) < processArray.get(1).getSubmit())
                {
                    nextTime = processArray.get(1).getSubmit();
                }
                else
                {
                    nextTime = nextTime + duration;
                }
            }
            else
            {
                nextTime = startTime + duration;
            }
            
            
            turnAroundTemp  = startTime - submit + duration;
            waitTemp = startTime - submit;
            responseTemp = waitTemp;
            
            turnAround += turnAroundTemp;
            wait += waitTemp;
            response += responseTemp;
            
            
                System.out.println(startTime + "\t" + duration + "\t" + responseTemp
                 + "\t" + response + "\t" + waitTemp + "\t" + wait + "\t" + 
                        turnAroundTemp + "\t" + turnAround);
            
            startTime = nextTime;
            
            remainingArray.remove(shortestTimeIndex);
            processArray.remove(shortestTimeIndex);
            
        }
        
        System.out.println("Average Response: " + response/size + 
                " Average T.A: " + turnAround/size +
                " Average Wait: " + wait/size);
        
        
        
    }
    
    private int shortestCheck(double currentTime, ArrayList<Process> remainingArray, int shortest)
    {
        shortest = 0;
        int size = remainingArray.size();
        int i = 0;
        for(i = 0; i < size; i++)
        {
            if(remainingArray.get(i).getSubmit() > currentTime)
            {
                break;
            }
            if(remainingArray.get(shortest).getDuration() > remainingArray.get(i).getDuration())
            {
                shortest = i;
            }
        }

        
        return shortest;
    
    }
}


