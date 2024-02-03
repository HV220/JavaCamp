package edu.school21.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrmManager {

    public OrmManager() {
    }

    public void save(Object entity) {
        Class<?> clazz = entity.getClass();
        if (!clazz.isAnnotationPresent(OrmEntity.class)) {
            throw new RuntimeException("The class is not an ORM entity.");
        }
        
        String tableName = clazz.getAnnotation(OrmEntity.class).table();
        String columns = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(OrmColumn.class) || f.isAnnotationPresent(OrmColumnId.class))
                .map(f -> {
                    String columnName = f.isAnnotationPresent(OrmColumn.class) 
                                        ? f.getAnnotation(OrmColumn.class).name() 
                                        : "id";
                    return columnName;
                })
                .collect(Collectors.joining(", "));

        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (...)";
        System.out.println(sql);
    }

public void update(Object entity) {
    Class<?> clazz = entity.getClass();
    if (!clazz.isAnnotationPresent(OrmEntity.class)) {
        throw new RuntimeException("The class is not an ORM entity.");
    }

    String tableName = clazz.getAnnotation(OrmEntity.class).table();
    List<String> columnValuePairs = new ArrayList<>();
    String idColumn = "";
    Object idValue = null;

    for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        try {
            boolean isId = field.isAnnotationPresent(OrmColumnId.class);
            boolean isColumn = field.isAnnotationPresent(OrmColumn.class);
            if (isId || isColumn) {
                String columnName = isColumn ? field.getAnnotation(OrmColumn.class).name() : "id";
                Object value = field.get(entity);
                String valueStr = value instanceof String ? "'" + value.toString().replace("'", "''") + "'" : value.toString();
                if (isId) {
                    idColumn = columnName;
                    idValue = valueStr;
                } else {
                    columnValuePairs.add(columnName + " = " + valueStr);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing the field values", e);
        }
    }

    if (idColumn.isEmpty() || idValue == null) {
        throw new RuntimeException("No ID column found or ID value is null for the update operation");
    }

    String sql = "UPDATE " + tableName + " SET " + String.join(", ", columnValuePairs) + " WHERE " + idColumn + " = " + idValue;
    System.out.println(sql);
}


    public <T> T findById(Long id, Class<T> clazz) {
        if (!clazz.isAnnotationPresent(OrmEntity.class)) {
            throw new RuntimeException("The class is not an ORM entity.");
        }
        
        String tableName = clazz.getAnnotation(OrmEntity.class).table();
        String sql = "SELECT * FROM " + tableName + " WHERE id = " + id;
        System.out.println(sql);
        
        return null;
    }
}
