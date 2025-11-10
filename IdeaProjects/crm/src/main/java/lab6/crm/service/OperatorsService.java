package lab6.crm.service;

import lab6.crm.dto.OperatorDto;
import lab6.crm.entity.Operators;
import lab6.crm.repository.OperatorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorsService {

    private final OperatorsRepository repository;

    public OperatorsService(OperatorsRepository repository) {
        this.repository = repository;
    }

    public List<Operators> getAllEntities() {
        return repository.findAll();
    }

    public List<Operators> getAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void save(Operators operator) {
        repository.save(operator);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Operators getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<OperatorDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OperatorDto getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public OperatorDto create(OperatorDto dto) {
        Operators entity = toEntity(dto);
        entity.setId(null); // на всякий случай
        Operators saved = repository.save(entity);
        return toDto(saved);
    }

    public OperatorDto update(Long id, OperatorDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setSurname(dto.getSurname());
                    existing.setDepartment(dto.getDepartment());
                    Operators saved = repository.save(existing);
                    return toDto(saved);
                })
                .orElse(null);
    }

    public OperatorDto toDto(Operators entity) {
        if (entity == null) return null;
        OperatorDto dto = new OperatorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setDepartment(entity.getDepartment());
        return dto;
    }

    public Operators toEntity(OperatorDto dto) {
        if (dto == null) return null;
        Operators entity = new Operators();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setDepartment(dto.getDepartment());
        return entity;
    }
}
