package algorithms;

import java.util.Scanner;
import java.util.Stack;

public class Evaluate
{
    /**
     * 计算一个简单的算术表达式
     * @param args
     */
    public static void main(String[] args)
    {
        Stack<Character> ops=new Stack<>();
        Stack<Double> vals=new Stack<>();

        Scanner in=new Scanner(System.in);
        String  exp=in.nextLine();
        for (int i = 0; i < exp.length(); i++)
        {
           char ch=exp.charAt(i);
           if(ch=='('||ch==' ');
           else if(ch=='+')
               ops.push(ch);
           else if(ch=='-')
               ops.push(ch);
           else if(ch=='*')
               ops.push(ch);
           else if(ch=='/')
               ops.push(ch);
           else if(ch==')')
           {
               char op=ops.pop();
               double v=vals.pop();
               if(op=='+')
                   v+=vals.pop();
               else if(op=='-')
                   v-=vals.pop();
               else if(op=='*')
                   v*=vals.pop();
               else
                   v/=vals.pop();
               vals.push(v);
           }
           else
               vals.push(Double.parseDouble(ch+""));
        }

        System.out.println(exp+"结果是："+vals.pop());
    }
}
