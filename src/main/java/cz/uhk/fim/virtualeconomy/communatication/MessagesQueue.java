package cz.uhk.fim.virtualeconomy.communatication;

import cz.uhk.fim.virtualeconomy.common.LoggedObject;
import cz.uhk.fim.virtualeconomy.model.MessageEntity;
import org.ubercraft.statsd.StatsdCounter;
import org.ubercraft.statsd.StatsdLoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;


/**
 * @author Tomáš Kolinger <tomas@kolinger.name>
 */
public class MessagesQueue extends LoggedObject {

    private Map<UUID, Queue<MessageEntity>> queue = new HashMap<UUID, Queue<MessageEntity>>();
    private StatsdCounter queueCounter = StatsdLoggerFactory.getLogger("statsd.virtualeconomy.queue");

    public synchronized void addMessage(MessageEntity message) {
        queueCounter.infoCount();
        for (MessageEntity.Address receiver : message.getReceivers()) {
            Queue<MessageEntity> messages;
            if (queue.containsKey(receiver.getAgentId())) {
                messages = queue.get(receiver.getAgentId());
                messages.add(message);
            } else {
                messages = new LinkedList<MessageEntity>();
                messages.add(message);
                queue.put(receiver.getAgentId(), messages);
            }
        }
    }

    public synchronized MessageEntity search(UUID agentId) {
        queueCounter.infoCount(-1);
        if (queue.containsKey(agentId)) {
            Queue<MessageEntity> messages = queue.get(agentId);
            return messages.poll();
        }
        return null;
    }

    public synchronized MessageEntity searchByType(UUID agentId, MessageEntity.Type type) {
        queueCounter.infoCount(-1);
        if (queue.containsKey(agentId)) {
            Queue<MessageEntity> messages = queue.get(agentId);
            for (MessageEntity message : messages) {
                if (message.getType() == type) {
                    messages.remove(message);
                    return message;
                }
            }
        }
        return null;
    }
}
