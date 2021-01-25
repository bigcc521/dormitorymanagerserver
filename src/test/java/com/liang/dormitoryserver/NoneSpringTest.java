package com.liang.dormitoryserver;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName NoneSpringTest
 * @Description TODO
 * @Author Liang Xi
 * @DATE 2021/1/22 16:13
 * @Version 1.0
 */

public class NoneSpringTest {
    @Test
    public void test1(){
        String info="{\"ops\":[{\"insert\":\"评分\\n寝室床铺未铺整洁\"},{\"attributes\":{\"width\":\"100%\",\"data-custom\":\"id=abcd&role=god\"},\"insert\":{\"image\":\"http://tmp/IO2ahtQadOCf627256c88c423f90ba9139828219aa08.png\"}},{\"insert\":\"\\n\\n\"}]}";
        //String regex="^[http|https]://tmp/[a-zA-Z0-9]+\\.png$";
        String regex="[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(info);
        if (matcher.find()) {
            String group = matcher.group(0);
            System.out.println("url:"+group);
        }
    }
}
