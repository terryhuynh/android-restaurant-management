package com.tdtu.project2.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdtu.project2.models.TableFood;
import com.tdtu.project2.repositories.TableFoodRepository;
import com.tdtu.project2.services.TableFoodService;

@Service
public class TableFoodServiceImpl implements TableFoodService {

    private final TableFoodRepository repository;

    @Autowired
    public TableFoodServiceImpl(TableFoodRepository repo) {
        this.repository = repo;
    }

    @Override
    public List<TableFood> getAllTableFoods() {
        return this.repository.findAll();
    }

    @Override
    public void updateTableStatus(Long tableId, Long status) {
        TableFood tableFood = this.repository.getOne(tableId);

        tableFood.setStatus(status);
        this.repository.save(tableFood);
    }

}
