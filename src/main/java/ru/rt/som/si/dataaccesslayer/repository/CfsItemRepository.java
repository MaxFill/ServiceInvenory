package ru.rt.som.si.dataaccesslayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rt.som.si.dataaccesslayer.model.CfsItem;

public interface CfsItemRepository extends JpaRepository<CfsItem, Long> {
    CfsItem findCfsByCfsId(String cfsId);
}