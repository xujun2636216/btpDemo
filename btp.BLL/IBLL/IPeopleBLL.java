package IBLL;
import btpEntity.People;
import java.util.List;
import java.util.Map;


public interface IPeopleBLL {
    List<People> getlist();

    Map<String, People> getPeoplelist();
}
