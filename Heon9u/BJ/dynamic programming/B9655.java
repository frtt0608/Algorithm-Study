import java.util.*;
import java.io.*;

// 돌게임
public class B9655 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        System.out.println(N%2 == 1 ? "SK":"CY");
    }
}
