/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assn5;

/**
 *
 * @author petersenrr
 */
public class Process 
{
    private double submitTime = 0;
    private double durationTime = 0;
    private double startTime = 0;
    private double endTime = 0;
    private double remainingTime = 0;
    private double firstRun = 0;
    
    
    public Process(double submit, double duration, double start, double end, double remaining)
    {
        submitTime = submit;
        durationTime = duration;
        startTime = start;
        endTime = end;
        remainingTime = duration;
    }
    
    public double getSubmit()
    {
        return submitTime;
    }
    
    public double getDuration()
    {
        return durationTime;
    }
    
    public double getStart()
    {
        return startTime;
    }
    
    public double getEnd()
    {
        return endTime;
    }
    
    public double getRemaining()
    {
        return remainingTime;
    }
    
    public void setRemaining(double remainingTime)
    {
        this.remainingTime = remainingTime;
    }
    
    public void setEnd(double endTime)
    {
         this.endTime = endTime;
    }
    
    public void setStart(double startTime)
    {
        this.startTime = startTime;
    }
    
    public void setFirst(double firstRun)
    {
        this.firstRun = firstRun;
    }
    
    public double getFirst()
    {
        return firstRun;
    }
    
}
