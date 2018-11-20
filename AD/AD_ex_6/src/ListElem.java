import java.util.Comparator;

public class ListElem {
    int val;
    ListElem prevElem;
    ListElem nextElem;

    public ListElem(int val, ListElem prev, ListElem next ) {
        this.val = val;
        this.prevElem = prev;
        this.nextElem = next;
    }

    public ListElem next() {
        return this.nextElem;
    }

    public ListElem prev() {
        return this.prevElem;
    }

    @Override
    public String toString() {
        return "[" + this.val + "]";
    }
    
}