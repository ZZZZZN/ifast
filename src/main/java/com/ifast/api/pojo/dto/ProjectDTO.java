package com.ifast.api.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <pre>
 *
 * </pre>
 * <small> 2019-05-05 11:40 | Aron</small>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO  {
    private String ID;
    private String projectname;
    private String projectaddress;
    private String projectType;
    private String buildNature;
    private String supervisionNum;
    private String buildareaUp;
    private String buildareaDown;
    private String projectLength;
    private String buildHeight;
    private String layerUp;
    private String layerDown;
    private String structureType;
    private String engineerCost;
    private String unplanTime;
    private String planTime;
    private String supervisionTime;
    private String supervisionNature;
    private String supervisionGroup;
    private String supervisioner;
    private String uncompleted;
    private String dangerEngineer;
    private String overweightNum;
    private String NAME;
    private String person;
    private String personnum;
    private String personphone;
    private String dw;
}
