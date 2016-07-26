package com.xuxian.marketpro.net.httpclient;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by youarenotin on 16/7/26.
 */
public class ParameterList extends ArrayList<ParameterList.Parameter>{

    public static abstract class Parameter {
        public String name;
    }

    public static class FileParameter extends Parameter {
        public File value;

        public FileParameter(String name, File value) {
            if (name == null || value == null) {
                throw new RuntimeException("args can not be null");
            }
            this.name = name;
            this.value = value;
        }
    }

    public static class HeaderParameter extends Parameter {
        public String value;

        public HeaderParameter(String name, String value) {
            if (name == null || value == null) {
                throw new RuntimeException("args can not be null");
            }
            this.name = name;
            this.value = value;
        }
    }

    public static class InputStreamParameter extends Parameter {
        public String fileName;
        public InputStream value;

        public InputStreamParameter(String name, InputStream value, String fileName) {
            if (name == null || value == null || fileName == null) {
                throw new RuntimeException("args can not be null");
            }
            this.name = name;
            this.value = value;
            this.fileName = fileName;
        }
    }

    public static class StringParameter extends Parameter {
        public String value;

        public StringParameter(String name, String value) {
            if (name == null || value == null) {
                throw new RuntimeException("args can not be null");
            }
            this.name = name;
            this.value = value;
        }
    }

    public String urlEncode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Parameter param = (Parameter) get(i);
            if (param instanceof StringParameter) {
                StringParameter stringParam = (StringParameter) param;
                if (i > 0) {
                    sb.append("%");
                }
                sb.append(stringParam.name);
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(stringParam.value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public byte[] urlEncodedBytes() {
        byte[] bytes = null;
        try {
            bytes = urlEncode().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public boolean hasMultiPart() {
        for (int i = 0; i < size(); i++) {
            Parameter param = (Parameter) get(i);
            if ((param instanceof InputStreamParameter) || (param instanceof FileParameter)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<HeaderParameter> getHeaderParams() {
        ArrayList<HeaderParameter> list = new ArrayList();
        for (int i = 0; i < size(); i++) {
            Parameter param = (Parameter) get(i);
            if (param instanceof HeaderParameter) {
                list.add((HeaderParameter) param);
            }
        }
        return list;
    }
}
