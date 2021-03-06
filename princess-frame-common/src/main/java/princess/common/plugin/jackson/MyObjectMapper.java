package princess.common.plugin.jackson;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import base.collect.Listx;
import base.collect.Mapx;
import base.lang.DateUtil;
import princess.common.plugin.jackson.databind.BigDecimalPowerDeserializer;
import princess.common.plugin.jackson.databind.BigDecimalPowerSerializer;
import princess.common.plugin.jackson.databind.DatePowerDeserializer;
import princess.common.plugin.jackson.databind.DatePowerSerializer;
import princess.common.plugin.jackson.databind.TreeNodeConverts;


/**
 * Jackson_ObjectMapper的自定义扩展
 * @author _yyl
 */
@SuppressWarnings("serial")
public class MyObjectMapper extends ObjectMapper {

    public static final MyObjectMapper INSTANCE = new MyObjectMapper();

    public MyObjectMapper() {

        // 当找不到对应的序列化器时 忽略此字段
        this.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 支持结束
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //
        this.setDateFormat(new SimpleDateFormat(DateUtil.ISO_DATETIME_FORMAT));
        // 反序列化忽略不需要的字段
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();

        // 将 Long 转换为 String 类型
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 将 Long 转换为 String 类型
        module.addSerializer(BigDecimal.class, BigDecimalPowerSerializer.INSTANCE);
        module.addDeserializer(BigDecimal.class, BigDecimalPowerDeserializer.INSTANCE);

        // 日期序列化与反序列化
        module.addSerializer(Date.class, DatePowerSerializer.INSTANCE);
        module.addDeserializer(Date.class, DatePowerDeserializer.INSTANCE);

        // 扩展集合类反序列化
        module.addDeserializer(Mapx.class, TreeNodeConverts.MAP_DESERIALIZER);
        module.addDeserializer(Listx.class, TreeNodeConverts.LIST_DESERIALIZER);

        this.registerModule(module);
    }
}
