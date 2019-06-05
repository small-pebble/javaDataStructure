package com.atguigu.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther:smallPebble
 * @Date:2019/6/4
 * @Description:com.atguigu.datastructures.stack
 * @version:1.0
 **/
public class PolandNotation {
    public static void main(String[] args) {
        //1.将中缀表达式字符串，转为中缀表达式的List
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);
        System.out.println("==============================");
        //2.将中缀的List转为后缀的List
        List<String> suffixExpression = parseSuffixExpression(infixExpression);
        System.out.println(suffixExpression);
        //3.将后缀的List计算出结果

       /* String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        System.out.println(calculate(rpnList));*/
    }

    //将中缀的List转为后缀的List
    public static List<String> parseSuffixExpression(List<String> infix){
        Stack<String> s1 = new Stack();
        List<String> s2 = new ArrayList<>();
        //逐个取出字符串判断
        for(String temp:infix){
            if(temp.matches("\\d+")){
                s2.add(temp);
            }else if(temp.equals("(")){
                s1.push(temp);
            }else if(temp.equals(")")){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                while(!s1.isEmpty()&&Optional.priority(temp)<=Optional.priority(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(temp);
            }
        }
        //将s1中的字符串添加到s2
        while(!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpression(String s){
        List<String> list = new ArrayList<>();
        char ch ;
        String num = "";
        for(int i=0;i<s.length();i++){
            ch = s.charAt(i);
            if(ch<48||ch>57){
                list.add(String.valueOf(ch));
            }else{
                num+=ch;
                i++;
                while(i<s.length() && s.charAt(i)>=48 && s.charAt(i)<=57){
                    num+=s.charAt(i);
                    i++;
                }
                i--;
                list.add(num);
                num="";
            }
        }
        return list;
    }

    public static List<String> getListString(String suffixExpression){
        String[] strings = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(int i=0;i<strings.length;i++){
            list.add(strings[i]);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        String cur ;
        int num1;
        int num2;
        int res;
        for(int i=0;i<ls.size();i++){
            cur = ls.get(i);
            if(cur.matches("\\d+")){
                stack.push(cur);
            }else{
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                res = oper(num1,num2,cur);
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static int oper(int num1,int num2,String oper){
        int res = 0;
        switch (oper){
            case "+":
                res = num1+num2;
                break;
            case "-":
                res = num1-num2;
                break;
            case "*":
                res = num1*num2;
                break;
            case "/":
                res = num1/num2;
                break;
            default:
                //System.out.println("输入的操作数有误");
                break;
        }
        return res;
    }
}

class Optional{
    static final int ADD=1;
    static final int SUB=1;
    static final int MUL=2;
    static final int DIV=2;
    public static int priority(String oper){
        int res = 0;
        switch (oper){
            case "+":
                res = ADD;
                break;
            case "-":
                res=SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
            default:
                //System.out.println("输入运算符有误");
                break;
        }
        return res;
    }
}


