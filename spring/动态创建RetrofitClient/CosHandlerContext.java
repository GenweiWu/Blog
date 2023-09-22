import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.env.Environment;

@ToString
@Setter
@Getter
public class CosHandlerContext {

    private Class<?> targetInterface;
    private Environment environment;
}
