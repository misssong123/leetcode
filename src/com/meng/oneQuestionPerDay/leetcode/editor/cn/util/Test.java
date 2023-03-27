package com.meng.oneQuestionPerDay.leetcode.editor.cn.util;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>((Comparator.comparingInt(Person::getScore)));
        Map<Integer,Person> map = new HashMap<>();
        for(int i = 0 ; i < 10 ; i ++){
            Person person = new Person(i,i);
            map.put(i,person);
            priorityQueue.add(person);
        }
        map.get(2).setScore(2222);
        map.get(3).setScore(99999);
        map.get(8).setScore(-9);
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }
}

class Person{
    private int age;
    private int score;
    public Person(){

    }
    public Person(int age,int score){
        this.age = age;
        this.score = score;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", score=" + score +
                '}';
    }
}