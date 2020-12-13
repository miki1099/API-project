package model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Data
@ToString
@Getter
public class CriminalHolder {

    private String total;
    private Criminal[] items;
    private String page;


    public void connectCriminalLists(CriminalHolder criminalHolder) {
        Criminal[] list = new Criminal[this.items.length + criminalHolder.items.length];
        System.arraycopy(this.items, 0, list, 0, this.items.length);
        System.arraycopy(criminalHolder.items, 0, list, this.items.length,
                criminalHolder.items.length);

        this.items = list;
    }

}
