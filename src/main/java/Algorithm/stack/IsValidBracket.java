package Algorithm.stack;

import java.util.Stack;

public class IsValidBracket {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i =0; i < s.length(); i++){
            char c = s.charAt(i);

            if(!stack.isEmpty()){
                char top = stack.peek();
                if((top == '(' && c == ')') || (top == '[' && c == ']') || (top == '{' && c == '}')){
                    stack.pop();
                }
                else{
                    stack.push(c);
                }
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
