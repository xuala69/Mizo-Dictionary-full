package k.realmdataentry;

import io.realm.RealmObject;

/**
 * Created by Dsoloman on 20-03-2017.
 */

public class ModelClass extends RealmObject {


    public ModelClass() {

    }


    private String name;
    private String desc;

    public ModelClass(String name, String desc) {
        this.name=name;
        this.desc=desc;
    }

    public ModelClass(String s) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
