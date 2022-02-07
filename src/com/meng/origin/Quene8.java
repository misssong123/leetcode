package com.meng.origin;


/**
 * 经典八皇后问题
 * 要在8*8的国际象棋棋盘中放8个皇后，使任意两个皇后都不能互相吃掉。规则：皇后能吃掉同一行、同一列、同一对角线的任意棋子。
 */
public class Quene8 {

    public static void main(String[] args) {
        Quene8 demo = new Quene8();
        demo.solve(4);
    }
    public void solve(int n){
        int[] temp = new int[n];
        solve(temp,0,n);
    }

    private void solve(int[] temp, int index,int n) {
        if (index == n){
            print(temp,n);
            return;
        }
        for(int i = 0 ; i<n;i++){
            if (check(index,temp,i)){
                temp[index] = i;
                solve(temp,index+1,n);
            }
        }
    }

    private void print(int[] temp,int n) {
        for (int i = 0 ;i<n;i++)
            System.out.print(temp[i]+"\t");
        System.out.println();
    }

    private boolean check(int index, int[] temp,int val) {
        for(int i=0;i<index;i++){
            if (temp[i]==val || index-i == (Math.abs(temp[i]-val)))
                return false;
        }
        return true;
    }
}
