import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Repetitions iterations
        int[] repetitions = {100, 1000, 10000, 100000};

        // the 4 Algorithms to simulate
        String[] algorithms = {"fcfs", "srtf", "rr", "mlfq"};


        //FCFS ATT/AWT Results
        //hashmap for atts and awts
        double[] fcfsATTs = {0, 0, 0, 0};
        HashMap<Map<String, Integer>, Double> mapATTs = new HashMap<>();
        double[] fcfsAWTs = {0, 0, 0, 0};
        HashMap<Map<String, Integer>, Double> mapAWTs = new HashMap<>();


        //SRTF ATT/AWT Results
        double[] srtfATTs = {0, 0, 0, 0};
        double[] srtfAWTs = {0, 0, 0, 0};

        //RR ATT/AWT Results
        double[] rrATTs = {0, 0, 0, 0};
        double[] rrAWTs = {0, 0, 0, 0};


        //MLFQ ATT/AWT Results
        double[] mlfqATTs = {0, 0, 0, 0};
        double[] mlfqAWTs = {0, 0, 0, 0};

        //iterations for each repetition
        for (int repetition : repetitions) {

            int index = 0;
            double[] totalTurnaroundTime1 = new double[4];
            double[] totalWaitingTime1 = new double[4];

            //loop for each algorithm
            for (String algorithm : algorithms) {


                for (int i = 0; i < repetition; i++) {
                    int totalTurnaroundTime = 0;
                    int totalWaitingTime = 0;

                    // creating 8 processes with random arrival time and burst time
                    List<Process> processes = generateProcesses(8);
                    switch (algorithm) {
                        case "fcfs":
                            FCFS.FCFS(processes);
                            break;
                        case "srtf":
                            SRTF.SJFPreemption(processes);
                            break;
                        case "rr":
                            RR.RR(processes, 20); // Quantum for Round Robin
                            break;
                        case "mlfq":
                            MLFQ.mlfq(processes);
                            break;
                    }

                    // calculating average turnaround time (ATT) and waiting time (AWT) for each process
                    for (Process process : processes) {
                        totalTurnaroundTime += process.getTurnaroundTime();
                        totalWaitingTime += process.getWaitingTime();
                    }
                    double ATT = (double) totalTurnaroundTime / 8;
                    totalTurnaroundTime1[index] += ATT;
                    double AWT = (double) totalWaitingTime / 8;
                    totalWaitingTime1[index] += AWT;
                }
                index++;
            }
            //calculating total final awt and att for each algorithm
            for (int i = 0; i < 4; i++) {
                Map<String, Integer> innerMap = new HashMap<>();
                innerMap.put(algorithms[i], repetition);

                totalTurnaroundTime1[i] /= (repetition);
                mapATTs.put(innerMap, totalTurnaroundTime1[i]);

                totalWaitingTime1[i] /= (repetition);
                mapAWTs.put(innerMap, totalWaitingTime1[i]);
                System.out.println("Algorithm: " + algorithms[i] + ", Repetition Count: " + repetition);
                System.out.println("Average Turnaround Time (ATT): " + totalTurnaroundTime1[i]);
                System.out.println("Average Waiting Time (AWT): " + totalWaitingTime1[i]);
                System.out.println("----------------------------------------------------");
            }
        }

