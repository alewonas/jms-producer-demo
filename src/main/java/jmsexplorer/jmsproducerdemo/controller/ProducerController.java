package jmsexplorer.jmsproducerdemo.controller;

import jmsexplorer.jmsproducerdemo.model.IntakeFormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller class responsible for handling HTTP requests related to intake forms and message processing.
 */
@RestController
@RequestMapping("/intake")
public class ProducerController {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * A test endpoint to check if the service is running and returns an HTTP OK status.
     *
     * @return An HTTP OK response.
     */
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles the submission of an intake form, validates the form data, and sends it to a JMS queue.
     *
     * @param intakeFormModel The intake form data to be submitted and processed.
     * @return An HTTP response indicating the success or failure of the submission.
     */
    @PostMapping("/submit")
    public ResponseEntity<String> submitIntakeForm(@Valid @RequestBody IntakeFormModel intakeFormModel) {
        try {
            String asJson = intakeFormModel.toString();
            jmsTemplate.convertAndSend("intake-queue", asJson);
            String responseMessage = "Received Intake Form: " + intakeFormModel.toString();
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles validation exceptions thrown during intake form submission and returns a list of validation errors.
     *
     * @param ex The MethodArgumentNotValidException containing validation errors.
     * @return A list of validation error messages.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return errors;
    }

}
