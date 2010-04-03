package com.amin.luceneindexsi.index.entity.recognition;

import java.io.Serializable;

/**
 * NamedEntity
 *
 * @author: Amin Mohammed-Coleman
 * @since: Apr 3, 2010
 */
public class NamedEntity implements Serializable {

    private Long id;

    private NamedEntityType type;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NamedEntityType getType() {
        return type;
    }

    public void setType(NamedEntityType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
