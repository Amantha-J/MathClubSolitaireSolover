
import java.util.LinkedList;
import java.util.ListIterator;

public class Solitaire {
    private static LinkedList<Integer> stacks;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        stacks = new LinkedList<>();
        int outputMode = 0;

        for (String num: args) {
            if (num.equals("-a") || num.equals("-c") || num.equals("-i")) {
                outputMode = switch (num) {
                    case "-c" -> 1;
                    case "-i" -> 2;
                    default -> outputMode;
                };
                break;
            }
            stacks.add(Integer.parseInt(num));
        }

        LinkedList<String> pastStates = new LinkedList<>();
        String lastState = stacks.toString();
        int newStack;

        while(!pastStates.contains(lastState)) {
            newStack = 0;

            pastStates.add(stacks.toString());
            ListIterator<Integer> listChecker = stacks.listIterator();
            while (listChecker.hasNext()) {
                int currentStack = listChecker.next();
                if (currentStack == 1) {
                    listChecker.remove();

                } else {
                    listChecker.set(currentStack -1);

                }
                newStack++;
            }
            //System.out.println(lastState);
            stacks.add(newStack);
            stacks.sort(Integer::compareTo);
            lastState = stacks.toString();
        }

        int preCycleStates = pastStates.indexOf(lastState);

        switch (outputMode) {
            case 0:
                for (String state: pastStates) {
                    System.out.println(state);
                }
                System.out.println(lastState);
                break;
            case 1:
                for(int i = preCycleStates; i < pastStates.size(); i++) {
                    System.out.println(pastStates.get(i));
                }
                System.out.println(lastState);
                break;
        }

        System.out.println("For Input: " +pastStates.getFirst());
        System.out.println(preCycleStates + " States before loop");
        System.out.println("Cycle of " + (pastStates.size() - preCycleStates) + " found");

        System.out.println("Time Taken: " + (System.currentTimeMillis() -startTime + " Milliseconds"));
    }
}
