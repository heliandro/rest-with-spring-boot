package br.com.heliandro.services;

import org.springframework.stereotype.Service;
import br.com.heliandro.enums.Operation;
import br.com.heliandro.exceptions.MathOperationException;
import br.com.heliandro.utils.NumberConverter;

@Service
public class MathService {

    public Operation getOperationFromString(String operation) {
        try {
            return Operation.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MathOperationException("Invalid operation: " + operation);
        }
    }

    public double calculate(String operation, String numberOne, String numberTwo) throws MathOperationException {
        Operation op = getOperationFromString(operation);
        return op.calculate(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }
}
