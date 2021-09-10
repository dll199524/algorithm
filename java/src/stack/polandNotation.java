package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class polandNotation  {
    public static void main(String[] args) {
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
        List<String> list = getListString(suffixExpression);
        int res = calculate(list);
        System.out.println("计算的结果是 = " + res);

        String expression = "1+((2+3)*4)-5";// 注意表达式
        List<String> infix = InfixExperssionToList(expression);
        System.out.println("中缀表达式对应的List=" + infix); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffix = infixToSuffix(infix);
        System.out.println("后缀表达式对应的List" + suffix); // ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.printf("expression=%d", calculate(suffix)); // ?
        
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    // 方法：将 中缀表达式转成对应的List
    // s="1+((2+3)×4)-5";
    public static List<String> InfixExperssionToList(String infix) {
        List<String> list = new ArrayList<>();
        int i = 0;
        char c;
        String str;
        do {
            c = infix.charAt(i);
            if (c < 48 || c > 57) {
                list.add(c + "");
                i++;
            }
            else {
                str = "";
                if (i < infix.length() && c >= 48 && c <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < infix.length());
        return list;
    }

    // 即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
    // 方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> infixToSuffix(List<String> list) {
        Stack<String> operStack = new Stack<>();
        List<String> tempList = new ArrayList<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                tempList.add(s);
            } else if (s.equals("(")) {
                operStack.push(s);
            } else if (s.equals(")")) {
                String oper = operStack.peek();
                while (!oper.equals("(")) {
                    tempList.add(s);
                }
                operStack.pop();
            } else {
                while (operStack.size() != 0 && Operation.getValue(s) >= Operation.getValue(operStack.peek())) {
                    tempList.add(operStack.pop());
                }
                operStack.push(s);
            }
        }
        while (operStack.size() != 0) {
            tempList.add(operStack.pop());
        }
        return tempList;
    }


}

class Operation {
	private static int LEFT_BRACKET  = 0;
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	// 写一个方法，返回对应的优先级数字
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "(":
			result = LEFT_BRACKET;
			break;
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("不存在该运算符" + operation);
			break;
		}
		return result;
	}
}