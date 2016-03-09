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
public class SRTF 
{
    private ArrayList<Process> processArray;
    private ArrayList<Process> readyQueue = new ArrayList<>();
    private double turnAround;
    private double wait;
    private double response;
    private double currentTime;
    private int shortestTimeIndex;
    private boolean addReady = false;
    private boolean done = false;
    private boolean gap = false;
    
    public SRTF(ArrayList<Process> processArray)
    {
        this.processArray = processArray;
    }
    
    public void CalculateSRTF()
    {
        shortestTimeIndex = 0;
        double startTime = 0; 
        
        if(processArray.get(0).getSubmit() != 0)
        {
            startTime = processArray.get(0).getSubmit();
            processArray.get(0).setStart(startTime);
            addReady = true;
        }
        currentTime = processArray.get(0).getSubmit();
        double nextTime = 0;
        System.out.println("Shortest Time Remaining First\nclock\trun\tresp\tt.resp\twait\tt.wait\tta\tt.ta");
        int size = processArray.size();
        ArrayList<Process> remainingArray = new ArrayList<>(processArray);
        int shortestRemanIndex = 0;
        
        


            double turnAroundTemp = 0;
            double waitTemp = 0;
            double responseTemp = 0;
            double duration = 0;
            double submit = 0;
            int i = 0;
            
            
            while(!processArray.isEmpty())
            {
                int sizeReady = readyQueue.size();
                int tempCount = 0;
                while(startTime >= processArray.get(tempCount).getSubmit())
                {
                    
                    readyQueue.add(processArray.get(tempCount));
                    processArray.remove(tempCount);
                    tempCount++;
                    
                }
                
                
                
                shortestTimeIndex = shortestCheck(startTime, processArray, shortestTimeIndex, readyQueue);
                duration = readyQueue.get(shortestTimeIndex).getDuration();
                submit = readyQueue.get(shortestTimeIndex).getSubmit();
                if(readyQueue.get(shortestTimeIndex).getFirst() == 0)
                {
                    readyQueue.get(shortestTimeIndex).setFirst(currentTime);
                }
                
                
                
                if(startTime + readyQueue.get(shortestTimeIndex).getRemaining() > processArray.get(0).getSubmit())
                {
                    addReady = true;
                    gap = true;
                }
                
                
                if(addReady)
                {
                    insertIntoReadyQueue(startTime);
                }
                
                if(gap)
                {
                    gap = false;
                    startTime = currentTime;
                }
                else
                {
                    if(i == 0)
                    {
                        nextTime = readyQueue.get(shortestTimeIndex).getRemaining() + submit + (currentTime - readyQueue.get(shortestTimeIndex).getSubmit());
                        readyQueue.get(shortestTimeIndex).setEnd(nextTime);
                        
                    }
                    else if(i < size -1)
                    {
                        if(nextTime < processArray.get(0).getSubmit() && (nextTime + duration) < processArray.get(0).getSubmit())
                        {
                            nextTime = processArray.get(0).getSubmit();
                            readyQueue.get(shortestTimeIndex).setEnd(duration + submit);
                        }
                        else
                        {
                            nextTime = nextTime + duration;
                            readyQueue.get(shortestTimeIndex).setEnd(duration + submit);
                        }
                    }
                    else
                    {
                        nextTime = startTime + duration;
                        readyQueue.get(shortestTimeIndex).setEnd(duration + submit);
                    }

                   
                    waitTemp = currentTime - 
                            readyQueue.get(shortestTimeIndex).getSubmit()
                            -(readyQueue.get(shortestTimeIndex).getDuration()- readyQueue.get(shortestTimeIndex).getRemaining());
                    
                    turnAroundTemp  = readyQueue.get(shortestTimeIndex).getDuration() + waitTemp;
                    
                    responseTemp = readyQueue.get(shortestTimeIndex).getFirst() - 
                            readyQueue.get(shortestTimeIndex).getSubmit();

                    turnAround += turnAroundTemp;
                    wait += waitTemp;
                    response += responseTemp;

                    System.out.println(currentTime + "\t" + duration + "\t" + responseTemp
                    + "\t" + response + "\t" + waitTemp + "\t" + wait + "\t" + 
                    turnAroundTemp + "\t" + turnAround);
                    
                    startTime = nextTime;
                    currentTime = nextTime;


                    if(!readyQueue.isEmpty())
                    {
                        remainingArray.remove(shortestTimeIndex);
                        readyQueue.remove(shortestTimeIndex);
                        
                    }
                    else
                    {
                        i++;
                    }
                    if(nextTime < processArray.get(0).getSubmit() && readyQueue.isEmpty())
                        {
                            nextTime = processArray.get(0).getSubmit();
                            readyQueue.add(processArray.get(0));
                            processArray.remove(0);
                            startTime = nextTime;
                            currentTime = nextTime;
                        }
                }
                
                
            }
            
            if(processArray.isEmpty())
            {
                while(!readyQueue.isEmpty())
                {
                    shortestTimeIndex = shortestCheck(startTime, processArray, shortestTimeIndex, readyQueue);
                    duration = readyQueue.get(shortestTimeIndex).getDuration();
                    submit = readyQueue.get(shortestTimeIndex).getSubmit();
                    if(readyQueue.get(shortestTimeIndex).getFirst() == 0)
                    {
                        readyQueue.get(shortestTimeIndex).setFirst(currentTime);
                    }

                    nextTime = readyQueue.get(shortestTimeIndex).getRemaining() + submit + (currentTime - readyQueue.get(shortestTimeIndex).getSubmit());
                    readyQueue.get(shortestTimeIndex).setEnd(nextTime);

                    waitTemp = currentTime - 
                            readyQueue.get(shortestTimeIndex).getSubmit()
                            -(readyQueue.get(shortestTimeIndex).getDuration()- readyQueue.get(shortestTimeIndex).getRemaining());

                    turnAroundTemp  = readyQueue.get(shortestTimeIndex).getDuration() + waitTemp;

                    responseTemp = readyQueue.get(shortestTimeIndex).getFirst() - 
                            readyQueue.get(shortestTimeIndex).getSubmit();

                    turnAround += turnAroundTemp;
                    wait += waitTemp;
                    response += responseTemp;

                    System.out.println(currentTime + "\t" + duration + "\t" + responseTemp
                    + "\t" + response + "\t" + waitTemp + "\t" + wait + "\t" + 
                    turnAroundTemp + "\t" + turnAround);

                    startTime = nextTime;
                    currentTime = nextTime;


                    if(!readyQueue.isEmpty())
                    {
                        remainingArray.remove(shortestTimeIndex);
                        readyQueue.remove(shortestTimeIndex);

                    }
                }
            }

            System.out.println(startTime + "\t" + duration + "\t" + responseTemp
             + "\t" + response + "\t" + waitTemp + "\t" + wait + "\t" + 
                    turnAroundTemp + "\t" + turnAround);
            
            
            
//            remainingArray.remove(shortestTimeIndex);
//            processArray.remove(shortestTimeIndex);
            
        
        
        System.out.println("Average Response: " + response/size + 
                " Average T.A: " + turnAround/size +
                " Average Wait: " + wait/size);
        
        
        
    }

    
    
    
    
