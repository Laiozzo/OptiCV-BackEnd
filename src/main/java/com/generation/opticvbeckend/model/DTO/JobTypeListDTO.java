package com.generation.opticvbeckend.model.dto;

import java.util.List;

public class JobTypeListDTO {
    private List<JobTypeDTO> jobTypeList;

    public List<JobTypeDTO> getJobTypeList() {
        return jobTypeList;
    }

    public void setJobTypeList(List<JobTypeDTO> jobTypeList) {
        this.jobTypeList = jobTypeList;
    }
}
