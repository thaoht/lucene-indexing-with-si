package com.amin.luceneindexsi.index.entity.recognition;

/**
 * NamedEntityType
 *
 * @author: Amin Mohammed-Coleman
 * @since: Apr 3, 2010
 */
public enum NamedEntityType {

   PERSON ("Person"), ORGANIZATION ("Organization"), DATE("Date"), MONEY ("Money"), LOCATION("Location"), ADDRESS("Address"), URL_ADDRESS("UrlAddress");

    private final String description;

    private NamedEntityType(String description) {
        this.description = description;
    }


    public String description() {
        return this.description;
    }

    public NamedEntityType findBy(String description) {
        for(NamedEntityType type: values() ) {
            if (type.description().equals(description)) {
                return type;
            }
        }
        throw new RuntimeException("Could not find NamedEntityType for " + description);
    }
}
