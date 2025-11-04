import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Clique finder that uses lists and adjacency matrix. */
public class SimpleCliqueFinder {

    /** returns cliques as lists of Person, size >= minSize. */
    public static List<List<Person>> findCliquesAtLeast(List<Person> people, int minSize) {
        // Prepare data
        // Copy valid people into P
        // Build an adjacency matrix adj[i][j] that’s true if person i is friends with person j.
        List<Person> P = new ArrayList<>();
        for (Person p : people) if (p != null && p.idperson != null) P.add(p);

        int n = P.size();
        boolean[][] adj = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Person a = P.get(i);
            List<Person> fs = a.getFriends();
            if (fs == null) continue;
            for (Person f : fs) {
                int j = indexOfPerson(P, f);
                if (j >= 0) {
                    adj[i][j] = true;
                    adj[j][i] = true;
                }
            }
        }

        List<List<Person>> result = new ArrayList<>();

        // Backtracking: try each vertex as a starting root
        List<Integer> clique = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            // Build initial candidates = neighbors of r with index > r
            List<Integer> candidates = new ArrayList<>();
            for (int v = r + 1; v < n; v++) if (adj[r][v]) candidates.add(v);

            clique.add(r);
            expand(P, adj, clique, candidates, minSize, result);
            clique.remove(clique.size() - 1);
        }

        return result;
    }

    /** Linear search of a Person in the array */
    private static int indexOfPerson(List<Person> P, Person target) {
        if (target == null || target.idperson == null) return -1;
        for (int i = 0; i < P.size(); i++) {
            Person p = P.get(i);
            if (p != null && target.idperson.equals(p.idperson)) return i;
        }
        return -1;
    }

    /** Recursive backtracking */
    private static void expand(List<Person> P, boolean[][] adj,
                               List<Integer> clique, List<Integer> candidates,
                               int minSize, List<List<Person>> out) {
        // can’t reach minSize
        if (clique.size() + candidates.size() < minSize) return;

        // If we already meet the size, record (and continue to grow for larger cliques)
        if (clique.size() >= minSize) out.add(toPersons(P, clique));

        // Iterate over a snapshot because we'll modify candidates
        List<Integer> snapshot = new ArrayList<>(candidates);
        for (int idx = 0; idx < snapshot.size(); idx++) {
            int v = snapshot.get(idx);

            // v must be adjacent to everyone already in the clique
            boolean fits = true;
            for (int u : clique) {
                if (!adj[u][v]) { fits = false; break; }
            }
            if (!fits) {
                candidates.remove((Integer) v);
                continue;
            }

            // Build next candidate list = (candidates after v) ∩ N(v)
            List<Integer> next = new ArrayList<>();
            for (int j = idx + 1; j < snapshot.size(); j++) {
                int w = snapshot.get(j);
                if (candidates.contains(w) && adj[v][w]) next.add(w);
            }

            // Choose v
            clique.add(v);
            expand(P, adj, clique, next, minSize, out);
            clique.remove(clique.size() - 1);

            // Exclude v from future branches
            candidates.remove((Integer) v);
        }
    }

    private static List<Person> toPersons(List<Person> P, List<Integer> clique) {
        List<Person> res = new ArrayList<>(clique.size());
        for (int idx : clique) res.add(P.get(idx));
        return res;
    }

    /** Pretty print for console */
    public static void printCliques(List<List<Person>> cliques) {
        if (cliques.isEmpty()) {
            System.out.println("No cliques with size >= 5 found.");
            return;
        }
        int k = 1;
        for (List<Person> c : cliques) {
            String line = c.stream()
                    .map(p -> p.idperson + " (" + p.lastName + ")")
                    .collect(Collectors.joining(", "));
            System.out.println("#" + (k++) + " size=" + c.size() + " => " + line);
        }
    }
}
