package com.gizmo.utils;

import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * XML을 이용하여 개발할 때 이용할 수 있는 유틸리티 클래스이다.
 */
public class XmlWrapper {

//    public static String doJsonToXml(JSONArray json) {
//        XMLSerializer xmlSerializer = new XMLSerializer();
//        String xml = xmlSerializer.write(JSONArray.fromObject(json));
//
//        return xml;
//    }

    /**
     * List를 XML로 만든다.
     * @param list Map형식의 List 데이터.
     * @return
     */
    protected String doListToXML(List<Map<String, Object>> list) {
//      return convertToXML(list, isContainSpecialChar, null);

        String xmlStr = "<ROOT>\n";
        xmlStr += format(list, null);
        xmlStr += "</ROOT>\n";

        return xmlStr;
    }

    /**
     * Map객체를 xml 형식으로 변환한다 (xml 헤더 미포함). XmlUtil.format과 동일
     * <br>
     * ex) map을 xml 형식으로 변환하는 경우 => String xml = XmlUtil.render(map)
     *
     * @param map 변환할 Map객체
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml 형식으로 변환된 문자열
     */
    protected String render(Map<String, Object> map, ArrayList exceptColumn) {
        return format(map, exceptColumn);
    }

    /**
     * Map객체를 xml 형식으로 변환한다 (xml 헤더포함). XmlUtil.format과 동일
     * <br>
     * ex) map을 xml 형식으로 변환하는 경우  => String xml = XmlUtil.render(map, "utf-8")
     *
     * @param map 변환할 Map객체
     * @param encoding 헤더에 포함될 인코딩
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml 형식으로 변환된 문자열
     */
    protected String render(Map<String, Object> map, String encoding, ArrayList exceptColumn) {
        return format(map, encoding, exceptColumn);
    }

    /**
     * List객체를 xml 형태로 변환한다 (xml 헤더 미포함).  XmlUtil.format과 동일
     * <br>
     * ex) mapList를 xml으로 변환하는 경우 => String xml = XmlUtil.render(mapList)
     *
     * @param mapList 변환할 List객체
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml형식으로 변환된 문자열
     */
    protected String render(List<Map<String, Object>> mapList, ArrayList exceptColumn) {
        return format(mapList, exceptColumn);
    }

    /**
     * List객체를 xml 형태로 변환한다 (xml 헤더포함). XmlUtil.format과 동일
     * <br>
     * ex) mapList를 xml으로 변환하는 경우  => String xml = XmlUtil.render(mapList, "utf-8")
     *
     * @param mapList 변환할 List객체
     * @param encoding 헤더에 포함될 인코딩
     *@param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml형식으로 변환된 문자열
     */
    protected String render(List<Map<String, Object>> mapList, String encoding, ArrayList exceptColumn) {
        return format(mapList, encoding, exceptColumn);
    }

    /**
     * Map객체를 xml 형식으로 변환한다 (xml 헤더포함).
     * <br>
     * ex) map을 xml 형식으로 변환하는 경우  => String xml = XmlUtil.format(map, "utf-8")
     *
     * @param map 변환할 Map객체
     * @param encoding 헤더에 포함될 인코딩
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml 형식으로 변환된 문자열
     */
    protected String format(Map<String, Object> map, String encoding, ArrayList exceptColumn) {
        if (map == null) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(xmlHeaderStr(encoding));
        buffer.append(format(map, exceptColumn));
        return buffer.toString();
    }

    /**
     * Map객체를 xml 형식으로 변환한다 (xml 헤더 미포함).
     * <br>
     * ex) map을 xml 형식으로 변환하는 경우 => String xml = XmlUtil.format(map)
     *
     * @param map 변환할 Map객체
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml 형식으로 변환된 문자열
     */
    protected String format(Map<String, Object> map, ArrayList exceptColumn) {
        if (map == null) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("<items>\n");
        buffer.append(xmlItemStr(map, exceptColumn));
        buffer.append("</items>\n");
        return buffer.toString();
    }

    /**
     * List객체를 xml 형태로 변환한다 (xml 헤더포함).
     * <br>
     * ex) mapList를 xml으로 변환하는 경우  => String xml = XmlUtil.format(mapList, "utf-8")
     *
     * @param mapList 변환할 List객체
     * @param encoding 헤더에 포함될 인코딩
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml형식으로 변환된 문자열
     */
    protected String format(List<Map<String, Object>> mapList, String encoding, ArrayList exceptColumn) {
        if (mapList == null) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(xmlHeaderStr(encoding));
        buffer.append(format(mapList, exceptColumn));
        return buffer.toString();
    }

    /**
     * List객체를 xml 형태로 변환한다 (xml 헤더 미포함).
     * <br>
     * ex) mapList를 xml으로 변환하는 경우 => String xml = XmlUtil.format(mapList)
     *
     * @param mapList 변환할 List객체
     * @param exceptColumn 리스트 데이터중 제외 하고자 하는 컬럼이 있을때 해당 컬럼의 key값을 ArrayList로 넘긴다.
     *
     * @return xml형식으로 변환된 문자열
     */
    protected String format(List<Map<String, Object>> mapList, ArrayList exceptColumn) {
        if (mapList == null) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("<listCnt>" + mapList.size() + "</listCnt>\n");
        buffer.append("<list>\n");
        for (Map<String, Object> map : mapList) {
            buffer.append(xmlItemStr(map, exceptColumn));
        }
        buffer.append("</list>\n");
        return buffer.toString();
    }

    /**
     *  xml 헤더 문자열 생성
     */
    protected String xmlHeaderStr(String encoding) {
        return "<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>\n";
    }

    /**
     * xml item 문자열 생성
     */
    @SuppressWarnings("unchecked")
    protected String xmlItemStr(Map<String, Object> map, ArrayList exceptColumn) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<item>\n");
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(exceptColumn != null && exceptColumn.contains(key)){
                continue;
            }

            if (value == null) {
                buffer.append("<" + key.toLowerCase() + ">" + "</" + key.toLowerCase() + ">\n");
            } else {
                if (value instanceof Number) {
                    buffer.append("<" + key.toLowerCase() + ">" + value.toString() + "</" + key.toLowerCase() + ">\n");
                }
                else if (value instanceof Map) {
                    buffer.append("<" + key.toLowerCase() + ">" + format((Map<String, Object>) value, exceptColumn) + "</" + key.toLowerCase() + ">\n");
                }

                else if (value instanceof List) {
                    buffer.append("<" + key.toLowerCase() + ">" + format((List<Map<String, Object>>) value, exceptColumn) + "</" + key.toLowerCase() + ">\n");
                }
                else {
                    buffer.append("<" + key.toLowerCase() + ">" + "<![CDATA[" + value.toString() + "]]>" + "</" + key.toLowerCase() + ">\n");
                }
            }
        }
        buffer.append("</item>\n");
        return buffer.toString();
    }

}