package ru.suchkov.parts.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.suchkov.parts.entities.Part;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends PagingAndSortingRepository<Part, Long> {

    List<Part> findAllByNecessary(boolean necessary);

    Page<Part> findAllByNecessary(boolean necessary, Pageable pageable);

    List<Part> findByNameContains(String lastname);

    /*  @Modifying
    void DeleteById(Long id);

    @Transactional
    @Modifying
    @Query("update Part s set s.name = ?1, s.age = ?2 where s.id = ?3")
    void updateStudentById(String name, int age, Long id);

*/
}
