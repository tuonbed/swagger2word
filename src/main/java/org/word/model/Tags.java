package org.word.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuonbed
 * @date 2019/10/23  14:01
 */
@Data
public class Tags {
    /**
     * tags
     */
    private String name;

    /**
     * 具体接口
     */
    private List<Table> listTable=new ArrayList<>();

    public Tags(String name){
        this.name=name;
    }
}
