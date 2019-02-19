/**
 * Graph Interface
 * @author psing
 *
 * @param <E>
 */

public interface Graph<E> {
	public void addNode (E n);
	public void addEdge (E from, E to, int distance);
	public E getNode (String name);
	public void printMap();

}
