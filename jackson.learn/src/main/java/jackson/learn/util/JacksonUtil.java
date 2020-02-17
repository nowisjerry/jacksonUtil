package jackson.learn.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jackson.learn.enums.SexEnum;
import jackson.learn.pojo.Person;

public class JacksonUtil {

	private static String objectToJson(ObjectMapper objectMapper,Object obj) {
		String jsonStr = null;
		try {
			jsonStr = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	private static<T> T jsonToObject(ObjectMapper objectMapper,String jsonStr,Class<T> classInfo) {
		T obj = null;
		try {
			obj = objectMapper.readValue(jsonStr, classInfo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		//jsonתjava����
//		// ȥ��Ĭ�ϵ�ʱ�����ʽ
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//		// ����Ϊ�й��Ϻ�ʱ��
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//		objectMapper.setTimeZone(TimeZone.getDefault());
//		// ���л�ʱ�����ڵ�ͳһ��ʽ
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

//		// ��ö����toString()���
		objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		//��ö�����±����
//		objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, false);

//

		Person person = new Person();
		person.setName("Jack");
		person.setAge(21);
		person.setSex(SexEnum.MAN);

		String dateStr = "2012-06-18 11:11:11";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		person.setWorkFromDay(sdf.parse(dateStr));
//		System.out.println(objectToJson(objectMapper,person));






		List<Person> personList = new ArrayList<Person>();
		personList.add(person);
		Person personTwo = new Person();
		personTwo.setName("Lee");
		personTwo.setAge(28);
		personTwo.setSex(SexEnum.MAN);
		//personList.add(personTwo);
		//�����б�����ͨ����ʽ���
		objectMapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);
//		System.out.println(objectToJson(objectMapper,personList));



//		char[] countChar = new char[] { 'a', 'b', 'c', 'd' };
//		Ϊtrueʱ�����ַ�������jsonArray��ʽ���
//		objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, false);
//		System.out.println(objectToJson(objectMapper,countChar));
//		objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
//		System.out.println(objectToJson(objectMapper,countChar));
		//�����Ԫ��
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

//		System.out.println(objectToJson(objectMapper,person));



		//������ĸ�ʽ�����Ż�
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

//		System.out.println(objectToJson(objectMapper,person));




//		personTwo.setName(null);
		personTwo.setName("jack");
		personTwo.setAge(0);
//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//����Ϊnullʱ�����л�
//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);//����Ϊnull��""ʱ�����л�
//		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);//����Ϊnull��""��0ʱ�����л�
		objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);//Ĭ�϶����л�
//		System.out.println(objectToJson(objectMapper,personTwo));
//

		//jsonתjava����
		String personStr = "{\"name\":\"amanda\",\"age\":29,\"workFromDay\":\"2012-09-10 09:00:00\",\"sex\":\"MAN\"}";
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"name\":\"amanda\",\"age\":29,\"workFromDay\":\"2012-09-10 09:00:00\",\"sex\":\"MAN\",\"name1\":\"amanda1\"}";//�����ڵ�key
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"name\":\"amanda\",\"age\":29,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"}";//keyû��˫����
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"name\":\"amanda\",\"age\":0029,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"}";//value��0��ͷ
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));


		personStr = "{\"name\":\"amanda\",\"age\":0029,\"workFromDay\":,sex:\"MAN\"}";//©����һ������ֵ
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES, true);
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"name\":'amanda',\"age\":0029,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"}";//�е�����
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"name\":'amanda',\"name\":\"lee\",\"age\":0029,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"}";//�ظ���key
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true);
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"nameInfo\":'amanda',\"age\":0029,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"}";//������
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));

		personStr = "{\"nameInfo\":'amanda',\"age\":0029,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"}";//��������
//		System.out.println(jsonToObject(objectMapper,personStr,Person.class));


		String personListStr = "[{\"nameInfo\":\"amanda\",\"age\":0029,\"workFromDay\":\"2012-09-10 09:00:00\",sex:\"MAN\"},{\"nameInfo\":\"jenny'\",\"age\":0032,\"workFromDay\":\"2008-06-30 09:00:00\",sex:\"WOMAN\"}]";//���Ӷ���
		System.out.println(jsonToObject(objectMapper,personListStr,List.class));
/*		System.out.println(objectMapper.readValue(personListStr,new TypeReference<List<Person>>() {
		}));*/


	}

}
