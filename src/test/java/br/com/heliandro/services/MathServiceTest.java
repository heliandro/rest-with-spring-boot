package br.com.heliandro.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.heliandro.exceptions.MathOperationException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MathServiceTest {
    
    private MathService mathService;

    @BeforeEach
    public void setUp() {
        mathService = new MathService();
    }

    // Cenarios Negativos
    @Test
    public void calculate_InvalidOperation_ThrowsException() {
        // Arrange
        String operation = "invalid_operation";
        String numberOne = "10";
        String numberTwo = "5";

        // Act & Assert
        MathOperationException exception = assertThrows(MathOperationException.class, () -> mathService.calculate(operation, numberOne, numberTwo));
        
        assertThat(exception.getMessage()).isEqualTo("Invalid operation: " + operation);
    }

    @Test
    public void calculate_InvalidNumbericInput_ThrowsException() {
        // Arrange
        String operation = "addition";
        String numberOne = "10";
        String numberTwo = "abc";

        // Act & Assert
        MathOperationException exception = assertThrows(MathOperationException.class, () -> mathService.calculate(operation, numberOne, numberTwo));

        assertThat(exception.getMessage()).isEqualTo("Invalid numeric value: " + numberTwo);
    }

    @Test
    public void calculate_Division_DivideByZero_ThrowsException() {
        // Arrange
        String operation = "division";
        String numberOne = "10";
        String numberTwo = "0";

        // Act & Assert
        assertThatExceptionOfType(MathOperationException.class)
                .isThrownBy(() -> mathService.calculate(operation, numberOne, numberTwo));
    }

    // Cenarios Positivos
    @Test
    public void calculate_Addition_ValidInput_ReturnsCorrectResult() {
        // Arrange
        String operation = "addition";
        String numberOne = "5";
        String numberTwo = "10";

        // Act
        double result = mathService.calculate(operation, numberOne, numberTwo);

        // Assert
        assertThat(result).isEqualTo(15.0);
    }

    @Test
    public void calculate_Subtraction_ValidInput_ReturnsCorrectResult() {
        // Arrange
        String operation = "subtraction";
        String numberOne = "50";
        String numberTwo = "25";

        // Act
        double result = mathService.calculate(operation, numberOne, numberTwo);

        // Assert
        assertThat(result).isEqualTo(25.0);
    }

    @Test
    public void calculate_Multiplication_ValidInput_ReturnsCorrectResult() {
        // Arrange
        String operation = "multiplication";
        String numberOne = "10";
        String numberTwo = "10";

        // Act
        double result = mathService.calculate(operation, numberOne, numberTwo);

        // Assert
        assertThat(result).isEqualTo(100.0);
    }

    @Test
    public void calculate_Division_ValidInput_ReturnsCorrectResult() {
        // Arrange
        String operation = "division";
        String numberOne = "100";
        String numberTwo = "2";

        // Act
        double result = mathService.calculate(operation, numberOne, numberTwo);

        // Assert
        assertThat(result).isEqualTo(50.0);
    }

    @Test
    public void calculate_SquareRoot_ValidInput_ReturnsCorrectResult() {
        // Arrange
        String operation = "square_root";
        String numberOne = "25";
        String numberTwo = "0";

        // Act
        double result = mathService.calculate(operation, numberOne, numberTwo);

        // Assert
        assertThat(result).isEqualTo(5.0);
    }
}
