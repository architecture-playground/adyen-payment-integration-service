@TypeDefs({
        @TypeDef(name = "pgsql-enum", typeClass = PostgreSQLEnumType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
package com.playground.adyen.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
