package AI;

import java.util.*;

public class HashInfo<T> {

    private HashSet<T> hash; 
    private Boolean[] hashArray;

    public HashInfo(){
        this.hash = new HashSet<>();
        this.hashArray = new Boolean[9999999];
    }


    public void add(T value){
        hashArray[hash.hashCode()] = true;
        hash.add(value);
    }

    public boolean contains(T value){
        return hashArray[value.hashCode()] == null ? false : true;
    }

}
