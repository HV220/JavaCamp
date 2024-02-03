package edu.school21.numbers;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5})
    void isPrimeForPrimes(int number) {
        assertTrue(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8})
    void isPrimeForNotPrimes(int number) {
        assertFalse(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1})
    void isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> {
            new NumberWorker().isPrime(number);
        });

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void isPrimeForIncorrectNumbers(int number, int sum) {
        assertEquals(sum, new NumberWorker().digitsSum(number));
    }


}