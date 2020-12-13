package model;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Criminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CR_ID")
    private int id;
    @Column(name = "CR_TITLE")
    private String title;
    @Column(name = "CR_AGE")
    private String age_min;
    @Column(name = "CR_SEX")
    private String sex;
    @Column(name = "CR_UID")
    private String uid;
    @Column(name = "CR_NATIONALITY")
    private String nationality;
    @Column(name = "CR_HAIR")
    private String hair_raw;
    @Column(name = "CR_EYES")
    private String eyes;
    @Column(name = "CR_RACE")
    private String race;

    @Override
    public String toString() {
        String result = "----------------------------------------------" +'\n' +
                "title: " + title + '\n' +
                "age: " + age_min + '\n' +
                "sex: " + sex + '\n' +
                "nationality: " + nationality + '\n' +
                "hair: " + hair_raw + '\n' +
                "eyes: " + eyes + '\n' +
                "race: " + race + '\n';
        result = result.replace("None", "Unknow");
        return result;
    }
}
