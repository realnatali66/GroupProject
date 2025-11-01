import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LongestChainConnectingPeople {
     public static void chainFinder(String  person1ID, String person2ID){
         if (SocialNetwork.findById(person1ID) == null || SocialNetwork.findById(person2ID) == null){
             System.out.println("Error: either person's id is incorrect");
             return;
         }
         System.out.println("Finding longest chain...");
         Set<String> examined = new HashSet<>();
         ArrayList<String> chain = chainFinderRec( person1ID, person2ID, examined, new ArrayList<>());
         if (chain.size() >= 13)
             System.out.println("Error: maximum chain length reached: " + chain.size());
         else
             System.out.println("Longest chain: " + chain +  " with length: " + chain.size());


     }
    public static ArrayList<String> chainFinderRec(String personCurrent, String personTarget,
                                                   Set<String> examined, ArrayList<String> chain) {
        examined.add(personCurrent);
        chain.add(personCurrent);

        ArrayList<String> longestChain = new ArrayList<>();

        if (Objects.equals(personCurrent, personTarget)) {
            longestChain = new ArrayList<>(chain);
            chain.remove(chain.size() - 1);
            examined.remove(personCurrent);
            return longestChain;
        }

        if (chain.size() > 13) {
            chain.remove(chain.size() - 1);
            examined.remove(personCurrent);
            return longestChain;  // Return empty if depth exceeded
        }

        for (String friend : SocialNetwork.findById(personCurrent).getFriendsID()) {
            if (!examined.contains(friend)) {
                ArrayList<String> result = chainFinderRec(friend, personTarget, examined, chain);
                if (result.size() > longestChain.size()) {
                    longestChain = result;
                }
            }
        }

        chain.remove(chain.size() - 1);
        examined.remove(personCurrent);
        return longestChain;
    }
}
