import java.util.*;
import java.io.*;

// 공항
// 분리집합, path compression
// 중간 탐색 과정이 없기 때문에 단순한 반복문보다 빠르다.
public class B10755 {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] parentGate = new int[G+1];

        for(int i=1; i<G+1; i++) {
            parentGate[i] = i;
        }
        
        Airplane airplane = new Airplane(G, P, parentGate);
        int planeCount = 0;
        for(int i=0; i<P; i++) {
            int gate = Integer.parseInt(br.readLine());
            gate = airplane.getParentGate(gate);

            if(gate == 0) break;

            airplane.unionGate(gate-1, gate);
            planeCount += 1;
        }

        System.out.println(planeCount);
    }

    static class Airplane {
        int G, P;
        int[] parentGate;

        Airplane(int G, int P, int[] parentGate) {
            this.G = G;
            this.P = P;
            this.parentGate = parentGate;
        }

        public int getParentGate(int gate) {
            if(parentGate[gate] == gate)
                return gate;
            else
                return parentGate[gate] = getParentGate(parentGate[gate]);
        }

        public void unionGate(int gateA, int gateB) {
            gateA = getParentGate(gateA);
            gateB = getParentGate(gateB);

            if(gateA < gateB) parentGate[gateB] = gateA;
            else parentGate[gateA] = gateB;
        }
    }
}