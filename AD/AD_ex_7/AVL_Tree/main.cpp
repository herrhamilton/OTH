#include "AVL_Tree.h"
#include "AVL_Sorter.h"

#define Ex_2
//#define Ex_3
//#define Ex_4

void Print(int*, int);
int TreeCount(int);

int main()
{
    #ifdef Ex_2
{
    avltree1* boy = new avltree1();

    boy->Insert(5);
    boy->Insert(6);
    boy->Insert(9);
    boy->Insert(12);
    boy->Insert(13);
    boy->Insert(3);
    boy->Insert(8);
    boy->Insert(10);
    boy->Insert(11);
    boy->Insert(16);
    boy->Insert(15);
    boy->Insert(14);
    boy->Insert(4);
    boy->Insert(2);
    boy->Insert(1);

    boy->Delete(12);
    boy->Delete(8);
    boy->Delete(5);
    boy->Delete(4);
    boy->Delete(3);
    boy->Delete(6);
    boy->Delete(15);
    boy->Delete(14);

    boy->Print();
}
    #endif // Ex_2

    #ifdef Ex_3
{
    int height;
    cout << "Input height: ";
    cin >> height;
    cout << TreeCount(height);
}
    #endif // Ex_3

    #ifdef Ex_4
{
    avlsorter1* sorter = new avlsorter1();
    int arr[] = {1,78,17,31,40,7,3,23,54};
    int n = 9;
    Print(arr, n);
    sorter->Sort(arr, n);
    Print(arr, n);
}
    #endif // Ex_4

    return 0;
}

void Print(int* arr, int n) {
    for(int i=0; i<n; i++) {
        cout << arr[i] << ", ";
    }
    cout << "\n";
}

int TreeCount(int height) {
    if(height > 6) {
        cout << "Lol too much\n";
        return 0;
    }
    else if(height == 0) {
        return 1;
    }
    else if(height == 1) {
        return 2;
    }
    else {
        return 2 * (TreeCount(height-1) * TreeCount(height-2));
    }
}
