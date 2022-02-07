package com.meng.origin;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class Solve {
    public static void main(String[] args) {
        Solve solve = new Solve();
        char[] [] board ={{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        solve.solve(board);
    }

    /**
     * 1.遍历第一行，最后一行，第一列，最后一列
     * 2.查找是否为O
     * 3.若为O,则以该位置为起点，查找其右，下，左，上是否为O
     * 4.若为O，则继续第三步，否则进行下一个查找
     * @param board
     */
    public void solve(char[][] board) {
        if(board.length ==0)
            return;
        List<String> list = new ArrayList<>();
        //遍历第一行最后一行
        for (int i = 0 ;i<board[0].length;i++){
            solveMove(0,i,list,board);
            solveMove(board.length-1,i,list,board);
        }
        //遍历两侧
        for (int i =1 ;i<board.length-1;i++){
            solveMove(i,0,list,board);
            solveMove(i,board[0].length-1,list,board);
        }
        printSolve(list,board);
    }
    //执行右下左上操作
    public void solveMove(int i,int j,List<String>list,char[][] board){
        if( i>=0 && i<board.length && j>=0 && j<board[0].length  ){
            if (list.contains(i+","+j) || board[i][j] == 'X'){
                return;
            }
            list.add(i+","+j);
            //执行右操作
            solveMove(i,j+1,list,board);
            //执行下操作
            solveMove(i+1,j,list,board);
            //执行左操作
            solveMove(i,j-1,list,board);
            //执行上操作
            solveMove(i-1,j,list,board);
        }
    }
    //打印结果
    public void printSolve(List<String>list,char[][]board){
        for (int i = 0;i<board.length;i++){
            for (int j = 0 ;j<board[i].length;j++){
                if (list.contains(i+","+j))
                    board[i][j]='O';
                else
                    board[i][j]='X';
            }
        }
        print(board);
    }
    public void print(char[][] board){
        for (int i = 0;i<board.length;i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
    }
    //执行右下操作
    public void solveRightDown(int i,int j,List<String>list,char[][] board){
        //i>=0 && i<board.length && j<board[0].length && j>=0
        if( i<board.length && j<board[0].length ){
            if (list.contains(i+","+j) || board[i][j] == 'X'){
                return;
            }
            //添加进List
            list.add(i+","+j);
            //执行右操作
            solveRightDown(i,j+1,list,board);
            //执行下操作
            solveRightDown(i+1,j,list,board);
        }

    }
    //执行左上操作
    public void solveLeftUp(int i,int j,List<String>list,char[][] board){
        if(i>=0 && j>=0){
            if (list.contains(i+","+j ) || board[i][j] == 'X'){
                return;
            }
            //执行左操作
            solveLeftUp(i,j-1,list,board);
            //执行上操作
            solveLeftUp(i-1,j,list,board);
        }


    }

}
