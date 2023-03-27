package com.meng.collect.str;

import java.util.ArrayList;
import java.util.List;

public class T0002LumpyTrie {
    public List<String> LumpyTrie(String[] dicts,String[] names){
        List<String> res = new ArrayList<>();
        TreeNode node = new TreeNode();
        for(String dict : dicts){
            node.build(dict);
        }
        for (String name : names){
            res.add(node.contains(name));
        }
        return res;
    }

    public static void main(String[] args) {
        String[] dicts = {"a","b","c","ab","abc"};
        String[] names = {"a","a","e"};
        T0002LumpyTrie demo = new T0002LumpyTrie();
        System.out.println(demo.LumpyTrie(dicts,names));
    }
}
class TreeNode{
    public int count;
    public boolean endFlag;
    public TreeNode[] child;
    TreeNode(){
        count = 0;
        endFlag = false;
        child = new TreeNode[26];
    }
    public String contains(String name){//WRONG
        TreeNode temp = this;
        for(char c : name.toCharArray()){
            int index = c - 'a';
            if (temp.child[index]==null){
                return "WRONG";
            }
            temp = temp.child[index];
        }
        if (!temp.endFlag){
            return "WRONG";
        }
        temp.count++;
        return temp.count == 1?"OK":"REPEAT";
    }
    public void build(String name){
        TreeNode temp = this;
        for(char c : name.toCharArray()){
            int index = c-'a';
            if (temp.child[index]==null){
                temp.child[index] = new TreeNode();
            }
            temp = temp.child[index];
        }
        temp.endFlag = true;
    }
}