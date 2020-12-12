package model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.List;

@Data
@ToString
@Getter
public class CriminalHolder {

    private String total;
    private Criminal[] items;
    private String page;

}
