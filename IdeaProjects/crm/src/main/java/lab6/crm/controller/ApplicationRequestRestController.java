package lab6.crm.controller;

import lab6.crm.entity.ApplicationRequest;
import lab6.crm.service.ApplicationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ApplicationRequestRestController {

    private final ApplicationRequestService requestService;

    public ApplicationRequestRestController(ApplicationRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<ApplicationRequest> getAll() {
        return requestService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationRequest> getById(@PathVariable Long id) {
        ApplicationRequest req = requestService.getById(id);
        if (req == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(req);
    }

    @PostMapping
    public ResponseEntity<ApplicationRequest> create(@RequestBody ApplicationRequest request) {
        requestService.save(request);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationRequest> update(@PathVariable Long id,
                                                     @RequestBody ApplicationRequest updated) {
        ApplicationRequest existing = requestService.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setUserName(updated.getUserName());
        existing.setCommentary(updated.getCommentary());
        existing.setPhone(updated.getPhone());
        existing.setHandled(updated.isHandled());
        existing.setCourse(updated.getCourse());
        existing.setOperators(updated.getOperators());

        requestService.save(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        requestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
