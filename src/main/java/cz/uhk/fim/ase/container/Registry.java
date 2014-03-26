package cz.uhk.fim.ase.container;

import cz.uhk.fim.ase.common.LoggedObject;
import cz.uhk.fim.ase.model.AgentEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Local agents registry.
 *
 * @author Tomáš Kolinger <tomas@kolinger.name>
 */
public class Registry extends LoggedObject {

    private Set<AgentEntity> agents = new HashSet<AgentEntity>();

    private Map<String, Long> nodes = new HashMap<String, Long>();

    private static Registry instance;

    public static Registry get() {
        if (instance == null) {
            instance = new Registry();
        }
        return instance;
    }

    public void register(AgentEntity agent) {
        if (!agents.contains(agent)) {
            agents.add(agent);
        }
    }

    public void updateNode(String node, Long tick) {
        nodes.put(node, tick);
    }

    public Map<String, Long> getNodes() {
        return nodes;
    }

    public Set<AgentEntity> getAgents() {
        return agents;
    }

    public AgentEntity getRandomAgent1() {
        int size = agents.size();
        if (size == 0) {
            return null;
        }

        AgentEntity value = null;
        Integer item = new Random().nextInt(size);
        Integer i = 0;
        for (AgentEntity agent : agents) {
            if (i.equals(item)) {
                value = agent;
            }
            i++;
        }
        return value;
    }
}
