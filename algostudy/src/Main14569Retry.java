import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main14569Retry {

    static int N, K, M, P;
    static long[] T = new long[1001];

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/14569.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < K ; j++){
                T[i] = T[i] |  ((long) 1 << ( Integer.parseInt( st.nextToken() )));
            } // 해당 시간표에 1 마킹
        } // 각 과목별 시간표

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            long cur = 0;
            for(int j = 0 ; j < P; j++){
                int q = Integer.parseInt(st.nextToken());
                cur = cur | ( (long) 1 << q ); // 비어 있는 시간에 1 마킹
            }
            cur = ~cur; // 안되는 시간표
            int pos = 0;
            for(int j = 0; j < N ; j++ ){
                if(  (cur & T[j] ) == 0 ){
                    pos++; // 안되는 시간표가 없는 경우 카운팅
                }
            }
            bw.write(pos+"\n");

        }

        bw.close();
        br.close();
    }
}
