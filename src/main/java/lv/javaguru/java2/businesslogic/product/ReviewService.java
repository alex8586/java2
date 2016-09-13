package lv.javaguru.java2.businesslogic.product;

public interface ReviewService {
    /*
    can make review if
        - logged in
        - purchased it before , at least 5 days ago
        -
    */
    void addComment(String comment);

}
