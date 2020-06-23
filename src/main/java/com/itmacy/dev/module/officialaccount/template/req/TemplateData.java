package com.itmacy.dev.module.officialaccount.template.req;

import lombok.Data;

/**
 * 模板data
 * Created by itmacy on 2019/8/12.
 */
@Data
public class TemplateData {


    /**
     * {
     "first": {
     "value":"恭喜你购买成功！",
     "color":"#173177"
     },
     "keyword1":{
     "value":"巧克力",
     "color":"#173177"
     },
     "keyword2": {
     "value":"39.8元",
     "color":"#173177"
     },
     "keyword3": {
     "value":"2014年9月22日",
     "color":"#173177"
     },
     "remark":{
     "value":"欢迎再次购买！",
     "color":"#173177"
     }
     }
     */


    private DataValue first;
    private DataValue keyword1;
    private DataValue keyword2;
    private DataValue keyword3;
    private DataValue keyword4;
    private DataValue keyword5;
    private DataValue remark;


    @Data
    public static class DataValue{

        private String value;
        private String color;

        public DataValue() {
        }

        public DataValue(String value, String color) {
            this.value = value;
            this.color = color;
        }


    }




}
