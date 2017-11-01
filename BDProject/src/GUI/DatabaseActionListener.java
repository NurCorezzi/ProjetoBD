package GUI;
import Persistencia.DatabaseResponse;

public interface DatabaseActionListener {
	public DatabaseResponse queryRequested(String query);

}
