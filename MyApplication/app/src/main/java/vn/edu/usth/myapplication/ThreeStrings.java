package vn.edu.usth.myapplication;

/**
 * Created by linhtynny on 05/12/2016.
 */

public class ThreeStrings {
    private String left;
    private String right;
    private String centre;

    public ThreeStrings(String left, String right, String centre) {
        this.left = left;
        this.right = right;
        this.centre = centre;
    }

    public String getLeft(){
        return this.left;
    }

    public String getRight(){
        return this.right;
    }

    public String getCentre(){
        return this.centre;
    }
}
