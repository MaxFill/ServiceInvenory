package ru.rt.som.si.dataaccesslayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rt.som.si.dataaccesslayer.model.RfsItem;
import ru.rt.som.si.dataaccesslayer.repository.RfsItemRepository;

@Service
public class RfsItemController {
    @Autowired
    private final RfsItemRepository rfsItemRepository;

    public RfsItemController(RfsItemRepository rfsItemRepository) {
        this.rfsItemRepository = rfsItemRepository;
    }

    public RfsItem getRfsById(String rfsId) {
        return rfsItemRepository.findRfsByRfsId(rfsId);
    }
}