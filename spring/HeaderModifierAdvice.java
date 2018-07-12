
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class HeaderModifierAdvice implements ResponseBodyAdvice<Object>
{
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType)
    {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response)
    {
        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest)request).getServletRequest();
        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse)response).getServletResponse();

        boolean toJson = selectedContentType.equals(MediaType.APPLICATION_JSON);

        if (toJson)
        {
            //httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = httpServletResponse.getWriter())
            {
                String content = new ObjectMapper().writeValueAsString(body);
                log.debug("start to handle toJson,source body is {}", content);
                writer.write(content);
                writer.flush();

                //已经使用过writer此处要返回null，否则会报writer多次使用的错
                return null;
            }
            catch (IOException e)
            {
                log.error("write toJson response failed!", e);
            }
        }

        return body;
    }

}
