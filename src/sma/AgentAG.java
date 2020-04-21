package sma;

import algos.AG;
import algos.City;
import algos.Route;
import conf.Settings;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.ArrayList;

public class AgentAG extends Agent {
    private static class RunBehaviour extends SimpleBehaviour {
        private final Route route;
        private boolean end = false;

        public RunBehaviour(Agent a, Route route) {
            super(a);
            this.route = route;
        }

        public void action() {
            AG ag = new AG(route);
            ag.run(true);
            System.out.println(ag.getBestSolution());
            System.out.println(ag.getBestDistance());
            end = true;
            done();
        }

        public boolean done() {
            return end;
        }
    }

    protected void setup() {
        System.out.println("Création de l'agent " + getLocalName());
        ArrayList<City> cities = Settings.loadFile("data/cities10.csv", true);
        addBehaviour(new RunBehaviour(this, new Route(cities)));

    }

    protected void takeDown() {
        System.out.println("Destruction de l'agent " + getLocalName());
    }
}
