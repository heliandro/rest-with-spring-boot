package br.com.heliandro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.heliandro.dto.MathDto;
import br.com.heliandro.services.MathService;
import br.com.heliandro.validations.MathValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/calculator")
public class MathController {

    @Autowired
    private MathService mathService;

    @Autowired
    private MathValidation mathValidation;
 
    @Operation(summary = "Realiza calculos matematicos")
    @GetMapping(value = "/{operation}/{numberOne}/{numberTwo}")
    public MathDto calculator(
        @Parameter(
            description = "Operação a ser realizada", in = ParameterIn.PATH, required = true,
            schema = @Schema(allowableValues = {"addition", "subtraction", "multiplication", "division", "square_root"}))
        @PathVariable(value = "operation") String operation,
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {
        this.mathValidation.validate(operation, numberOne, numberTwo);
        double result = this.mathService.calculate(operation, numberOne, numberTwo);
        return new MathDto(result);
    }
}
