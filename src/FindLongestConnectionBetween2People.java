import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FindLongestConnectionBetween2People {
     public static void chainFinder(String  person1ID, String person2ID){
         Set<String> examined = new HashSet<>();
         System.out.println("Longest chain: " + chainFinderRec( person1ID, person2ID, 0, -1, examined));

     }
    public static int chainFinderRec( String personCurrent, String personTarget, int currentDistance, int maxDistance, Set<String> examined) {
        //System.out.println("Visiting: " + personCurrent + " at distance " + currentDistance + ", examined size: " + examined.size());
        if (currentDistance > 12) return maxDistance;
        examined.add(personCurrent);
        if (Objects.equals(personCurrent, personTarget)) {
            examined.remove(personCurrent);
            return currentDistance;
        }

        for (String friend : SocialNetwork.findById(personCurrent).getFriendsID()) {
            //System.out.println("  Checking friend: " + friend + ", examined contains: " + examined.contains(friend));
            if (!examined.contains(friend)) {
                int result = chainFinderRec( friend, personTarget, currentDistance + 1, maxDistance, examined);
                maxDistance = Math.max(result, maxDistance);
            }
        }

        examined.remove(personCurrent);
        examined.remove(personCurrent);
        //System.out.println("Returning from " + personCurrent + ": " + maxDistance);
        return maxDistance;
    }
}
