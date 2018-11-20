
public class CircularList {

    public ListElem first;
    public int count;
    
    public CircularList() {
        this.count = 0;
    }

    public void append(Integer val) {

        if(this.count != 0) {
            ListElem elem = this.first;
            while(elem.next() != this.first) {
                elem = elem.next();
            }

            elem.nextElem = new ListElem(val, elem, this.first);
            this.first.prevElem = elem.next();
        }
        else {
            this.first = new ListElem(val, null, null);
            this.first.prevElem = this.first;
            this.first.nextElem = this.first;

        }

        this.count++;

    }

    private ListElem find(Integer val) {
        if(this.count != 0) {
            ListElem elem = this.first;
            // was macht "while(elem?.next())" ?
            while(elem.next() != null) {
                if(elem.val == val) return elem;
                elem = elem.next();
            }
            return elem.val == val ? elem : null;
        }
        else {
            return null;
        }
    }
    public void delete(Integer val) {
        ListElem elem = this.find(val);
        if(elem == null) return;

        elem.prev().nextElem = elem.next();
        elem.next().prevElem = elem.prev();
        this.count--;
        // delete val ?
    }

    public boolean contains(Integer val) {
        return this.find(val) != null;

    }

    public void sort() {
        this.Quicksort(0, this.count-1);
    }

    private void Quicksort(int first, int last) {

        if(first<last) {
            int pivot = PreparePartition(first, last);
            System.out.println(pivot);
            System.out.println("QS" + "(" + first + "," + (pivot-1) + ")");
            System.out.println("QS" + "(" + (pivot+1) + "," + (last) + ")");
            Quicksort(first, pivot - 1);
            Quicksort(pivot + 1, last);
        }
    }

    // Laufzeit hier sehr schlecht, da so oft findIndex
    private int PreparePartition(int first, int last) {
        ListElem pivot = this.findIndex(first);
        int p = first-1;
        Reference<ListElem> a;
        Reference<ListElem> b;

        for(int i = first+1; i<= last; i++) {
            if(this.findIndex(i).val <= pivot.val) {
                p++;
                 a = new Reference<ListElem>(this.findIndex(i));
                 b = new Reference<ListElem>(this.findIndex(p));
                this.swap(a, b);
            }
        }
        a = new Reference<ListElem>(this.findIndex(first));
        b = new Reference<ListElem>(this.findIndex(p));
        this.swap(a, b);
        print();
        return p+1;
    }

    private void swap(Reference<ListElem> a, Reference<ListElem> b) {
        ListElem temp = a.get();
        a.set(b.get());
        b.set(temp);


        /*
        ListElem temp = a;
        a = b;
        b = temp;

        if (a.next() != b && a.next().next() != b) {
        /*

            ListElem aPrev = a.prev();
            ListElem aNext = a.next();
            ListElem bPrev = b.prev();
            ListElem bNext = b.next();

            a.prevElem = bPrev;
            a.nextElem = bNext;
            b.prevElem = aPrev;
            b.nextElem = aNext;

            aPrev.nextElem = b;
            aNext.prevElem = b;
            bPrev.nextElem = a;
            bNext.prevElem = a;
        }
        else if(a.next() == b) {

        }*/
    }
    // bequem, aber zerstört Laufzeit
    private ListElem findIndex(int i) {
        if(i < this.count) {
            ListElem elem = this.first;
            int c = 0;
            while(c < i) {
                elem = elem.next();
                c++;
            }
            return elem;
        }
        else {
            return null;
        }
    }

    public void print() {
        if(this.count != 0) {
            ListElem elem = this.first;
            int count = 0;
            while(elem.next() != this.first) {
                if(count++ > 10) {
                    System.out.print("\n");
                    count = 0;
                }
                System.out.print("(" + elem.val + ")=");
                elem = elem.next();
            }
            System.out.print("(" + elem.val + ")\n");
        }
        else {
            System.out.println("<empty>\n");
        }

    }

    // public void delList() notwendig?



}