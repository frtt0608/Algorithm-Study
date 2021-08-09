import java.util.*;
import java.io.*;

// Manacher algo
public class B13275 {
    static int[] table;

    public static void setManacherTable(char[] chars) {
        int size = chars.length;
        table = new int[size];
        int scope=0, center=0;

        for(int i=0; i<size; i++) {
            if(i > scope) 
                table[i] = 0;
            else 
                table[i] = Math.min(table[2*center-i], scope-i);

            while(i-table[i]-1>=0 && i+table[i]+1<size &&
                        chars[i-table[i]-1] == chars[i+table[i]+1]) {
                table[i] += 1;
            }

            if(scope < i+table[i]) {
                scope = i+table[i];
                center = i;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] inputs = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        for(char chr: inputs) {
            sb.append("#").append(chr);
        }

        String str = sb.append("#").toString();
        setManacherTable(str.toCharArray());

        int maxCount = Arrays.stream(table).max().getAsInt();
        System.out.println(maxCount);
    }
}