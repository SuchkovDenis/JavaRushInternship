package ru.suchkov.parts.servises;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.suchkov.parts.entities.Part;
import ru.suchkov.parts.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PartService {

    private PartRepository partRepository;

    @Autowired
    public void setPartRepository(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Part get(Long id) {
        return partRepository.findById(id).get();
    }

    public Page<Part> getAllPartsByPage(int pageNumber, int pageSize) {
        return partRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id"));
    }

    public Page<Part> getAllPartsByPage(boolean neseccary, int pageNumber, int pageSize) {
        return partRepository.findAllByNecessary(neseccary, PageRequest.of(pageNumber, pageSize));
    }

    public List<Part> getAllByName(String name) {
        return  partRepository.findByNameContains(name);
    }

    public void delete(Long id) {
        partRepository.deleteById(id);
    }

    public void add(Part part) {
        partRepository.save(part);
    }

    public void update(Part part) {
        partRepository.save(part);
    }

    public int getAmountOfComputers() {
        List<Part> necessaryParts =  partRepository.findAllByNecessary(true);
        if (necessaryParts == null || necessaryParts.size() == 0)
            return 0;
        return Collections.min(necessaryParts, Comparator.comparingInt(Part::getAmount)).getAmount();
    }
}
