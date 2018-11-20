public class Knot {
    int val;
    Knot left;
    Knot right;

    public Knot(int val) {
        this.val = val;
    }

    public void add(Knot bitch) {
        if(this.val <= bitch.val) {
            if(this.right != null) {
                this.right.add(bitch);
            }
            else {
                this.right = bitch;
            }
        } else {
            if(this.left != null) {
                this.left.add(bitch);
            }
            else {
                this.left = bitch;
            }
        }
    }
}
