package br.com.heliandro;

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
