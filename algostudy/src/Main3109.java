import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Main3109 {

    static int N, K, L;
    static int[][] map;
    static Queue<HashMap<Integer, Character>> direction = new LinkedList<>();
    static int[] dy = { 0, 1, 0, -1};
    static int[] dx= { 1, 0, -1, 0}; // 시계 방향 // 오른쪽이면 +1 왼쪽이면 -1
    static List<int[]> snake = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/3109_1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        K = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < K ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a-1][b-1] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < L ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            direction.add(new HashMap<>(x, c));
        }

        map[0][0] = 2;
        snake.add(new int[]{0, 0}); // 0행 0열 머리
        snake.add(new int[]{0, 0}); // 0행 0열 꼬리

        boolean flag = true;
        int d = 0 , time = 1;
        while (true){
            if(!flag) break;
            if(direction.peek().containsKey(time)){ // 방향을 바꾸는 경우
                if(direction.peek().get(time)=='L'){
                    d--;
                }else{ // 'D'일때
                    d++;
                }
            }else { // 방향을 안 바꾸는 경우
                int[] head = snake.get(0);
                int ny = head[0] + dy[d];
                int nx = head[1] + dx[d];

                if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1){
                    flag = false;
                    continue;
                }
                if(map[ny][nx]==1){ // 사과가 있으면 머리만 증가
                    snake.add(0, new int[]{ny, nx});
                    map[ny][nx] = 2;
                }else if(map[ny][nx]==0){ // 빈칸이면 헤드 추가 꼬리 빼기
                    snake.add(0, new int[]{ny, nx});
                    int ssize = snake.size();
                    int[] tail = snake.get(ssize-1);
                    map[ny][nx] = 2;
                    map[tail[0]][tail[1]] = 0;
                    snake.remove(ssize-1);
                }else{ // 뱀 몸이랑 겹침
                    flag = false;
                }
            }

            time++;

        }


        bw.write(time+"\n");




        br.close();
        bw.close();
    }
}
