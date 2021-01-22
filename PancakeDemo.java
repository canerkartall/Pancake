package Pancake;


import aima.core.agent.Action;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.NodeFactory;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.QueueSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.function.ToDoubleFunction;

public class PancakeDemo {

  
    static PancakeState initialState = new PancakeState();

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Number of pancakes:");
        n = sc.nextInt();
        
        for (int i2 = 0; i2 < n; i2++) {
            initialState.state1.add(r.nextInt(99) + 1);
        }
        System.out.println("Randomly Generated Initial State is :" + initialState.state1);

        // ----->>> Pancake Sort using shortest path to goal with using largest number index in state <<<-----
        /*
        int i;
        i = n - 1;
        while (i >= 0) {
        int a = 0;
        int q = 0;
        int index = 0;
        while (a <= i) {
        if (q < initialState.state1.get(a)) {
        index = a;
        q = initialState.state1.get(a);
        }
        a++;
        }
        String key="Flip"+Integer.toString(index+1);
        String key2="Flip"+Integer.toString(i+1);
        PancakeAction flip2=new PancakeAction(key);
        initialState.state1=SecondState.state1;
        PancakeAction flip3=new PancakeAction(key2);
        initialState.state1=SecondState.state1;
        i--;
        }
        System.out.println("Total Cost is :" + PancakeAction.getsum());
        
        */
        
        PancakeDemo();
    }

    private static void PancakeDemo() {
        int d;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter limit:");
        d = sc.nextInt();
        PancakeBFS();
        PancakeUCS();
        PancakeDLS(d);
        PancakeIDLS();
        PancakeDFS();
        /*PancakeGreedyBestFirst();
        PancakeAStar();*/
    }

    private static void PancakeBFS() {
        System.out.println("\nPancake Problem Breadth First Search -->");
        try {
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new BreadthFirstSearch<>(new GraphSearch<>());
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.println(agent.getActions().size());

            PancakeState s = problem.getInitialState();
            for (Action action : agent.getActions()) {
                System.out.println(s.state1);
                System.out.println(action.toString());
                s = problem.getResult(s, action);
            }
            System.out.println(s.state1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PancakeDFS() {
        System.out.println("\nPancake Problem Demo Depth First Search -->");
        try {
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new DepthFirstSearch<>(new GraphSearch<>());
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PancakeUCS() {
        System.out.println("\nPancake Problem Demo Uniform Cost Search -->");
        try {
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new UniformCostSearch<>(new GraphSearch<>());
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PancakeIDLS() {
        System.out.println("\nPancake Problem Demo Iterative Deepening Search -->");
        try {
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new IterativeDeepeningSearch<>();
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PancakeDLS(int depthLimit) {
        System.out.println("\nPancake Problem Demo Depth Limited Search -->");
        try {
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new DepthLimitedSearch<>(depthLimit);
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PancakeGreedyBestFirst() {
        System.out.println("\nPancake Problem Demo Greedy Best First Search -->");
        NodeFactory nd = new NodeFactory();
        try {
            QueueSearch impl = new QueueSearch(nd) {
                @Override
                public Optional findNode(Problem prblm, Queue queue) {
                   throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            ToDoubleFunction h = new ToDoubleFunction() {
                @Override
                public double applyAsDouble(Object value) {
                    return 1;
                }
            };
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new GreedyBestFirstSearch<>(impl, h);
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PancakeAStar() {
        System.out.println("\nPancake Problem AStar Search (largest out of order heuristic)-->");
        NodeFactory nd = new NodeFactory();
        try {
            QueueSearch impl = new QueueSearch(nd) {
                @Override
                public Optional findNode(Problem prblm, Queue queue) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            ToDoubleFunction h = new ToDoubleFunction() {
                @Override
                public double applyAsDouble(Object value) {
                    return 1;
                }
            };
            Problem<PancakeState, Action> problem = new PancakeProblem(initialState);
            SearchForActions<PancakeState, Action> search = new AStarSearch<>(impl, h);
            SearchAgent<Object, PancakeState, Action> agent = new SearchAgent<>(problem, search);
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printInstrumentation(Properties properties) {
        properties.keySet().stream().map(key -> key + "=" + properties.get(key)).forEach(System.out::println);
    }

    private static void printActions(List<Action> actions) {
        actions.forEach(System.out::println);
    }
}
