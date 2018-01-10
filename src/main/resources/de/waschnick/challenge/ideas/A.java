package de.waschnick.challenge.ideas;

class A{public static void main(String[] a){for(int i=0;i<100;i++,System.out.println(i%3==0||i%5==0?((i%3)==0?"fizz":"")+((i%5)==0?"buzz":""):i));}}