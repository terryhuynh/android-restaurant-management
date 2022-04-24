package com.tdtu.project2.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tdtu.project2.models.TableFood;
import com.tdtu.project2.services.TableFoodService;

@RestController
@RequestMapping("api/v1/tables")
public class TableController {

    @Autowired
    private final TableFoodService service;
    
    @Autowired
    public TableController(TableFoodService tblService) {
        this.service = tblService;
    }
    
    @GetMapping("/all")
    public List<TableFood> getAllTables() {
        return this.service.getAllTableFoods();
    }
    
    @PutMapping("/update/status/{tableId}/{status}")
    public void updateTableStatus(@PathVariable Long tableId, @PathVariable Long status) {
        this.service.updateTableStatus(tableId, status);
    }
}
