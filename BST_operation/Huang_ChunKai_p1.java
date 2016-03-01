import java.util.Scanner;
import java.util.*;
import java.io.*;


public class Huang_ChunKai_p1 {

	public static void main(String[] args)
	{

		Scanner sca = new Scanner(System.in);
		int selectindex;
		int inputint;
        Btree tree = new Btree();


		for(;;)
		{
           TreeNode Z;
		   try
		   {
		       System.out.println("MAIN MENU");
		       System.out.println("Insert: 1");
		       System.out.println("Delete: 2");
		       System.out.println("Postorder tree traversal: 3");
		       System.out.println("Reset: 4");
		       System.out.println("Exit: 5");

			   selectindex = sca.nextInt();
			   if(selectindex == 1)
               {
                System.out.print("Type a key (a positive integer) to be inserted and hit Enter:");
                inputint = sca.nextInt();
                System.out.println(inputint);
                Z = new TreeNode(inputint);
                TREE_INSERT(tree, Z);
               }
                else if(selectindex == 2)
               {
                System.out.print("Type a key to be deleted and hit Enter:");
                inputint = sca.nextInt();
                System.out.println(inputint);
                Z = Tree_Search(tree.root, inputint);
                Delete_Node(tree, Z);
               }
               else if(selectindex == 3)
               {
                PrintTree(tree.root);
                System.out.println();
               }
               else if(selectindex == 4)
               {
                Delete_tree(tree);
                System.out.print("Reset tree completed");
               }
               else if(selectindex == 5)
               {
                break;
               }
		   }
		   catch(Exception e) {System.out.println("wrong data, please try again.");}
		}
	}

  public static void TREE_INSERT(Btree T ,TreeNode Z)
  {
    TreeNode Y = null;
    TreeNode X = T.root;
    while(X != null)
    {
      Y=X;
      if(Z.key < X.key)
         X = X.left;
      else
         X = X.right;
      Z.parent = Y;
    }
    if (Y == null)
       T.root = Z;
    else if (Z.key < Y.key)
       Y.left = Z;
    else
       Y.right = Z;
  }

  public static void PrintTree(TreeNode leaf)
  {
    if (leaf!=null)
    {
      PrintTree(leaf.left);
      PrintTree(leaf.right);
      System.out.print("["+leaf.key+"] ");
    }

  }

  public static TreeNode Tree_Search(TreeNode x, int k)
  {
    if (x==null || k == x.key)
       return x;
    if (k < x.key)
       return Tree_Search(x.left, k);
    else
       return Tree_Search(x.right, k);
  }

  public static void Delete_Node(Btree T, TreeNode Z)
  {
    TreeNode y;
    if (Z.left == null)
         Transplant(T,Z,Z.right);
    else if (Z.right == null)
         Transplant(T,Z,Z.left);
    else
    {
     y = Find_Min(Z.right);
     if (y.parent != Z)
     {
       Transplant(T, y, y.right);
       y.right = Z.right;
       y.right.parent = y;
     }
     Transplant(T, Z, y);
     y.left = Z.left;
     y.left.parent = y;

    }
  }

  public static TreeNode Find_Min(TreeNode x)
  {
    while(x.left != null)
        x = x.left;
    return x;
  }

  public static void Transplant(Btree T, TreeNode u, TreeNode v)
  {
    if (u.parent==null)
        T.root=v;
    else if (u==u.parent.left)
        u.parent.left=v;
    else u.parent.right=v;
    if (v!=null)
        v.parent=u.parent;
  }
  public static void Delete_tree(Btree T)
  {
    T.root = null;
  }

}

class TreeNode
{
  int key;
  TreeNode left;
  TreeNode right;
  TreeNode parent;

  public TreeNode(int key)
  {
    this.key=key;
    this.left=null;
    this.right=null;
    this.parent=null;
  }
}

class Btree
{
 TreeNode root;

 public void SetRootNull()
 {
    this.root = null;
 }

}
