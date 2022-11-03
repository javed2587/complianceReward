package com.ssa.cms.model;

import org.hibernate.cfg.ImprovedNamingStrategy;

class CustomNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String columnName(String columnName) {
        return columnName;
    }

    @Override
    public String tableName(String tableName) {
        return tableName;
    }
}
