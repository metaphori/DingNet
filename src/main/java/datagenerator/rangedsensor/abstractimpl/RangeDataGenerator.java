package datagenerator.rangedsensor.abstractimpl;

import datagenerator.SensorDataGenerator;
import datagenerator.rangedsensor.api.Cell;
import datagenerator.rangedsensor.api.RangeSensor;
import datagenerator.rangedsensor.api.TimeUnit;
import iot.Environment;
import util.Pair;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *      -------------
 *      | 1 | 2 | 3 |
 *      -------------
 *      | 4 | 5 | 6 |
 *      -------------
 *      | 7 | 8 | 9 |
 *      -------------
 */
abstract public class RangeDataGenerator implements SensorDataGenerator {
    //FIXME put all final
    protected static SensorDataGenerator instance;
    protected int row;
    protected int columns;
    protected final int width;
    protected final int height;
    protected RangeSensor defaultLevel;
    protected TimeUnit timeUnit;
    protected Map<Integer, List<Cell>> map;

    public RangeDataGenerator() {
        width = Environment.getMapWidth();
        height = Environment.getMapHeight();
    }

    @Override
    public byte[] generateData(int x, int y, LocalTime time) {
        return map.getOrDefault(calcSquare(x, y), new LinkedList<>()).stream()
            .filter(c -> c.getFromTime() < timeUnit.convertFromNano(time.toNanoOfDay()))
            .findFirst()// the list of cell is ordered for time
            .map(Cell::getLevel)
            .orElse(defaultLevel)
            .getValue();
    }

    @Override
    public byte[] generateData(Pair<Integer, Integer> pos, LocalTime time) {
        return generateData(pos.getLeft(), pos.getRight(), time);
    }

    @Override
    public double nonStaticDataGeneration(double x, double y) {
        return 0.0;
    }

    private int calcSquare(int x, int y) {
        //`(height - y)` because in the simulator environment the origin is in the bottom left corner
        int moteRow = (height - y) / (height/row);
        int moteCol = x/ (width/columns);
        return moteRow * columns + moteCol;
    }

    @Override
    public void reset() {

    }
}