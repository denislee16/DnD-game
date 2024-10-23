import java.util.Comparator;

public class ReverseStatisticComparator implements Comparator<Statistic> {
    @Override
    public int compare(Statistic stat1, Statistic stat2){
        return -(stat1.getCount() - stat2.getCount());
    }
}
