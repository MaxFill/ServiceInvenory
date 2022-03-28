package ru.rt.som.si.dataaccesslayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rt.som.si.dataaccesslayer.model.RfsItem;

public interface RfsItemRepository extends JpaRepository<RfsItem, Long> {
    RfsItem findRfsByRfsId(String rfsId);
}