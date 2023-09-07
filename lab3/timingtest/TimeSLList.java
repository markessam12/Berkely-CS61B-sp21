package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>(), opCounts = new AList<>();
        SLList<Integer>  list;
        AList<Double> times = new AList<>();
        int n = 1000;
        for (int i = 0; i < 7; i++){
            list = new SLList<>();
            for(int j = 0; j < n; j++){
                list.addLast(j);
            }
            Stopwatch s1 = new Stopwatch();
            for(int j = 0; j < n; j++){
                list.getLast();
            }
            times.addLast(s1.elapsedTime());
            Ns.addLast(n);
            opCounts.addLast(n);
            n *= 2;
        }
        printTimingTable(Ns, times, opCounts);
    }
}
