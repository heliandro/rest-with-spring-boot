package br.com.heliandro.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.heliandro.enums.Operation;
import br.com.heliandro.exceptions.MathOperationException;
import br.com.heliandro.services.MathService;
import br.com.heliandro.utils.NumberConverter;

@Component
public class MathValidation {

    @Autowired
    private MathService mathService;

    public void validate(String operation, String numberOne, String numberTwo) throws Exception {
        validatePathVariableOperation(operation);
        validateIfPathVariablesIsNumeric(numberOne, numberTwo);
    }

    public void validateIfPathVariablesIsNumeric(String numberOne, String numberTwo)
            throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new MathOperationException("Please set a numeric value!");
    }

    public void validatePathVariableOperation(String operation) {
        Operation op = mathService.getOperationFromString(operation);
        if (op == null)
            throw new MathOperationException("Invalid operation: " + operation);
    }
}
