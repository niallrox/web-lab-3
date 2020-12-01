package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AreaCheckerTest {
    private AreaChecker areaChecker;
    private double r;

    @BeforeEach
    public void init() {
        areaChecker = new AreaChecker();
        r = 1.0;
    }

    @Test
    void triangleBorderUpTrue() {
        double x = 0, y = 0.5;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void triangleBorderUpFalse() {
        double x = 0, y = 0.5000000000000001;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void triangleBorderRightTrue() {
        double x = 1.0, y = 0;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void triangleBorderRightFalse() {
        double x = 1.000000000000001, y = 0;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void rectangleBorderRightTrue() {
        double x = 0.5, y = 0;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void rectangleBorderDownTrue() {
        double x = 0, y = -1.0;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void rectangleBorderDownFalse() {
        double x = 0, y = -1.000000000000001;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void rectangleBorderAngleTrue() {
        double x = 0.5, y = -1.0;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void rectangleBorderAngleRightFalse() {
        double x = 0.5000000000000001, y = -1.0;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void rectangleBorderAngleDownFalse() {
        double x = 0.5, y = -1.000000000000001;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderUpTrue() {
        double x = 0.0, y = 0.5;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderUpFalse() {
        double x = 0.0, y = 0.5000000000000001;
        assertFalse(areaChecker.result(x, y, r));
    }
    @Test
    void circleBorderDownTrue() {
        double x = -0.1, y = 0.0;
        assertTrue(areaChecker.result(x, y, r));
    }
    @Test
    void circleBorderDownFalse() {
        double x = -0.1, y = -0.1;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderLeftTrue() {
        double x = -0.5, y = 0;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderLeftFalse() {
        double x = -0.5000000000000001, y = 0;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderAngleLeftTrue() {
        double x = -r / 2, y = 0;
        assertTrue(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderAngleUpFalse() {
        double x = -r / 2, y = r / 2 + 0.00000000001;
        assertFalse(areaChecker.result(x, y, r));
    }

    @Test
    void circleBorderAngleLeftFalse() {
        double x = -r / 2 + 0.00000000001, y = r / 2;
        assertFalse(areaChecker.result(x, y, r));
    }
}