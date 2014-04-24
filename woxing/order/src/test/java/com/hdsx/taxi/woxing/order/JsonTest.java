package com.hdsx.taxi.woxing.order;

import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonTest {

	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = new HashMap();
		map.put("wangx", 12312);
		map.put("name", "王翔");
		map.put("age", 13810195316L);

		String json = mapper.writeValueAsString(map);
		System.out.println(json);

	}

}
