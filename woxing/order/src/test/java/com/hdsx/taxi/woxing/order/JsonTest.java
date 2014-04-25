package com.hdsx.taxi.woxing.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

		map.put("sss", null);
		String json = mapper.writeValueAsString(map);
		System.out.println(json);

		List<Integer> l = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++) {

			l.add(i);
		}

		for (int i = 0; i < l.size(); i++) {
			int v = l.get(i);
			if (v % 3 == 0)
				l.remove(i);
		}

		for (Integer integer : l) {
			System.out.println(integer);
		}
	}

}
