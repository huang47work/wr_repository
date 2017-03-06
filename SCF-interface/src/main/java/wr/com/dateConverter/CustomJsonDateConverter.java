package wr.com.dateConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * 解决Date类型返回json格式为自定义格式
 * 
 * @author 郭杰
 * @since Dec 21,2016
 * @version 1.0.1
 *
 */
public class CustomJsonDateConverter extends ObjectMapper {
	public CustomJsonDateConverter() {
		CustomSerializerFactory factory = new CustomSerializerFactory();
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider)
					throws IOException {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				jsonGenerator.writeString(sdf.format(value));
			}
		});
		this.setSerializerFactory(factory);
	}
}
