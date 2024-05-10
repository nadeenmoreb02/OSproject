import java.util.*;
public class MLFQ {
    public static void insertProcess(Process process, ArrayList<Queue<Process>> queues, int queueNum) {
        queues.get(queueNum).add(process);
    }

    public static void mlfq(List<Process> processes) {
        float avgwt = 0;
        float avgta = 0;
        ArrayList<Queue<Process>> queues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            queues.add(new LinkedList<>());
        }
        Scanner sc = new Scanner(System.in);

        int n = processes.size();

        for (Process p : processes) {
            insertProcess(p, queues, 0);
        }
        int timeNow = 0;
        //loop till all queues are empty and all processes has been executed
        while (!queues.get(0).isEmpty() || !queues.get(1).isEmpty() || !queues.get(2).isEmpty()) {
            //checking first queue
            if (!queues.get(0).isEmpty()) {
                Queue<Process> currentQueue = queues.get(0);

                if (!currentQueue.isEmpty()) {
                    // converting the queue to a list for sorting
                    List<Process> processList = new ArrayList<>(currentQueue);

                    // sorting the list based on arrival times
                    Collections.sort(processList, Comparator.comparingInt(Process::getArrivalTime));

                    // clearing the currentQueue and add the sorted processes back
                    currentQueue.clear();
                    currentQueue.addAll(processList);

                    Process currentProcess1 = currentQueue.peek();
                    if (currentProcess1.getArrivalTime() <= timeNow) {
                        Process currentProcess = currentQueue.poll();
                        // executing process for the first time quantum 10
                        int timeRemaining = Math.min(10, currentProcess.getRemainingTime());
                        currentProcess.setRemainingTime(currentProcess.getRemainingTime() - timeRemaining);
                        timeNow += timeRemaining;

                        // checking if process completes or is moved to a lower priority queue
                        if (currentProcess.getRemainingTime() > 0) {
                            currentProcess.setPriority(Math.min(currentProcess.getPriority() + 1, 3));
                            queues.get(1).add(currentProcess);
                        } else {
                            currentProcess.setCompletionTime(timeNow);
                        }
                        continue;
                    }
                }
            }
            //checking second queue
            if (!queues.get(1).isEmpty()) {
                Queue<Process> currentQueue = queues.get(1);


                if (!currentQueue.isEmpty()) {
                    Process currentProcess = currentQueue.poll();

                    // executing process for the second time quantum 50
                    int timeRemaining = Math.min(50, currentProcess.getRemainingTime());
                    currentProcess.setRemainingTime(currentProcess.getRemainingTime() - timeRemaining);
                    timeNow += timeRemaining;

                    // checking if process completes or is moved to a lower priority queue
                    if (currentProcess.getRemainingTime() > 0) {
                        currentProcess.setPriority(Math.min(currentProcess.getPriority() + 1, 3));
                        queues.get(2).add(currentProcess);
                    } else {
                        currentProcess.setCompletionTime(timeNow);
                    }
                }

                //checking last queue
            } else if (!queues.get(2).isEmpty()) {
                Queue<Process> currentQueue = queues.get(2);

                if (!currentQueue.isEmpty()) {
                    Process currentProcess = currentQueue.poll();


                    // executing process for the appropriate time quantum
                    int timeRemaining = currentProcess.getRemainingTime();
                    currentProcess.setRemainingTime(currentProcess.getRemainingTime() - timeRemaining);
                    timeNow += timeRemaining;

                    currentProcess.setCompletionTime(timeNow);
                }

            } else {
                timeNow++;
            }
        }
        //setting turn around and waiting time
        for (Process process : processes) {
            process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());
            process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
            avgwt += process.getWaitingTime();
            avgta += process.getTurnaroundTime();
        }
    }

}