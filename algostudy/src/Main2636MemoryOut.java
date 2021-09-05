import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2636MemoryOut {

    static int R, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;

    private static boolean bfs(int sr, int sc){
        visited = new boolean[R][C];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(sr);
        queue.offer(sc);
        visited[sr][sc] = true;

        boolean flag = true;
        if(sr==0 || sr == R-1 || sc==0 || sc==C-1 ) flag = false;

        while (!queue.isEmpty()){
            int r = queue.poll();
            int c = queue.poll();
            for(int d = 0 ; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nc < 0 || nr > R-1 || nc > C-1) continue;
                if(map[nr][nc] == 0 && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    queue.offer(nr);
                    queue.offer(nc);
                    if( flag && (nr == 0 || nr == R-1 || nc==0 || nc==C-1)){
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/2636.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        int cnt = 0;
        for(int r= 0 ; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c= 0 ; c < C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c]==1) cnt++;
            }
        }


        int time = 0;
        int prev = cnt;
        while(true){
            if(cnt==0) break;
            for(int r= 0 ; r < R; r++){
                for(int c= 0 ; c < C; c++){
                    if(map[r][c]==1){
                        if(r==0 || c==0 || r==R-1 || c == C-1){
                            map[r][c] = -1;
                            continue;
                        }

                        for(int d = 0; d < 4; d++){
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if(nr < 0 || nc < 0 || nr > R-1 || nc > C-1) continue;
                            if(map[nr][nc]==0){
                                boolean flag = bfs(nr, nc);
                                if(!flag){
                                    map[r][c]=-1;
                                }
                            }
                        }
                    }
                }
            }

            prev = cnt;
            for(int i = 0 ; i < R ; i++){
                for(int j = 0 ; j < C; j++){
                    if(map[i][j]==-1){
                        map[i][j]=0;
                        cnt--;
                    }
                }
            }
            time++;
        }

        bw.write(time+"\n"+prev+"\n");
        bw.close();
        br.close();
    }
}
