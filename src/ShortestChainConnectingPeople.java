import java.util.*;

public class ShortestChainConnectingPeople {

    public static List<String> findShortestChain(List<Person> people, String start, String target) {
        if (SocialNetwork.findById(start) == null || SocialNetwork.findById(target) == null){
            System.out.println("Error: either person's id is incorrect");
            return new ArrayList<>();
        }
        Queue<String> queue = new LinkedList<>();
        Map<String, String> previous = new HashMap<>();
        Set<String > visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(target)) {
                break;
            }

            for (String friend : Objects.requireNonNull(SocialNetwork.findById(current)).getFriendsID()) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    previous.put(friend, current);
                    queue.add(friend);
                }
            }
        }
        List<String> shortestChain = new ArrayList<>();
        for (String at = target; at != null; at = previous.get(at)) shortestChain.add(at);
        Collections.reverse(shortestChain);

        if (!shortestChain.isEmpty() && shortestChain.get(0).equals(start)) {
            return shortestChain;
        } else {
            return Collections.emptyList();
        }
    }
}
