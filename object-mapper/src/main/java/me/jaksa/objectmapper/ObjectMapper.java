package me.jaksa.objectmapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ObjectMapper {
    @FunctionalInterface
    private interface FieldReader {
        Object read(ResultSet rs, String columnName) throws SQLException;
    }

    // TODO mapping TIMESTAMP to String etc.
    private static final HashMap<Class, FieldReader> FIELD_READERS = new HashMap<>() {{
        put(String.class, ResultSet::getString);
        put(boolean.class, ResultSet::getBoolean);
        put(Boolean.class, ResultSet::getBoolean);
        put(byte.class, ResultSet::getByte);
        put(Byte.class, ResultSet::getByte);
        put(short.class, ResultSet::getShort);
        put(Short.class, ResultSet::getShort);
        put(int.class, ResultSet::getInt);
        put(Integer.class, ResultSet::getInt);
        put(long.class, ResultSet::getLong);
        put(Long.class, ResultSet::getLong);
        put(float.class, ResultSet::getFloat);
        put(Float.class, ResultSet::getFloat);
        put(double.class, ResultSet::getDouble);
        put(Double.class, ResultSet::getDouble);
        put(BigDecimal.class, ResultSet::getBigDecimal);
        put(byte[].class, ResultSet::getBytes);
        put(Byte[].class, ResultSet::getBytes);
        put(Date.class, ResultSet::getDate);
        put(java.util.Date.class, (rs, col) -> new java.util.Date(rs.getDate(col).getTime()));
        put(LocalDate.class, (rs, col) -> rs.getDate(col).toLocalDate());
        put(Time.class, ResultSet::getTime);
        put(Timestamp.class, ResultSet::getTimestamp);
        put(LocalDateTime.class, (rs, col) -> rs.getTimestamp(col).toLocalDateTime());
        put(Instant.class, (rs, col) -> rs.getTimestamp(col).toInstant());
        put(Blob.class, ResultSet::getBlob);
        put(Clob.class, ResultSet::getClob);
        put(NClob.class, ResultSet::getNClob);
        put(SQLXML.class, ResultSet::getSQLXML);
        put(URL.class, ResultSet::getURL);
        put(Object.class, ResultSet::getObject);
    }};

    private final Connection conn;

    public ObjectMapper(Connection conn) {
        this.conn = conn;
    }

    public <T> T readOne(ResultSet rs, Class<T> clazz) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!rs.first()) return null;
        T t = clazz.getDeclaredConstructor().newInstance();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            FieldReader fieldReader = FIELD_READERS.get(f.getType());
            if (fieldReader != null) {
                f.set(t, fieldReader.read(rs, getColumnName(rs, f)));
            }
        }
        return t;
    }

    private String getColumnName(ResultSet rs, Field f) {
        // TODO read @Column annotation
        // TODO handle CamelCase and UNDER_SCORE
        return f.getName();
    }
}
