package lab6.crm.controller;

import lab6.crm.dto.OperatorDto;
import lab6.crm.service.OperatorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class OperatorsRestController {

    private final OperatorsService operatorsService;

    public OperatorsRestController(OperatorsService operatorsService) {
        this.operatorsService = operatorsService;
    }

    @GetMapping
    public List<OperatorDto> getAll() {
        return operatorsService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatorDto> getById(@PathVariable Long id) {
        OperatorDto dto = operatorsService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OperatorDto> create(@RequestBody OperatorDto dto) {
        OperatorDto created = operatorsService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperatorDto> update(@PathVariable Long id,
                                              @RequestBody OperatorDto dto) {
        OperatorDto updated = operatorsService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        operatorsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
