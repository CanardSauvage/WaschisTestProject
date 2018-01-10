package de.waschnick;

import org.junit.Before;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class Java8roundings {

    private DecimalFormat decimalFormat;

    @Before
    public void beforeMethod() throws Exception {
        decimalFormat = buildFormatter();
    }

    private DecimalFormat buildFormatter() {
// The locale does not really matter for the bug!
        final DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
        formatter.setRoundingMode(RoundingMode.HALF_UP); // that's what we want to test
        formatter.applyPattern("0.0");
        return formatter;
    }

    @Test
    public void independentTest_testRounding() {
        final DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.GERMANY);
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.applyPattern("0.0");
        assertEquals("1,2", format.format(1.17d));
    }

    @Test
    public void independentTest_testRounding2() {
        final DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.applyPattern("0.0");
        assertEquals("1.2", format.format(1.17d));
    }

    @Test
    public void independentTest_testRounding3() {
        final DecimalFormat format = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.GERMANY));
        format.setRoundingMode(RoundingMode.HALF_UP);
        assertEquals("1.2", format.format(1.17d));
    }

    @Test
    public void testSpecificValue() {
        assertEquals("1,2", decimalFormat.format(1.17d));
    }

    @Test
    public void shouldRoundGivenValuesFromJavadocDividedByTen() {
        assertEquals("0,6", decimalFormat.format(0.55d));
        assertEquals("0,3", decimalFormat.format(0.25d));
        assertEquals("0,2", decimalFormat.format(0.16d));
        assertEquals("0,1", decimalFormat.format(0.11d));
        assertEquals("-0,1", decimalFormat.format(-0.11d));
        assertEquals("-0,3", decimalFormat.format(-0.25d));
        assertEquals("-0,6", decimalFormat.format(-0.55d));
    }

    @Test
    public void shouldRoundGivenValuesFromJavadocDividedByTenAddOne() {
        assertEquals("1,6", decimalFormat.format(1.55d));
        assertEquals("1,3", decimalFormat.format(1.25d));
        assertEquals("1,2", decimalFormat.format(1.16d));
        assertEquals("1,1", decimalFormat.format(1.11d));
        assertEquals("-1,1", decimalFormat.format(-1.11d));
        assertEquals("-1,3", decimalFormat.format(-1.25d));
        assertEquals("-1,6", decimalFormat.format(-1.55d));
    }

    @Test
    public void checkValuesAroundTheSpecificValue() {
        assertEquals("1,1", decimalFormat.format(1.14d));
        assertEquals("1,2", decimalFormat.format(1.15d));
        assertEquals("1,2", decimalFormat.format(1.16d));
        assertEquals("1,2", decimalFormat.format(1.18d));
        assertEquals("1,2", decimalFormat.format(1.19d));
        assertEquals("1,2", decimalFormat.format(1.20d));
    }

    @Test
    public void useHigherValues() {
        assertEquals("1,5", decimalFormat.format(1.49d));
        assertEquals("1,6", decimalFormat.format(1.55d));
    }
}