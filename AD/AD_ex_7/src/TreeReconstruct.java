public class TreeReconstruct {

    public static void main(String args[]) {
        String preorder = "6,3,1,8,7,9";
        String inorder = "1,3,6,7,8,9";
        TreeReconstruct rec = new TreeReconstruct();

        // bisher nur für einstellige Werte
        System.out.println(rec.Reconstruct(preorder, inorder));
    }

    public String Reconstruct(String preorder, String inorder) {
        String tree = "";
        // deshalb nur für einstellige Werte möglich
        char[] pre = preorder.replace(",", "").toCharArray();
        char in[] = inorder.replace(",", "").toCharArray();

        int n = pre.length;

        char[] leftIN = new char[n];
        int l = 0;
        char[] rightIN = new char[n];
        int r = 0;
        char root = pre[0];
        boolean rootFound = false;

        for (int i = 0; i < n; i++) {
            char temp = in[i];
            if (temp == root) {
                rootFound = true;
            } else if (!rootFound) {
                leftIN[l++] = temp;
            } else {
                rightIN[r++] = temp;
            }
        }

        char[] leftPRE = new char[n];
        char[] rightPRE = new char[n];

        l = 0;
        r = 0;

        for(int i=1; i<n; i++) {
            if(Contains(leftIN, pre[i])) {
                leftPRE[l++] = pre[i];
            }
            else {
                rightPRE[r++] = pre[i];
            }
        }

        // arrays kürzen
        String leftPre = GetOrderString(leftPRE);
        String leftIn = GetOrderString(leftIN);
        String rightPre = GetOrderString(rightPRE);
        String rightIn = GetOrderString(rightIN);

        tree += "(";
        tree += Print(leftPRE, leftPre, leftIN, leftIn);
        tree += "," + root + ",";
        tree += Print(rightPRE, rightPre, rightIN, rightIn);
        tree +=")";

        return tree;
    }

    private boolean Contains(char[] arr, char val) {
        for(char ch : arr) {
            if(ch == val)
                return true;
        }
        return false;
    }

    private String GetOrderString(char[] arr) {
        String retStr = "";
        for(char ch : arr) {
            retStr += ch + ",";
        }
        return retStr;

    }


    private String Print(char[] PRE, String Pre, char[] IN, String In) {
        String retStr = "";
        if(GetLen(PRE) > 3) {
            retStr += Reconstruct(Pre, In);
        }
        else if (GetLen(PRE) == 3) {
            retStr += "(" + IN[0] + "," + IN[1] + "," + IN[2] + ")";
        }
        else if(GetLen(PRE) == 2){
            if(PRE[0] != IN[0]) {
                retStr += "(" + IN[0] + "," + IN[1] + ",n" + ")";
            }
            else {
                retStr += "(n," + IN[0] + "," + IN[1] + ")";
                // idk if that can happen
            }
        }
        else {
            System.out.print("(n," + IN[0] + ",n)");
        }

        return retStr;
    }

    public int GetLen(char[] arr) {
        int cnt = 0;
        while(arr[cnt] != '\0') {
            cnt++;
        }
        return cnt;
    }
}
