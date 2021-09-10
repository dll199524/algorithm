package stack;

public class circleStack {
    public static void main(String[] args) {

        String expression = "7*2*2-5+1-5+3-4-11";    //18
        CalStack numStack = new CalStack(10);
        CalStack operStack = new CalStack(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = ' ';
        char ch = ' ';
        int res = 0;
        String keepNum = "";
        
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        operStack.push(ch);
                        numStack.push(res);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }  
            }
            index++;
            if (index >= expression.length()) break;
        }
        while (true) {
            if (operStack.isEmpty()) break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        res = numStack.pop();
        System.out.printf("表达式%s = %d", expression, res);
    }
}

class CalStack {
    private int size;
    private int[] stack;
    private int top = -1;
    public CalStack(int size) {
        this.size = size;
        stack = new int[this.size];
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public int peek() {
        return stack[top];
    }
    public boolean isFull() {
        return top == size - 1;
    }
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈空");
            return 0;
        }
        int res = stack[top];
        top--;
        return res;
    }
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d", i, stack[i]);
        }
    }
    public int priority(int oper) {
        if (oper == '*' || oper == '/') return 1;
        else if (oper == '+' || oper == '-') return 0;
        else return -1;
    }
    public boolean isOper (char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }
    public int cal(int num1, int num2, int oper) {
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
        }
        return res;
    }
}