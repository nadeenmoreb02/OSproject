import java.util.*;
public class RR {
    public static void RR(List<Process> processes, int quantum) {
        //needed variables
        int i, j;
        int n = processes.size();
        int processNum = n;
        int[][] start = new int[n][n*n];
        double timeNow = 0;
        double[] burst = new double[n];
        double[] arrival = new double[n];
        double minVal = Integer.MAX_VALUE;


        int negative = -2;
        int current = negative;
        //getting all burst and arrival times
        for (i = 0; i < n; i++) {
            burst[i] = processes.get(i).getBurstTime();
            arrival[i] = processes.get(i).getArrivalTime();
            int limit = n*n;
            for (j = 0; j < limit; j++) {
                start[i][j] = negative;
            }
        }

        int totalWaiting, totalTurnaround;
        totalWaiting = 0;
        totalTurnaround = 0;
        boolean finished = false;

        //loop till all processes finish executing
        while (processNum != 0) {

            minVal = Integer.MAX_VALUE;
            finished = false;

            //getting next process for execution depending on min arrival time
            for (i = 0; i < n; i++) {
                double p = (double) (timeNow + 0.09);
                if (arrival[i] <= p && minVal > arrival[i] && burst[i] > 0) {
                    current = i;
                    minVal = arrival[i];
                    finished = true;

                }
            }
// end of loop
            if (!finished) {
                timeNow++;
                continue;
            }

            //setting start times for processes
            j = 0;

            while (start[current][j] != negative) {
                j++;
            }

            if (start[current][j] == negative) {
                start[current][j] = (int) timeNow;
                processes.get(current).startTimes[j] = (int) timeNow;
            }

            if (burst[current] <= quantum) {
                timeNow += burst[current];
                burst[current] = 0;
            } else {
                timeNow += quantum;
                burst[current] -= quantum;
            }

            if (burst[current] > 0) {
                arrival[current] = (double) (timeNow + 0.09);
            }

// setting completion, waiting and turnaround times
            if (burst[current] == 0) {
                processNum--;
                processes.get(current).setCompletionTime((int) timeNow);
                processes.get(current).setWaitingTime(processes.get(current).getCompletionTime() - processes.get(current).getArrivalTime() - processes.get(current).getBurstTime());
                totalWaiting += processes.get(current).getWaitingTime();
                processes.get(current).setTurnaroundTime(processes.get(current).getBurstTime() + processes.get(current).getWaitingTime());
                totalTurnaround += processes.get(current).getTurnaroundTime();

            }

        }

    }
}





