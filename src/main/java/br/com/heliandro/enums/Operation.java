package br.com.heliandro.enums;

import br.com.heliandro.exceptions.MathOperationException;
import br.com.heliandro.interfaces.MathOperation;

public enum Operation implements MathOperation {
    ADDITION {
        @Override
        public double calculate(double numberOne, double numberTwo) {
            return numberOne + numberTwo;
        }
    },
    SUBTRACTION {
        @Override
        public double calculate(double numberOne, double numberTwo) {
            return numberOne - numberTwo;
        }
    },
    MULTIPLICATION {
        @Override
        public double calculate(double numberOne, double numberTwo) {
            return numberOne * numberTwo;
        }
    },
    DIVISION {
        @Override
        public double calculate(double numberOne, double numberTwo) {
            if (numberTwo == 0)
                throw new MathOperationException("Division by zero is not allowed.");
            return numberOne / numberTwo;
        }
    },
    SQUARE_ROOT {
        @Override
        public double calculate(double numberOne, double numberTwo) {
            return Math.sqrt(numberOne);
        }
    }
}
