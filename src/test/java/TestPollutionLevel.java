import org.junit.jupiter.api.Test;
import util.Pair;
import util.pollution.PollutionLevel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPollutionLevel {
    @Test
    void happyDay() {
        PollutionLevel level = new PollutionLevel(0.5);
        assertEquals(level.getPollutionFactor(), 0.5);
    }

    @Test
    void rainyDay() {
        assertThrows(IllegalArgumentException.class, () -> {
            PollutionLevel level = new PollutionLevel(4.5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PollutionLevel level = new PollutionLevel(-1.0);
        });
    }

    @Test
    void meanPollutionLevels() {
        List<Pair<Double, PollutionLevel>> input = new ArrayList<>();
        input.add(new Pair<>(30.0, new PollutionLevel(0.9)));
        input.add(new Pair<>(10.0, new PollutionLevel(0.5)));
        input.add(new Pair<>(20.0, new PollutionLevel(0.15)));

        assertEquals(PollutionLevel.getMediumPollution(input).getPollutionFactor(), 0.5);
    }

}
