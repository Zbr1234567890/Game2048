import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game2048 {
    static final int N = 4;
    static int[][] board = new int[N][N];

    public static void main(String[] args) {
        initBoard();
        printBoard();
        while (!isGameOver()) {
            System.out.print("请输入方向（WASD）：");
            char direction = new Scanner(System.in).nextLine().charAt(0);
            switch (direction) {
                case 'W':
                    moveUp();
                    break;
                case 'A':
                    moveLeft();
                    break;
                case 'S':
                    moveDown();
                    break;
                case 'D':
                    moveRight();
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    continue;
            }
            addRandomNumber();
            printBoard();
        }
        System.out.println("游戏结束！");
    }

    static boolean isGameOver() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
                if (i > 0 && board[i][j] == board[i - 1][j]) {
                    return false;
                }
                if (j > 0 && board[i][j] == board[i][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 初始化棋盘
    static void initBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
        addRandomNumber();
        addRandomNumber();
    }

    static void addRandomNumber() {
        List<int[]> empty = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    empty.add(new int[] {i, j});
                }
            }
        }
        if (empty.size() > 0) {
            int[] pos = empty.get((int) (Math.random() * empty.size()));
            board[pos[0]][pos[1]] = Math.random() < 0.9 ? 2 : 4;
        }
    }
    

    // 打印棋盘
    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 向上移动
    static void moveUp() {
        for (int j = 0; j < N; j++) {
            int cur = 0, next = 0;
            for (int i = 0; i < N; i++) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = i;
                    if (next != 0 && board[next][j] == board[cur][j]) {
                        board[next][j] *= 2;
                        board[cur][j] = 0;
                        cur = next;
                    }
                }
            }
            cur = 0;
            for (int i = 0; i < N; i++) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = i;
                    if (next != cur) {
                        board[next][j] = board[cur][j];
                        board[cur][j] = 0;
                        cur = next + 1;
                    } else {
                        cur++;
                    }
                }
            }
        }
    }

    // 向左移动
    static void moveLeft() {
        for (int i = 0; i < N; i++) {
            int cur = 0, next = 0;
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = j;
                    if (next != 0 && board[i][next] == board[i][cur]) {
                        board[i][next] *= 2;
                        board[i][cur] = 0;
                        cur = next;
                    }
                }
            }
            cur = 0;
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = j;
                    if (next != cur) {
                        board[i][next] = board[i][cur];
                        board[i][cur] = 0;
                        cur = next + 1;
                    } else {
                        cur++;
                    }
                }
            }
        }
    }

    // 向下移动
    static void moveDown() {
        for (int j = 0; j < N; j++) {
            int cur = N - 1, next = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = i;
                    if (next != N - 1 && board[next][j] == board[cur][j]) {
                        board[next][j] *= 2;
                        board[cur][j] = 0;
                        cur = next;
                    }
                }
            }
            cur = N - 1;
            // 向下移动（续）
            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = i;
                    if (next != cur) {
                        board[next][j] = board[cur][j];
                        board[cur][j] = 0;
                        cur = next - 1;
                    } else {
                        cur--;
                    }
                }
            }
        }
    }

    // 向右移动
    static void moveRight() {
        for (int i = 0; i < N; i++) {
            int cur = N - 1, next = N - 1;
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = j;
                    if (next != N - 1 && board[i][next] == board[i][cur]) {
                        board[i][next] *= 2;
                        board[i][cur] = 0;
                        cur = next;
                    }
                }
            }
            cur = N - 1;
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    next = cur;
                    cur = j;
                    if (next != cur) {
                        board[i][next] = board[i][cur];
                        board[i][cur] = 0;
                        cur = next - 1;
                    } else {
                        cur--;
                    }
                }
            }
        }
    }
}