import java.util.*;

public class ShortestChainConnectingPeople {

    public static List<Person> findShortestChain(List<Person> people, Person start, Person target) {
        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> previous = new HashMap<>();
        Set<Person> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            if (current.equals(target)) {
                break;
            }

            for (Person friend : current.getFriends()) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    previous.put(friend, current);
                    queue.add(friend);
                }
            }
        }
        List<Person> shortestChain = new ArrayList<>();
        for (Person at = target; at != null; at = previous.get(at)) shortestChain.add(at);
        Collections.reverse(shortestChain);

        if (!shortestChain.isEmpty() && shortestChain.get(0).equals(start)) {
            return shortestChain;
        } else {
            return Collections.emptyList();
        }
    }
}
