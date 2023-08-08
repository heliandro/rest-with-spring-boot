package br.com.heliandro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import br.com.heliandro.MainApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MainApplication.class)
@AutoConfigureMockMvc
public class MathControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void integration_calculator_Addition_ValidInput_ReturnsCorrectResult() throws Exception {
        // Arrange
        String operation = "addition";
        String numberOne = "5";
        String numberTwo = "10";
        double expectedResult = 15.0;

        // Act & Assert
        mockMvc.perform(get("/calculator/{operation}/{numberOne}/{numberTwo}", operation, numberOne, numberTwo))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(expectedResult));
    }
}
