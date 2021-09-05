import java.io.*;
import java.util.*;

public class Main14569MemoryOut {

    static int N, K, M, P;
    static Map<Integer, List<Integer>> T = new HashMap<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/14569.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            T.put(i, new ArrayList<>() );
        }

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            for(int j = 0 ; j < K ; j++){
                list.add(Integer.parseInt(st.nextToken()));
            }
            T.put(i, list);
        } // 각 과목별 시간표

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            int[] cnt = new int[N];
            int pos = 0;
            for(int j = 0 ; j < P; j++){
                int q = Integer.parseInt(st.nextToken());
                for(int k = 0 ; k < N; k++){
                    if(T.get(k).contains(q)){
                        cnt[k]++;
                    }
                }
            }
            for(int k = 0; k < N; k++){
                if(cnt[k]==T.get(k).size()) pos++;
            }
            bw.write(pos+"\n");

        }

        bw.close();
        br.close();
    }
}
