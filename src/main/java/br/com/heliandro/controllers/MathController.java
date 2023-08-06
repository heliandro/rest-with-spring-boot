package br.com.heliandro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.heliandro.dto.MathDto;
import br.com.heliandro.services.MathService;
import br.com.heliandro.validations.MathValidation;

@RestController
public class MathController {

    @Autowired
    private MathService mathService;

    @Autowired
    private MathValidation mathValidation;

    @RequestMapping(value = "/{operation}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public MathDto calculator(@PathVariable(value = "operation") String operation,
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        this.mathValidation.validate(operation, numberOne, numberTwo);
        double result = this.mathService.calculate(operation, numberOne, numberTwo);
        return new MathDto(result);
    }
}
