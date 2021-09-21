import java.util.*;
import java.io.*;

// 공항
// 분리집합, path compression
public class B10775 {
    static int[] gateState;

    public static int getParentGateNumber(int gate) {
        if(gateState[gate] == gate)
            return gate;
        else
            return gateState[gate] = getParentGateNumber(gateState[gate]);
    }

    public static void unionParentSet(int preGate, int gate) {
        preGate = getParentGateNumber(preGate);
        gate = getParentGateNumber(gate);

        if(preGate < gate) 
            gateState[gate] = preGate;
        else
            gateState[preGate] = gate;
    }
    
    public static boolean checkPossibleDocking(int gate) {
        gate = getParentGateNumber(gate);

        if(gate != 0) {
            unionParentSet(gate-1, gate);
            return true;
        }
        
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        
        gateState = new int[G+1];
        for(int i=1; i<G+1; i++)
            gateState[i] = i;

        int planeCount = 0;
        int gate;
        for(int i=0; i<P; i++) {
            gate = Integer.parseInt(br.readLine());
            if(!checkPossibleDocking(gate)) break;

            planeCount += 1;
        }

        System.out.println(planeCount);
    }
}