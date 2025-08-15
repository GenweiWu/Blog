
### java.time.temporal.Temporal#with(java.time.temporal.TemporalAdjuster)
```java
default Temporal with(TemporalAdjuster adjuster) {
    return adjuster.adjustInto(this);
}
```

### java.time.temporal.TemporalAccessor#query
```java
default <R> R query(TemporalQuery<R> query) {
        if (query == TemporalQueries.zoneId()
                || query == TemporalQueries.chronology()
                || query == TemporalQueries.precision()) {
            return null;
        }
        return query.queryFrom(this);
    }
```

### java.time.temporal.TemporalAccessor#range
```java
default ValueRange range(TemporalField field) 
```

### java.time.temporal.Temporal#until 
```java
long until(Temporal endExclusive, TemporalUnit unit);
```


<img width="1020" height="550" alt="image" src="https://github.com/user-attachments/assets/720a6c92-8bc4-4d93-b259-c6564d9fa2b4" />

