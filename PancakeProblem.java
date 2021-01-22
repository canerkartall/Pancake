package Pancake;

import static Pancake.PancakeAction.SecondState;
import aima.core.agent.Action;
import aima.core.search.framework.problem.Problem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PancakeProblem implements Problem<PancakeState, Action> {

    PancakeState initialState;
    List<Action> listActions;
    PancakeState resultState;
    PancakeState GoalState;
    private double StepCost;
    int temp;

    PancakeProblem(PancakeState s) {
        initialState = s;
        System.out.println("initialState : " + initialState.state1);     
        PancakeState thirdState = new PancakeState();
        thirdState.state1=(ArrayList<Integer>) initialState.state1.clone();
        Collections.sort(thirdState.state1);
        GoalState = new PancakeState();
        listActions = new ArrayList<Action>();
        GoalState.state1 = thirdState.state1;
        System.out.println("Goalstate : " + GoalState.state1);                    
        for (int i = 2; i <= initialState.state1.size(); i++) {        
        String key = "Flip" + Integer.toString(i);
        listActions.add(new PancakeAction(key));        
        }
        
    }

    @Override
    public PancakeState getInitialState() {
        return initialState;
    }

    @Override
    public List<Action> getActions(PancakeState s) {
        return listActions;
    }

    @Override
    public PancakeState getResult(PancakeState s, Action a) {
        resultState = new PancakeState();
        initialState=s;
        for(int i4=0;i4<listActions.size();i4++){
           if(a==listActions.get(i4)){
               String key = "Flip" + Integer.toString(PancakeAction.getactions().get(i4));
               PancakeAction flip4 = new PancakeAction(key);
               resultState.state1=(ArrayList<Integer>) SecondState.state1.clone();
           }
        }
        resultState.state1=(ArrayList<Integer>) SecondState.state1.clone();
        return resultState;
    }

    @Override
    public boolean testGoal(PancakeState s) {
      boolean isEqual = s.state1.equals(GoalState.state1); 
               if (isEqual==true) {
               return true;
               } else {
               return false;
               }
    }

    @Override
    public double getStepCosts(PancakeState s, Action a, PancakeState s1) {
        s = initialState;
        s1 = SecondState;
        for(int i3=0;i3<listActions.size();i3++){
           if(a==listActions.get(i3))
               StepCost=PancakeAction.getactions().get(i3);
        }
        return StepCost;
    }

}
