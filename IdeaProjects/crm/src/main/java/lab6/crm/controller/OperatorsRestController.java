package lab6.crm.controller;

import lab6.crm.entity.ApplicationRequest;
import lab6.crm.entity.Operators;
import lab6.crm.service.ApplicationRequestService;
import lab6.crm.service.OperatorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class OperatorsRestController {

    private final OperatorsService operatorsService;
    private final ApplicationRequestService requestService;

    public OperatorsRestController(OperatorsService operatorsService,
                                   ApplicationRequestService requestService) {
        this.operatorsService = operatorsService;
        this.requestService = requestService;
    }

    @GetMapping
    public List<Operators> getAll() {
        return operatorsService.getAll();
    }

    @PostMapping
    public ResponseEntity<Operators> create(@RequestBody Operators operator) {
        operatorsService.save(operator);
        return ResponseEntity.ok(operator);
    }

    @PutMapping("/{id}/assign/{requestId}")
    public ResponseEntity<ApplicationRequest> assignToRequest(@PathVariable Long id,
                                                              @PathVariable Long requestId) {
        Operators operator = operatorsService.getById(id);
        if (operator == null) {
            return ResponseEntity.notFound().build();
        }

        ApplicationRequest request = requestService.getById(requestId);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        if (!request.getOperators().contains(operator)) {
            request.getOperators().add(operator);
        }

        if (!operator.getRequests().contains(request)) {
            operator.getRequests().add(request);
        }

        requestService.save(request);

        return ResponseEntity.ok(request);
    }
}