    private void insertIntoReadyQueue(double startTime) {
        for(int tempReadyCount = 0; tempReadyCount < processArray.size(); tempReadyCount++)
        {
            if(startTime + readyQueue.get(shortestTimeIndex).getRemaining() > processArray.get(tempReadyCount).getSubmit())
            {
                
                readyQueue.add(processArray.get(tempReadyCount));
                if((readyQueue.get(shortestTimeIndex).getRemaining() + startTime) > processArray.get(tempReadyCount).getRemaining())
                {
                    currentTime = processArray.get(tempReadyCount).getSubmit();
                    
                    readyQueue.get(shortestTimeIndex).setRemaining(readyQueue.get(shortestTimeIndex).getRemaining() - (currentTime-startTime));
                    shortestTimeIndex = shortestCheck(currentTime, processArray, shortestTimeIndex, readyQueue);
                    readyQueue.get(shortestTimeIndex).setStart(currentTime);
                    gap = true;
                }
                processArray.remove(tempReadyCount);
            }
            else
            {
                addReady = false;
                break;
            }
        }
    }
    
    
    
    
    private int shortestCheck(double currentTime, ArrayList<Process> remainingArray, int shortest, ArrayList<Process> readyQ)
    {
        shortest = 0;
        int size = readyQ.size();
        int i = 0;
        for(i = 0; i < size; i++)
        {
            if(readyQ.get(shortest).getRemaining() > readyQ.get(i).getRemaining())
            {
                shortest = i;
            }
        }
        return shortest;
    }
}
