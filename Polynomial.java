1 import java.util.Scanner;
2
3 public class Polynomial
4 {
5
6 private static class Node
7 {
8 private int coef;
9 private int expo;
10 public Node next;
11
12 public Node(int c, int e, Node n)
13 {
14 coef = c;
15 expo = e;
16 next = n;
17 }
18
19 public int getCoef()
20 {
21 return coef;
22 }
23
24 public int getExpo()
25 {
26 return expo;
27 }
28
29 public Node getNext()
30 {
31 return next;
32 }
33
34 public void setNext(Node n)
35 {
36 next = n;
37 }
38
39 }
40
41 private Node head = null;
42 private Node tail = null;
43 private int size = 0;
44
45 public Polynomial()
46 {
47 }
48
49 public int size()
50 {
51 return size;
52 }
53
54 public boolean isEmpty()
55 {
56 return size == 0;
57 }
58
59 public void addTerm(int c, int e)
60 {
61 Node newest = new Node(c, e, null);
62
63 if( isEmpty() )
64 {
65 head = newest;
66 }
67
68 else
69 {
70 tail.setNext(newest);
C:\Users\gusta\Downloads\CMPS 221\Polynomial.java 1
71 }
72
73 tail = newest;
74 size++;
75 }
76
77 public void print()
78 {
79 String poly = "";
80
81 for(Node i = head; i != null; i = i.next)
82 {
83 if(i.coef > 0)
84 {
85
86 poly = poly + " + " + i.coef + "x^" + i.expo;
87 }
88
89 else if(i.coef < 0)
90 {
91 poly = poly + " - " + (-i.coef) + "x^" + i.expo;
92 }
93 }
94
95 System.out.println(poly + "\n");
96 }
97
98 public Polynomial add(Polynomial b)
99 {
100 Polynomial a = this;
101 Polynomial c = new Polynomial();
102 Node x = a.head;
103 Node y = b.head;
104
105 while( x!= null || y != null)
106 {
107 Node t = null;
108 if (x == null)
109 {
110 t = new Node(y.coef, y.expo, y.next);
111 }
112
113 else if (y == null)
114 {
115 t = new Node(x.coef, x.expo, x.next);
116 }
117
118 else if (x.expo > y. expo)
119 {
120 t = new Node(x.coef, x.expo, x.next);
121 }
122
123 else if (x.expo < y.expo)
124 {
125 t = new Node(y.coef, y.expo, y.next);
126 }
127
128 else
129 {
 int coef = x.coef + y.coef;
 int expo = x.expo;
 Node next = y.next;

 x = x.next;
 y = y.next;

 if (coef == 0)
 {
 continue;
 }
C:\Users\gusta\Downloads\CMPS 221\Polynomial.java 2

 t = new Node(coef, expo, next);
 }

 c.tail.next = t;
 c.tail = c.tail.next;

 }

 return c;
 }

 public Polynomial multiply(Polynomial b)
 {
 Polynomial a = this;
 Polynomial c = new Polynomial();

 for(Node x = a.head; x != null; x = x.next)
 {
 Polynomial temp = new Polynomial();

 for(Node y = b.head; y != null; y = y.next)
 {
 temp.tail.next = new Node(x.coef * y.coef, x.expo + y.expo, temp.
tail.next);
 temp.tail = temp.tail.next;
 }

 c = c.add(temp);
 }

1 return c;
 }


 public static void main(String args[])
 {

 Polynomial p = new Polynomial();

 System.out.println("NEW POLYNOMIAL p(x): What order will this polynomial
be?");
 Scanner scan = new Scanner(System.in);
 int o = scan.nextInt();

 System.out.println("Enter values for the coefficient and exponent of the
first term with each value followed the return key.");
 Scanner scan2 = new Scanner(System.in);
 int a = scan.nextInt();
 int b = scan.nextInt();

 p.addTerm(a, b);

 for (int i = 1; i < o; i++)
 {
 System.out.println("Enter values for the coefficient and exponent of
the next term with each value followed by the return key.");
 int c = scan.nextInt();
 int d = scan.nextInt();

 p.addTerm(c, d);
 }

 System.out.println("p(x) => ");
 p.print();


 Polynomial q = new Polynomial();

 System.out.println("NEW POLYNOMIAL q(x): What order will this polynomial
C:\Users\gusta\Downloads\CMPS 221\Polynomial.java 3
be?");
 Scanner scan3 = new Scanner(System.in);
 int o2 = scan.nextInt();

 System.out.println("Enter values for the coefficient and exponent of the
first term with each value followed the return key.");
 Scanner scan4 = new Scanner(System.in);
 int e = scan.nextInt();
 int f = scan.nextInt();

 q.addTerm(e, f);

 for (int j = 1; j < o2; j++)
 {
 System.out.println("Enter values for the coefficient and exponent of
the next term with each value followed by the return key.");
 int g = scan.nextInt();
 int h = scan.nextInt();

 q.addTerm(g, h);
 }

 System.out.println("q(x) => ");
 q.print();

 System.out.println("Would you like to add p(x) and q(x)? Type 'yes' or
'no'. ");
 Scanner scan5 = new Scanner(System.in);
 String option = scan.next();

 if( option.equals("yes") )
 {
 p.add(q);
 System.out.println("The sum of p(x) and q(x) is: ");
 p.add(q).print();
 }

 else if( option.equals("no") )
 {
 System.out.println("Would you like to multiply p(x) and q(x)? Type
'yes' or 'no'. ");
 Scanner scan6 = new Scanner(System.in);
 String option2 = scan.next();

 if( option2.equals("yes") )
 {
 p.multiply(q);
 System.out.println("The product of p(x) and q(x) is: ");
 p.multiply(q).print();
 }

 else
 {
 System.out.println("Program terminated.");
 System.exit(0);
 }
 }

 System.out.println("Program terminated.");
 System.exit(0);

 }


 }
C:\Users\gusta\Downloads\CMPS 221\Polynomial.java 4