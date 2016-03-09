/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assn5;

import java.util.ArrayList;

/**
 *
 * @author petersenrr
 */
public class FirstCome 
{
    private ArrayList<Process> processArray;
    private double turnAround;
    private double wait;
    private double response;
    
    public FirstCome(ArrayList<Process> processArray)
    {
        this.processArray = processArray;
    }
    
    public void calculateFirstCome()
    {
        double startTime = processArray.get(0).getSubmit();
        double endTime = 0;
        System.out.println("clock\trun\tresp\tt.resp\twait\tt.wait\tta\tt.ta");
        for(int i = 0; i < processArray.size(); i++)
        {
            double turnAroundTemp;
            double waitTemp;
            double responseTemp;
            double duration = processArray.get(i).getDuration();
            double submit = processArray.get(i).getSubmit();
            
            
            if(i == 0)
            {
                endTime = duration + submit;
            }
            else if(i < processArray.size() -1)
            {
                if(endTime < processArray.get(i+1).getSubmit() && (endTime + duration) < processArray.get(i+1).getSubmit())
                {
                    endTime = processArray.get(i+1).getSubmit();
                }
                else
                {
                    endTime = endTime + duration;
                }
            }
            else
            {
                endTime = startTime + duration;
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
            
            startTime = endTime;
            
        }
        
        System.out.println("Average Response: " + response/processArray.size() + 
                " Average T.A: " + turnAround/processArray.size() +
                " Average Wait: " + wait/processArray.size());
    }
    
    
    
}