//creating tables for gui and collecting final results
// filling tables with simulation results:
        Map<String, Integer> fcfsMap1 = new HashMap<>();
        fcfsMap1.put("fcfs", 100);

        Map<String, Integer> fcfsMap2 = new HashMap<>();
        fcfsMap2.put("fcfs", 1000);

        Map<String, Integer> fcfsMap3 = new HashMap<>();
        fcfsMap3.put("fcfs", 10000);

        Map<String, Integer> fcfsMap4 = new HashMap<>();
        fcfsMap4.put("fcfs", 100000);


        String[] columnNames = {"FCFS", "100", "1000", "10000", "100000"};
        Object[][] fcfsData = {
                // Filling in data with algorithm names and ATT/AWT values

                {"ATT", mapATTs.get(fcfsMap1), mapATTs.get(fcfsMap2), mapATTs.get(fcfsMap3), mapATTs.get(fcfsMap4)},
                {"AWT", mapAWTs.get(fcfsMap1), mapAWTs.get(fcfsMap2), mapAWTs.get(fcfsMap3), mapAWTs.get(fcfsMap4)},
        };

        JTable fcfsTable = new JTable(fcfsData, columnNames);


        //srtf data
        Map<String, Integer> srtfMap1 = new HashMap<>();
        srtfMap1.put("srtf", 100);

        Map<String, Integer> srtfMap2 = new HashMap<>();
        srtfMap2.put("srtf", 1000);

        Map<String, Integer> srtfMap3 = new HashMap<>();
        srtfMap3.put("srtf", 10000);

        Map<String, Integer> srtfMap4 = new HashMap<>();
        srtfMap4.put("srtf", 100000);

        // After collecting simulation results:
        String[] srtfColumnNames = {"SRTF", "100", "1000", "10000", "100000"};
        Object[][] srtfData = {
                // Fill in data with algorithm names and ATT/AWT values

                {"ATT", mapATTs.get(srtfMap1), mapATTs.get(srtfMap2), mapATTs.get(srtfMap3), mapATTs.get(srtfMap4)},
                {"AWT", mapAWTs.get(srtfMap1), mapAWTs.get(srtfMap2), mapAWTs.get(srtfMap3), mapAWTs.get(srtfMap4)},
        };

        JTable srtfTable = new JTable(srtfData, srtfColumnNames);

        //rr data
        Map<String, Integer> rrMap1 = new HashMap<>();
        rrMap1.put("rr", 100);

        Map<String, Integer> rrMap2 = new HashMap<>();
        rrMap2.put("rr", 1000);

        Map<String, Integer> rrMap3 = new HashMap<>();
        rrMap3.put("rr", 10000);

        Map<String, Integer> rrMap4 = new HashMap<>();
        rrMap4.put("rr", 100000);

        // after collecting simulation results:
        String[] rrColumnNames = {"RR", "100", "1000", "10000", "100000"};
        Object[][] rrData = {
                // Fill in data with algorithm names and ATT/AWT values
                {"ATT", mapATTs.get(rrMap1), mapATTs.get(rrMap2), mapATTs.get(rrMap3), mapATTs.get(rrMap4)},
                {"AWT", mapAWTs.get(rrMap1), mapAWTs.get(rrMap2), mapAWTs.get(rrMap3), mapAWTs.get(rrMap4)},
        };

        JTable rrTable = new JTable(rrData, rrColumnNames);

        //mlfq data
        Map<String, Integer> mlfqMap1 = new HashMap<>();
        mlfqMap1.put("mlfq", 100);

        Map<String, Integer> mlfqMap2 = new HashMap<>();
        mlfqMap2.put("mlfq", 1000);

        Map<String, Integer> mlfqMap3 = new HashMap<>();
        mlfqMap3.put("mlfq", 10000);

        Map<String, Integer> mlfqMap4 = new HashMap<>();
        mlfqMap4.put("mlfq", 100000);

        // after collecting simulation results:
        String[] mlfqColumnNames = {"MLFQ", "100", "1000", "10000", "100000"};
        Object[][] mlfqData = {
                // Fill in data with algorithm names and ATT/AWT values

                {"ATT", mapATTs.get(mlfqMap1), mapATTs.get(mlfqMap2), mapATTs.get(mlfqMap3), mapATTs.get(mlfqMap4)},
                {"AWT", mapAWTs.get(mlfqMap1), mapAWTs.get(mlfqMap2), mapAWTs.get(mlfqMap3), mapAWTs.get(mlfqMap4)},
        };

        JTable mlfqTable = new JTable(mlfqData, mlfqColumnNames);

        // creating a JFrame with a layout manager
        JFrame frame = new JFrame("Results Tables");
        frame.setLayout(new GridLayout(4, 1)); // Arrange tables vertically

        // adding the tables to the frame using scroll panes
        frame.add(new JScrollPane(fcfsTable));
        frame.add(new JScrollPane(srtfTable));
        frame.add(new JScrollPane(rrTable));
        frame.add(new JScrollPane(mlfqTable));

        frame.pack();
        frame.setVisible(true);
    }


    static List<Process> generateProcesses(int numProcesses) {
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < numProcesses; i++) {
            processes.add(new Process(i + 1)); // Process IDs start from 1
        }
        return processes;
    }
}