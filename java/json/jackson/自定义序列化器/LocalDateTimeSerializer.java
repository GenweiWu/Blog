import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> implements ContextualSerializer {

    private DateTimeFormatter dateTimeFormatter;

    protected LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    protected LocalDateTimeSerializer(String pattern) {
        super(LocalDateTime.class);
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        JsonFormat jsonFormat = property.getAnnotation(JsonFormat.class);
        String pattern = jsonFormat.pattern();
        return new LocalDateTimeSerializer(pattern);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator gen, SerializerProvider provider) throws IOException {

        String format = localDateTime.format(dateTimeFormatter);
        gen.writeString(format);
    }
}
