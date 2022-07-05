package com.gizmo.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Xml 생성
 */
public class XmlUtil {

    public static final int BUFF_SIZE = 2048;

    /**
     * List를 XML로 만든다.
     * @param list List 데이터.
     */
    public static String doListToXML(ArrayList<Map<String, Object>> list) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        XmlWrapper wrapper = new XmlWrapper();
        String xmlStr = "<ROOT>\n";
        xmlStr += wrapper.format(list, null);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }

    /**
     * List를 XML로 만든다.
     * @param list List 데이터.
     */
    public static String doListToXML(List<?> list) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        XmlWrapper wrapper = new XmlWrapper();
        String xmlStr = "<ROOT>\n";
        xmlStr += wrapper.format(ConvertUtils.convertToMaps(list), null);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }

    /**
     * List를 XML로 만든다.
     * @param list Map형식의 List 데이터.
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     * @return
     */
    public static String doListToXML(List<?> list, ArrayList<String> exceptColumn) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        XmlWrapper wrapper = new XmlWrapper();

        String xmlStr = "<ROOT>\n";
        xmlStr += wrapper.format(ConvertUtils.convertToMaps(list), exceptColumn);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }

    public static String doObjectToXML(Object obj){
        XmlWrapper wrapper = new XmlWrapper();

        String xmlStr = "<ROOT>\n";
        xmlStr += wrapper.format(ConvertUtils.convertToMap(obj), null);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }

    /**
     * Map 데이터를 xml로 변환한다.
     * @param map 데이터
     * @return
     */
    public static String doMapToXML(Map<String, Object> map){
        XmlWrapper wrapper = new XmlWrapper();

        String xmlStr = "<ROOT>\n";
        xmlStr += wrapper.format(map, null);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }

    /**
     * Map 데이터를 xml로 변환한다.
     * @param map 데이터
     * @param exceptColumn 예외처리할 컬럼.
     * @return
     */
    public static String doMapToXML(Map<String, Object> map, ArrayList<String> exceptColumn){
        XmlWrapper wrapper = new XmlWrapper();

        String xmlStr = "<ROOT>\n";
        xmlStr += wrapper.format(map, exceptColumn);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }
}
