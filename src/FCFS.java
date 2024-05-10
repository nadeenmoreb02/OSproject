import java.util.*;
public class FCFS {
    //first comes first served algorithm
    //simplest algorithm
    public static void FCFS(List<Process> processes) {
        //sort list based on arrival time in Ascending order
        Collections.sort(processes, (process1, process2) -> Integer.compare(process1.getArrivalTime(), process2.getArrivalTime()));

        int timeNow = 0;
        //processes are entered and implemented in a list
        //a loop that starts with first process that is entered in the processes' list
        for (Process process : processes) {
            //choose maximum passed time to start cpu execution for the process
            process.setStartTime(Math.max(timeNow, process.getArrivalTime()));
            //completion time taken is the total time it enters the system till it finishes execution
            process.setCompletionTime(process.getStartTime() + process.getBurstTime());
            timeNow = process.getCompletionTime();
        }
        //setting turn around and waiting time
        for (Process process : processes) {
            process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());
            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());

        }

    }

}
