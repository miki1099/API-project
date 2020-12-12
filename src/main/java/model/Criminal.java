package model;

import lombok.Data;

@Data
public class Criminal {

    private String title;
    private String age_min;
    private String sex;
    private String uid;
    private String details;

    @Override
    public String toString() {
        String result = "title: " + title + '\n' +
                "age: " + age_min + '\n' +
                "sex: " + sex + '\n' +
                "details: " + details + '\n';
        result = result.replace("None", "Unknow");
        return result;
    }
}
