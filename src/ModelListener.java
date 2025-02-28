import java.util.Map;

public interface ModelListener {
    void onModelChanged(Map<Car, Coords> carPoints);
}