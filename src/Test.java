import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();
        stack.push("Google");
        stack.push("Youtube");

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        Queue<String> queue = new LinkedList<>();
        queue.add("Google");
        queue.add("Youtube");
        while(!queue.isEmpty()){
            System.out.println(queue.remove());
        }
    }
}
