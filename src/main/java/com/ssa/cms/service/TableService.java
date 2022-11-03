package com.ssa.cms.service;

import com.ssa.cms.dao.TableDAO;
import com.ssa.cms.model.DTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TableService {

    @Autowired
    TableDAO tableDAO;

    public List<Object[]> getDataBaseTableList() {
        List<Object[]> list = new ArrayList<>();
        try {
           list.addAll(tableDAO.getTableList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
