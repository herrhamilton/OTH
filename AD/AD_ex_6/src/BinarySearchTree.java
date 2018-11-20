public class BinarySearchTree {
    Knot root;

    public void insert(int val) {
        Knot neww = new Knot(val);
        if(this.root != null) {
            this.root.add(neww);
        }
        else {
            this.root = neww;
        }
    }

    // VALUES! Keine References. Educate yourself!
    public void delete(int val) {
        System.out.println(("Delete " +  val));
        Knot del = root;
        Knot prev = null;
        while(del.val != val) {
            prev = del;
            del = val > del.val ? del.right : del.left;
        }
        if(del.left == null && del.right == null) {
            del = null;
        }
        else if(del.left != null && del.right == null) {
            del = del.left;
            // Knoten umhängen
        }
        else if(del.left == null && del.right != null) {
            del = del.right;
            // Knoten umhängen

        }
        else {
            Knot left = del.left;
            del = del.right;
            while(del.left != null) {
                del = del.left;
            }
            del.left = left;
            // Knoten durch Knoten mit kleinstem Wert rechts ersetzen

        }
    }

    public void print() {
        this.printElem(this.root);
        System.out.println("\n");
    }

    private void printElem(Knot root) {
        if (root!=null) {
            System.out.print("(");
            this.printElem(root.left);
            System.out.print("," + root.val + ",");
            this.printElem(root.right);
            System.out.print(")");
        }
        else {
            System.out.print("n");

        }
    }
}