package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;

public class URL {
	public static List<Integer> decodeListInt(String s){
		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt((String) x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
