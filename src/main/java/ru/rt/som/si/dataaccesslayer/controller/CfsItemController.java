package ru.rt.som.si.dataaccesslayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rt.som.si.dataaccesslayer.model.CfsItem;
import ru.rt.som.si.dataaccesslayer.repository.CfsItemRepository;

@Service
public class CfsItemController {
    @Autowired
    private final CfsItemRepository cfsItemRepository;

    public CfsItemController(CfsItemRepository cfsItemRepository) {
        this.cfsItemRepository = cfsItemRepository;
    }

    public CfsItem getCfsById(String cfsId) {
        return cfsItemRepository.findCfsByCfsId(cfsId);
    }
}