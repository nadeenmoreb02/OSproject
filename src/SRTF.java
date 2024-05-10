import java.util.*;
public class SRTF {
    public static void SJFPreemption(List<Process> processes) {
        //total is number of completed processes
        int timeNow = 0, total = 0;
        //average waiting and average turnaround time
        float awt = 0, aat = 0;
        int i;
        //set remaining time for each process
        for (i = 0; i < processes.size(); i++) {
            processes.get(i).setRemainingTime(processes.get(i).getBurstTime());
            processes.get(i).setCompleted(false);
        }
        //loop till all the processes finish executing
        while (true) {
            //minimum burst time
            int min = Integer.MAX_VALUE;
            int current = processes.size();
            if (total == processes.size()) {
                break;
            }
          //get shortest remaining time
            for (i = 0; i < processes.size(); i++) {
                if ((processes.get(i).getArrivalTime() <= timeNow) && (processes.get(i).isCompleted() == false) && (processes.get(i).getRemainingTime() < min)) {
                    min = processes.get(i).getRemainingTime();
                    current = i;
                }
            }

            //this case means that there is no available processes at the moment the cpu is idle
            if (current == processes.size())
                timeNow++;
            else {
                //decrement remaining time
                processes.get(current).setRemainingTime((processes.get(current).getRemainingTime() - 1));
                timeNow++;
                //check if process has completed and set its status to completed
                if (processes.get(current).getRemainingTime() == 0) {
                    processes.get(current).setCompletionTime(timeNow);
                    processes.get(current).setCompleted(true);
                    total++;
                }
            }
        }
        //setting turn around and waiting time
        for (i = 0; i < processes.size(); i++) {
            processes.get(i).setTurnaroundTime(processes.get(i).getCompletionTime() - processes.get(i).getArrivalTime());
            processes.get(i).setWaitingTime(processes.get(i).getTurnaroundTime() - processes.get(i).getBurstTime());
            awt += processes.get(i).getWaitingTime();
            aat += processes.get(i).getTurnaroundTime();
        }

    }
}
