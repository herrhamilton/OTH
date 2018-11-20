#include "AVL_Tree.h"

avltree1::avltree1() {
    root=NULL;
}

avltree1::~avltree1() {
    DeleteTree(root);
    root=NULL;
}

avlelem1* avltree1::GetRoot() {
    return this->root;
}

int avltree1::Max(int a, int b) {
    if (a<b) return b; else return a;
}

int avltree1::GetHeight(avlelem1 *elem)
{
    if (elem==NULL) return -1;
    else return elem->height;
}

void avltree1::UpdateHeight(avlelem1 *elem)
{
    elem->height = 1 + Max(GetHeight(elem->left),
                          GetHeight(elem->right));
}

void avltree1::RotateLeft(avlelem1* &a) {
    avlelem1 *b=a->right;
    a->right=b->left; b->left=a; a=b;
    UpdateHeight(a->left); UpdateHeight(a);
}

void avltree1::RotateRight(avlelem1* &a) {
    avlelem1 *b=a->left;
    a->left=b->right; b->right=a; a=b;
    UpdateHeight(a->right); UpdateHeight(a);
}

void avltree1::DoubleRotationLeft(avlelem1* &a) {
    RotateRight(a->right);
    RotateLeft(a);
}

void avltree1::DoubleRotationRight(avlelem1* &a) {
    RotateLeft(a->left);
    RotateRight(a);
}

void avltree1::CheckRotationRight(avlelem1* &elem) {
    if (elem!=NULL)
    {
        if (elem->left!=NULL)
        {
            if (GetHeight(elem->left)-GetHeight(elem->right)==2)
            {
                if (GetHeight(elem->left->right) >
                    GetHeight(elem->left->left)) {
                    DoubleRotationRight(elem);
                }
                else RotateRight(elem);
            }
            else UpdateHeight(elem);
        }
        else UpdateHeight(elem);
    }
}

void avltree1::CheckRotationLeft(avlelem1* &elem) {
    if (elem!=NULL)
    {
        if (elem->right!=NULL)
        {
            if (GetHeight(elem->right)-GetHeight(elem->left)==2)
            {
                if (GetHeight(elem->right->left)>
                    GetHeight(elem->right->right)){
                    DoubleRotationLeft(elem);
                }
                else RotateLeft(elem);
            }
            else UpdateHeight(elem);
        }
        else UpdateHeight(elem);
    }
}

void avltree1::Insert(avlelem1* &elem,object o) {
    if (elem==NULL) {
        elem=new avlelem1;
        elem->height=0; elem->val=o;
        elem->left=NULL;
        elem->right=NULL;
    }
    else {
        if (o <= elem->val) {
            Insert(elem->left,o);
            CheckRotationRight(elem);
        }
        else {
            Insert(elem->right,o);
            CheckRotationLeft(elem);
        }
    }
}

void avltree1::Insert(object o) {
    Insert(root,o);
}

void avltree1::Delete(object o){
    Delete(root,o);
}


avlelem1* avltree1::FindElemToSwitch(avlelem1* &prev, avlelem1* &elem) {
    avlelem1* ret;
    if(elem->left == NULL) {
        ret = elem;
        prev->left = NULL;
        UpdateHeight(prev);
        CheckRotationLeft(prev);
    }
    else {
        ret = FindElemToSwitch(elem, elem->left);
        UpdateHeight(elem);
        CheckRotationLeft(elem);
    }
    return ret;
}

avlelem1* avltree1::SwitchElems(avlelem1* &elem) {
    if(elem->left == NULL && elem->right == NULL) {
        return NULL;
    }
    else if(elem->left == NULL && elem->right != NULL) {
        return elem->right;
    }
    else if(elem->left != NULL && elem->right == NULL) {
        return elem->left;
    }
    else if(elem->right->left == NULL) {
        avlelem1* ret = elem->right;
        ret->left = elem->left;
    return ret;
    }
    else {
        avlelem1* ret = FindElemToSwitch(elem->right, elem->right->left);
        ret->left = elem->left;
        return ret;
    }
}

void avltree1::Delete(avlelem1* &elem,object o) {
// Entsprechend der Nachfolger
// Knoten umhängen
// Knoten löschen
// Höhen aktualisieren
    if(elem == NULL) {
        cout << "Sounds like an error..";
    }
    else if(elem->val == o) {
        cout << "Ain't he supposed to go in there only for the root?";
        elem = NULL;
    }
    else if(elem->left != NULL && elem->left->val == o) {
        elem->left = SwitchElems(elem->left);
        UpdateHeight(elem);
        CheckRotationLeft(elem);
    }
    else if(elem->right != NULL && elem->right->val == o) {
        elem->right = SwitchElems(elem->right);
        UpdateHeight(elem);
        CheckRotationRight(elem);
    }
    else {
        if (o <= elem->val) {
            Delete(elem->left,o);
            UpdateHeight(elem);
            CheckRotationLeft(elem);
        }
        else {
            Delete(elem->right,o);
            UpdateHeight(elem);
            CheckRotationRight(elem);
        }
    }
}

void avltree1::DeleteTree(avlelem1* a) {
    // TODO: Implement
}

void avltree1::Print() {
    Print(root);
}

void avltree1::Print(avlelem1 *curr) {
    if (curr!=NULL) {
        cout<<"(";
        Print(curr->left);
        cout<<","<<curr->val<<",";
        Print(curr->right);
        cout<<")";
    }
    else { cout<<"n"; }
}
