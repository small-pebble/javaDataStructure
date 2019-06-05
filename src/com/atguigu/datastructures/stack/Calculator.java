package com.atguigu.datastructures.stack;

/**
 * @Auther:smallPebble
 * @Date:2019/6/4
 * @Description:com.atguigu.datastructures.stack
 * @version:1.0
 **/
public class Calculator {
    public static void main(String[] args) {
        String expression = "4000*1+9";
        String keepNum = "";
        char ch;
        char oper;
        int num1;
        int num2;
        int index = 0;
        int res;
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //1.逐个取出expression中的字符，判断是否为操作符
        while (index < expression.length()) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                //3.操作符，再判断操作符栈中是否有操作符
                if (operStack.isEmpty()) {
                    //4.如果没有，操作符直接入栈
                    operStack.push(ch);
                } else {
                    //5.如果有操作符，再判断此操作符与栈中操作符的优先级
                    if (operStack.prioroty(ch) <= operStack.prioroty(((char) operStack.peek()))) {
                        //6.如果此操作符的优先级小于等于栈中的，则先拿出两个数与栈中的操作符计算，再将结果压入数栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        res = operStack.oper(num1, num2, oper);
                        numStack.push(res);
                        index--;
                    } else {
                        //7.如果此操作符的优先级大于栈中的，则直接将操作符入栈
                        operStack.push(ch);
                    }
                }
            } else {
                //2.如果是数，直接压入数栈,判断字符串下一个是否为数
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(ch - 48);
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
        }
        //8.逐个拿出数栈和操作符栈中的进行计算
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            res = operStack.oper(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf(expression + "=%d", numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;
    private int top = -1;
    private int[] data;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int e) {
        if (isFull()) {
            System.out.println("栈满，不能添加数据");
            return;
        }
        data[++top] = e;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int res = data[top];
        top--;
        return res;
    }

    public int peek() {
        return data[top];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, data[i]);
        }
    }

    public boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public int prioroty(char ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public int oper(int num1, int num2, char oper) {
        if (oper != '+' && oper != '-' && oper != '*' && oper != '/') {
            throw new IllegalArgumentException("操作数不合法");
        }
        int res = 0;
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}