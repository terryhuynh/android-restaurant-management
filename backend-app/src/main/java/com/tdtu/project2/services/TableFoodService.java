package com.tdtu.project2.services;

import java.util.List;
import com.tdtu.project2.models.TableFood;

public interface TableFoodService {

    List<TableFood> getAllTableFoods();
    
    void updateTableStatus(Long tableId, Long status);
}
