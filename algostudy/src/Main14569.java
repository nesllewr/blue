import java.io.*;
import java.util.*;

public class Main14569 {

    static int N, K, M, P;
    static Map<Integer, Set<Integer>> T = new HashMap<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/14569.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 50; i++){
            T.put((i+1), new HashSet<>() );
        }

        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < K ; j++){
                int tmp = Integer.parseInt(st.nextToken());
                Set<Integer> origin = T.get(tmp);
                origin.add(i);
                T.put(tmp, origin);
            }
        }

        for(int i = 1; i < 11; i++){
            for(int j = 0 ; j < 5; j++){
                System.out.print(T.get(j*10+i)+" ");
            }
            System.out.println();
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            Set<Integer> dup = new HashSet<>();
            for(int j = 0 ; j < P; j++){
                int q = Integer.parseInt(st.nextToken());
                dup.addAll(T.get(q));
            }
            System.out.println(dup);
            bw.write( (N-dup.size())+"\n");
        }

        bw.close();
        br.close();
    }
}
