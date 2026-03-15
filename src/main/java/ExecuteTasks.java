import java.util.*;
import java.util.stream.Collectors;

// od机试题目：给出一个数，给出一个数组
// 5
// [[1,2],[],[],[0],[3,4]] = arr
//  数组索引代表任务编号从0到N，arr[0] = [1,2]代表0号任务的前置任务是1号和2号任务，arr[1] = []代表1号任务没有前置任务
// 现在开启扫描，第一轮扫描输出初始任务，第二轮扫描输出已经准备好的任务，必须所有前置任务完成的任务才算准备好，直到输出所有轮任务

// 原始输入是这样的,第一行是任务个数，剩下的每一行代表任务的前置任务编号集合
// 5
// 1 2
// -1
// -1
// 0
// 3 4
public class ExecuteTasks {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String s = in.nextLine();
            if (s.equals("-1")){
                map.add(new ArrayList<>());
            }else {
                List<Integer> list = Arrays.stream(s.split(" ")).map(Integer::parseInt)
                                .collect(Collectors.toList());
                map.add(list);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            if (map.get(i).isEmpty()){
                queue.add(i);
            }
        }
        int[] visited = new int[n];
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++){
                int x = queue.poll();
//                if (visited[x] == 1){
//                    continue;
//                }
                curLevel.add(x);
                for(int j = 0; j < map.size(); j++){
                    if (map.get(j).contains(x) && visited[j]!=1){
                        visited[j] = 1;
                        queue.add(j);
                    }
                }
            }
            res.add(curLevel);
        }
        for (int i = 0; i < res.size(); i++){
            for (int j = 0; j < res.get(i).size(); j++){
                System.out.print(res.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println(res);
    }

    public static void bfs(List<List<Integer>> map){

    }
}
