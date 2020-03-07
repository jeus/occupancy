/*
 * @author jeus (suje@protonmail.com)
 * @since 2/28/20
 */
package com.hosp.occupancy.common.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;


public class NumberUtils {

    public static final int ZERO_INT = 0;
    public static final int ONE_INT = 1;
    public static final int TEN_INT = 10;
    public static final int ONE_HUNDRED_INT = 100;

    public static final Integer ZERO_INTEGER = 0;
    public static final Integer ONE_INTEGER = 1;
    public static final Integer TEN_INTEGER = 10;
    public static final Integer ONE_HUNDRED_INTEGER = 100;

    public static final Float ZERO_FLOAT = 0F;
    public static final Float ONE_FLOAT = 1F;
    public static final Float TEN_FLOAT = 10F;
    public static final Float ONE_HUNDRED_FLOAT = 100F;

    public static final Long ZERO_LONG = 0L;
    public static final Long ONE_LONG = 1L;
    public static final Long TWO_LONG = 2L;
    public static final Long TEN_LONG = 10L;
    public static final Long ONE_HUNDRED_LONG = 100L;

    public static final Double ZERO_DOUBLE = 0D;
    public static final Double ONE_DOUBLE = 1D;
    public static final Double TEN_DOUBLE = 10D;
    public static final Double ONE_HUNDRED_DOUBLE = 100D;

    public static final BigDecimal ZERO_BIG_DECIMAL = BigDecimal.ZERO;
    public static final BigDecimal ONE_BIG_DECIMAL = BigDecimal.ONE;
    public static final BigDecimal TWO_BIG_DECIMAL = new BigDecimal("2");
    public static final BigDecimal TEN_BIG_DECIMAL = BigDecimal.TEN;
    public static final BigDecimal ONE_HUNDRED_BIG_DECIMAL = BigDecimal.valueOf(100);

    private static final int CALCULATION_PRECISION = 25;

    private NumberUtils() {
    }

    public static <T extends Comparable<? super T>> boolean isEqual(T first, T second) {
        return first.compareTo(second) == 0;
    }

    public static <T> boolean isEqual(T first, T second, Comparator<T> comparator) {
        return comparator.compare(first, second) == 0;
    }

    public static <T extends Comparable<? super T>> boolean isNotEqual(T first, T second) {
        return first.compareTo(second) != 0;
    }

