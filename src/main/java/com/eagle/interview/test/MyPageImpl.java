package com.eagle.interview.test;

import java.util.ArrayList;
import java.util.List;

public class MyPageImpl implements MyPage<MyServer> {
	@Override
	public List<MyServer> getContent() {
		List list =  new ArrayList();
		list.add(new MyServer());
		list.add(new MyServer());
		return list;
	}
}
