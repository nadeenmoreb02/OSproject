import java.util.*;
public class Process {
    private int pId;
    private int arrivalTime; // Time at which the process arrives in the system in the ready queue
    private int burstTime; // Time required by the process to complete its execution
    private int startTime; // Time at which the process starts execution
    private int completionTime; // Time at which the process completes its execution
    private int waitingTime; // Time spent by the process waiting in the ready queue (turnaround_time - burst_time)
    private int turnaroundTime; // Total time taken by the system to execute the process (completion_time - arrival_time)
    private int remainingTime; // Remaining time needed for the process to complete its execution
    private boolean completed = false; //a flag indicator of the process completion
    private int priority; //a priority indicator for mlfq when choosing the best queue
    int[] startTimes = new int[64];


    public Process(int pId) {
        this.pId = pId;

        Random random = new Random();
        this.burstTime = random.nextInt(96) + 5; //generate and assign random burst time between 5 and 100

        Random random2 = new Random();
        this.arrivalTime = random2.nextInt(31); //generate and assign random arrival time between 0 and 30

        this.priority = 1;
        this.startTime = 0;
        this.completionTime = 0;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pId=" + pId +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", startTime=" + startTime +
                ", completionTime=" + completionTime +
                ", waitingTime=" + waitingTime +
                ", turnaroundTime=" + turnaroundTime +
                ", remainingTime=" + remainingTime +
                ", completed=" + completed +
                '}';
    }

}