    public static <T extends Comparable<? super T>> boolean isLess(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public static <T> boolean isLess(T first, T second, Comparator<T> comparator) {
        return comparator.compare(first, second) < 0;
    }

    public static <T extends Comparable<? super T>> boolean isLessOrEqual(T first, T second) {
        return first.compareTo(second) <= 0;
    }

    public static <T> boolean isLessOrEqual(T first, T second, Comparator<T> comparator) {
        return comparator.compare(first, second) <= 0;
    }

    public static <T extends Comparable<? super T>> boolean isGreater(T first, T second) {
        return first.compareTo(second) > 0;
    }

    public static <T> boolean isGreater(T first, T second, Comparator<T> comparator) {
        return comparator.compare(first, second) > 0;
    }

    public static <T extends Comparable<? super T>> boolean isGreaterOrEqual(T first, T second) {
        return first.compareTo(second) >= 0;
    }

    public static <T> boolean isGreaterOrEqual(T first, T second, Comparator<T> comparator) {
        return comparator.compare(first, second) >= 0;
    }

    public static <T extends Comparable<? super T>> boolean isInRange(T value, T inclusiveStart, T exclusiveEnd) {
        return isGreaterOrEqual(value, inclusiveStart) && isLess(value, exclusiveEnd);
    }

    public static <T> boolean isInRange(T value, T inclusiveStart, T exclusiveEnd, Comparator<T> comparator) {
        return isGreaterOrEqual(value, inclusiveStart, comparator) && isLess(value, exclusiveEnd, comparator);
    }

    public static <T extends Comparable<? super T>> boolean isNotInRange(T value, T inclusiveStart, T exclusiveEnd) {
        return !isInRange(value, inclusiveStart, exclusiveEnd);
    }

    public static <T> boolean isNotInRange(T value, T inclusiveStart, T exclusiveEnd, Comparator<T> comparator) {
        return !isInRange(value, inclusiveStart, exclusiveEnd, comparator);
    }

    public static <T extends Comparable<? super T>> T min(T first, T second) {
        return isLessOrEqual(first, second) ? first : second;
    }

    public static <T> T min(T first, T second, Comparator<T> comparator) {
        return isLessOrEqual(first, second, comparator) ? first : second;
    }

    public static <T extends Comparable<? super T>> T max(T first, T second) {
        return isGreaterOrEqual(first, second) ? first : second;
    }

    public static <T> T max(T first, T second, Comparator<T> comparator) {
        return isGreaterOrEqual(first, second, comparator) ? first : second;
    }

    public static int length(Long number) {
        assert (number != null) : "number can not be null!";
        return number.toString().length();
    }

    public static long trailingLeftDigit(Long number, int trailCount) {
        assert (trailCount != ZERO_INTEGER) : "trailCount can not be zero!";

        final int quotientLength = length(number) - trailCount;
        if (quotientLength <= 0) return number;

        return number / ((long) Math.pow(TEN_INTEGER, quotientLength));
    }

    public static long trailingRightDigit(Long number, int trailCount) {
        assert (trailCount != ZERO_INTEGER) : "trailCount can not be zero!";
        return number % ((long) Math.pow(TEN_INTEGER, trailCount));
    }

    public static Integer add(Integer firstAddend, Integer secondAddend) {
        return firstAddend + secondAddend;
    }

    public static Float add(Float firstAddend, Float secondAddend) {
        final Float result = firstAddend + secondAddend;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Long add(Long firstAddend, Long secondAddend) {
        return firstAddend + secondAddend;
    }

    public static Double add(Double firstAddend, Double secondAddend) {
        final Double result = firstAddend + secondAddend;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static BigDecimal add(BigDecimal firstAddend, BigDecimal secondAddend) {
        final BigDecimal result = firstAddend.add(secondAddend);
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Integer subtract(Integer minuend, Integer subtrahend) {
        return minuend - subtrahend;
    }

    public static Float subtract(Float minuend, Float subtrahend) {
        final Float result = minuend - subtrahend;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Long subtract(Long minuend, Long subtrahend) {
        return minuend - subtrahend;
    }

    public static Double subtract(Double minuend, Double subtrahend) {
        final Double result = minuend - subtrahend;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        final BigDecimal result = minuend.subtract(subtrahend);
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Integer multiply(Integer multiplier, Integer multiplicand) {
        return multiplier * multiplicand;
    }

    public static Float multiply(Float multiplier, Float multiplicand) {
        final Float result = multiplier * multiplicand;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Long multiply(Long multiplier, Long multiplicand) {
        return multiplier * multiplicand;
    }

    public static Double multiply(Double multiplier, Double multiplicand) {
        final Double result = multiplier * multiplicand;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static BigDecimal multiply(BigDecimal multiplier, BigDecimal multiplicand) {
        final BigDecimal result = multiplier.multiply(multiplicand);
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Float divide(Integer dividend, Integer divisor) {
        final float result = dividend.floatValue() / divisor;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Float divide(Float dividend, Float divisor) {
        final Float result = dividend / divisor;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Double divide(Long dividend, Long divisor) {
        final double result = dividend.floatValue() / divisor;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static Double divide(Double dividend, Double divisor) {
        final Double result = dividend / divisor;
        return setScale(result, CALCULATION_PRECISION, RoundingMode.DOWN);
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return dividend.divide(divisor, CALCULATION_PRECISION, RoundingMode.DOWN).stripTrailingZeros();
    }

    public static Integer remainder(Integer dividend, Integer divisor) {
        return dividend % divisor;
    }

    public static Float remainder(Float dividend, Float divisor) {
        return dividend % divisor;
    }

    public static Long remainder(Long dividend, Long divisor) {
        return dividend % divisor;
    }

    public static Double remainder(Double dividend, Double divisor) {
        return dividend % divisor;
    }

    public static BigDecimal remainder(BigDecimal dividend, BigDecimal divisor) {
        return dividend.remainder(divisor, MathContext.UNLIMITED);
    }

    public static Float setScale(Float value, int newScale, RoundingMode roundingMode) {
        return setScale(BigDecimal.valueOf(value), newScale, roundingMode).stripTrailingZeros().floatValue();
    }

    public static Double setScale(Double value, int newScale, RoundingMode roundingMode) {
        return setScale(BigDecimal.valueOf(value), newScale, roundingMode).stripTrailingZeros().doubleValue();
    }

    public static BigDecimal setScale(BigDecimal value, int newScale, RoundingMode roundingMode) {
        return value.setScale(newScale, roundingMode).stripTrailingZeros();
    }

    public static Integer negate(Integer value) {
        return -value;
    }

    public static Float negate(Float value) {
        return -value;
    }

    public static Long negate(Long value) {
        return -value;
    }

    public static Double negate(Double value) {
        return -value;
    }

    public static BigDecimal negate(BigDecimal value) {
        return value.negate();
    }

    /**
     * @param value input parameter
     * @return True if value is null or is zero.
     */
    public static boolean isNotInitialized(Long value) {
        return (value == null) || isZero(value);
    }

    /**
     * @param value input parameter
     * @return True if value is null or is zero.
     */
    public static boolean isNotInitialized(Integer value) {
        return (value == null) || isZero(value);
    }

    /**
     * @param value input parameter
     * @return True if value is null or is zero.
     */
    public static boolean isNotInitialized(Double value) {
        return (value == null) || isZero(value);
    }

    /**
     * @param value input parameter
     * @return True if value is null or is zero.
     */
    public static boolean isNotInitialized(Float value) {
        return (value == null) || isZero(value);
    }

    /**
     * @param value input parameter
     * @return True if value is null or is zero.
     */
    public static boolean isNotInitialized(BigDecimal value) {
        return (value == null) || isZero(value);
    }

    /**
     * @param value input parameter
     * @return True if value is null or is empty.
     */
    public static boolean isNotInitialized(String value) {
        return (value == null) || value.trim().isEmpty();
    }

    /**
     * @param value input parameter
     * @return True if value is not null and is not zero.
     */
    public static boolean isInitialized(Long value) {
        return !isNotInitialized(value);
    }

    /**
     * @param value input parameter
     * @return True if value is not null and is not zero.
     */
    public static boolean isInitialized(Integer value) {
        return !isNotInitialized(value);
    }

    /**
     * @param value input parameter
     * @return True if value is not null and is not zero.
     */
    public static boolean isInitialized(Double value) {
        return !isNotInitialized(value);
    }

    /**
     * @param value input parameter
     * @return True if value is not null and is not zero.
     */
    public static boolean isInitialized(Float value) {
        return !isNotInitialized(value);
    }

    /**
     * @param value input parameter
     * @return True if value is not null and is not zero.
     */
    public static boolean isInitialized(BigDecimal value) {
        return !isNotInitialized(value);
    }

    /**
     * @param value input parameter
     * @return True if value is not null and is not empty.
     */
    public static boolean isInitialized(String value) {
        return !isNotInitialized(value);
    }

    /**
     * Check if input parameter is zero using {@link Integer#compareTo(Integer)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see Integer#compareTo(Integer)
     */
    public static boolean isZero(Integer value) {
        return value.compareTo(ZERO_INTEGER) == 0;
    }

    /**
     * Check if input parameter is zero using {@link Float#compareTo(Float)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see Float#compareTo(Float)
     */
    public static boolean isZero(Float value) {
        return value.compareTo(ZERO_FLOAT) == 0;
    }

    /**
     * Check if input parameter is zero using {@link Long#compareTo(Long)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see Long#compareTo(Long)
     */
    public static boolean isZero(Long value) {
        return value.compareTo(ZERO_LONG) == 0;
    }

    /**
     * Check if input parameter is zero using {@link Double#compareTo(Double)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see Double#compareTo(Double)
     */
    public static boolean isZero(Double value) {
        return value.compareTo(ZERO_DOUBLE) == 0;
    }

    /**
     * Check if input parameter is zero using {@link BigDecimal#compareTo(BigDecimal)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see BigDecimal#compareTo(BigDecimal)
     */
    public static boolean isZero(BigDecimal value) {
        return value.compareTo(ZERO_BIG_DECIMAL) == 0;
    }

    /**
     * Check if input parameter is <b>not zero</b> using {@link Integer#compareTo(Integer)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is <b>not zero</b>, otherwise <code>false</code>
     * @see Integer#compareTo(Integer)
     */
    public static boolean isNotZero(Integer value) {
        return value.compareTo(ZERO_INTEGER) != 0;
    }

    /**
     * Check if input parameter is zero using {@link Float#compareTo(Float)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see Float#compareTo(Float)
     */
    public static boolean isNotZero(Float value) {
        return value.compareTo(ZERO_FLOAT) != 0;
    }

    /**
     * Check if input parameter is zero using {@link Long#compareTo(Long)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is zero, otherwise <code>false</code>
     * @see Long#compareTo(Long)
     */
    public static boolean isNotZero(Long value) {
        return value.compareTo(ZERO_LONG) != 0;
    }

    /**
     * Check if input parameter is <b>not zero</b> using {@link Double#compareTo(Double)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is <b>not zero</b>, otherwise <code>false</code>
     * @see Double#compareTo(Double)
     */
    public static boolean isNotZero(Double value) {
        return value.compareTo(ZERO_DOUBLE) != 0;
    }

    /**
     * Check if input parameter is <b>not zero</b> using {@link BigDecimal#compareTo(BigDecimal)}
     *
     * @param value input parameter
     * @return <code>true</code> if input parameter is <b>not zero</b>, otherwise <code>false</code>
     * @see BigDecimal#compareTo(BigDecimal)
     */
    public static boolean isNotZero(BigDecimal value) {
        return value.compareTo(ZERO_BIG_DECIMAL) != 0;
    }

    public static boolean isLessThanZero(Integer value) {
        return value.compareTo(ZERO_INTEGER) < 0;
    }

    public static boolean isLessThanZero(Float value) {
        return value.compareTo(ZERO_FLOAT) < 0;
    }

    public static boolean isLessThanZero(Long value) {
        return value.compareTo(ZERO_LONG) < 0;
    }

    public static boolean isLessThanZero(Double value) {
        return value.compareTo(ZERO_DOUBLE) < 0;
    }

    public static boolean isLessThanZero(BigDecimal value) {
        return value.compareTo(ZERO_BIG_DECIMAL) < 0;
    }

    public static boolean isLessOrEqualToZero(Integer value) {
        return value.compareTo(ZERO_INTEGER) <= 0;
    }

    public static boolean isLessOrEqualToZero(Float value) {
        return value.compareTo(ZERO_FLOAT) <= 0;
    }

    public static boolean isLessOrEqualToZero(Long value) {
        return value.compareTo(ZERO_LONG) <= 0;
    }

    public static boolean isLessOrEqualToZero(Double value) {
        return value.compareTo(ZERO_DOUBLE) <= 0;
    }

    public static boolean isLessOrEqualToZero(BigDecimal value) {
        return value.compareTo(ZERO_BIG_DECIMAL) <= 0;
    }

    public static boolean isGreaterThanZero(Integer value) {
        return value.compareTo(ZERO_INTEGER) > 0;
    }

    public static boolean isGreaterThanZero(Float value) {
        return value.compareTo(ZERO_FLOAT) > 0;
    }

    public static boolean isGreaterThanZero(Long value) {
        return value.compareTo(ZERO_LONG) > 0;
    }

    public static boolean isGreaterThanZero(Double value) {
        return value.compareTo(ZERO_DOUBLE) > 0;
    }

    public static boolean isGreaterThanZero(BigDecimal value) {
        return value.compareTo(ZERO_BIG_DECIMAL) > 0;
    }

    public static boolean isGreaterOrEqualToZero(Integer value) {
        return value.compareTo(ZERO_INTEGER) >= 0;
    }

    public static boolean isGreaterOrEqualToZero(Float value) {
        return value.compareTo(ZERO_FLOAT) >= 0;
    }

    public static boolean isGreaterOrEqualToZero(Long value) {
        return value.compareTo(ZERO_LONG) >= 0;
    }

    public static boolean isGreaterOrEqualToZero(Double value) {
        return value.compareTo(ZERO_DOUBLE) >= 0;
    }

    public static boolean isGreaterOrEqualToZero(BigDecimal value) {
        return value.compareTo(ZERO_BIG_DECIMAL) >= 0;
    }
}
