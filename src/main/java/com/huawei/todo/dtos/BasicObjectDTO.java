package com.huawei.todo.dtos;

import com.huawei.todo.models.BasicObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BasicObjectDTO implements Serializable {

    Long id;

    Boolean enabled = false;

    public BasicObjectDTO(BasicObject basicObject) {

        this.id = basicObject.getId();
        this.enabled = basicObject.isEnabled();
    }
}
