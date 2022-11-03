package com.ssa.cms.dto;

/**
 *
 * @author mzubair
 */
public class TabletDTO extends DrugTypeDTO {

    private Integer drugId;
    private Long    drugNDC;
    private String strength;
    

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Long getDrugNDC() {
        return drugNDC;
    }

    public void setDrugNDC(Long drugNDC) {
        this.drugNDC = drugNDC;
    }

       
    
    
    
}
