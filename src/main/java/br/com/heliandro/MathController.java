package br.com.heliandro;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.heliandro.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

    @RequestMapping(value = "/{operation}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double calculator(
        @PathVariable(value = "operation") String operation,
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        validatePathVariableOperation(operation);
        validateIfPathVariablesIsNumeric(numberOne, numberTwo);
        return calculate(operation, numberOne, numberTwo);
    }

    private void validateIfPathVariablesIsNumeric(String numberOne, String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");
    }

    private void validatePathVariableOperation(String operation) {
        Operation op = getOperationFromString(operation);
        if (op == null)
            throw new UnsupportedMathOperationException("Invalid operation: " + operation);
    }

    private Operation getOperationFromString(String operation) {
        try {
            return Operation.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private double calculate(String operation, String numberOne, String numberTwo) {
        Operation op = getOperationFromString(operation);
        return op.calculate(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        
        String number = strNumber.replaceAll(",", ".");
        
        if (isNumeric(number))
            return Double.parseDouble(number);

        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;

        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
