package com.noteshare.xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/** 
 * @Title: Pets.java 
 * @Package com.noteshare.xstream 
 
 * @author noteshare
 * @date 2015年6月30日 下午5:55:53 
 * @version V1.0 
 */
public class Pets {
    @XStreamImplicit(itemFieldName="pet")
    private List<Animal> animalList;
     
    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
