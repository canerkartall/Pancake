package Pancake;

import aima.core.agent.impl.DynamicAction;
import java.util.ArrayList;

public class PancakeAction extends DynamicAction {

    static PancakeState SecondState = Pancake.PancakeDemo.initialState;
    static ArrayList<Integer> actions = new ArrayList<>();
    public String name;
    static int sum=0;
    

    public PancakeAction(String Flip) {
        super(Flip);
        name = Flip;

        String strArray[] = name.split("p");
        Integer result = Integer.valueOf(strArray[1]);
        if (result > 1) {
            int i = result - 1;
            int temp, start = 0;
            while (start < i) {
                int index = SecondState.state1.get(i);
                temp = SecondState.state1.get(start);
                SecondState.state1.set(start, index);
                SecondState.state1.set(i, temp);
                start++;
                i--;
            }
            System.out.println("Flip" + result + " " + "Action has worked. New State is:" + SecondState.state1 + " == " + "Cost:" + result);
            actions.add(result);
            sum += result;
        }
    }
    public static PancakeState get(){
    return SecondState;
    }
    public static ArrayList<Integer> getactions(){
    return actions;
    }
    public static int getsum(){
    return sum;
    }
}
