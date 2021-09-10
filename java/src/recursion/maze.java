package recursion;

public class maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板, 1 表示
        map[3][1] = 1;
        map[3][2] = 1;
        map[4][4] = 1;
        map[5][4] = 1;
        map[6][4] = 1;
        map[4][4] = 1;

        System.out.println("地图的情况");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);

        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static boolean setWay(int[][] map, int i, int j) {
        // 按照策略 下->右->上->左 走
        if (map[6][5] == 2) return true;
        else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) return true;
                else if (setWay(map, i, j + 1)) return true;
                else if (setWay(map, i - 1, j)) return true;
                else if (setWay(map, i, j - 1)) return true;
                else {
                    map[i][j] = 3;
                    return false;
                }  
            } else {
                return false;
            }
        }
    }

    public static boolean setWay2(int[][] map, int i, int j) {
        // 按照策略 上->右->下->左
        if (map[6][5] == 2) return true;
        else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay2(map, i - 1, j)) return true;
                else if (setWay2(map, i, j - 1)) return true;
                else if (setWay2(map, i + 1, j)) return true;
                else if (setWay2(map, i, j + 1)) return true;
                else {
                    map[i][j] = 3;
                    return false;
                }  
            } else {
                return false;
            }
        }
    }
}
