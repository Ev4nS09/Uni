final color black   = color(0, 0, 0);
final color white   = color(255, 255, 255);
final color pt_green = color(0, 102, 0);
final color ag_red = color(200, 16, 46);
final color eau_green = color (0, 115, 47);

// Primary colors
final color red     = color(255, 0, 0);
final color green   = color(0, 256, 0);
final color blue    = color(0, 0, 255);

// Secondary colors
final color yellow  = color(255, 255, 0);
final color magenta = color(255, 0, 255);
final color cyan    = color(0, 255, 255);

float x1 = 1;
float x2 = 2;
float x3 = 3;
float x4 = 4;

void setup()
{
 size(600, 400); 
}

class Rect {
 float x;
 float y;
 float l; // latgura || comprimento do retangulo
 float h; //Altura do retangulo
 color c;
 Rect (float x, float y, float l, float h, color c)
 {
 this.x = x;
 this.y = y;
 this.l = l;
 this.h = h;
 this.c = c;
 }
 void rectdraw()
{
  stroke(c);
  fill(c);
  rect(x, y, l, h);
}
} 

class Flag {
 float n;
 Rect a[] = new Rect[20];
 Flag (Rect a[], float n)
 {
 this.a = a;
 this.n = n;
 }
 void flagdraw()
{
  for(int i = 0; i < n; i++)
  {
    a[i].rectdraw();
  }
} 
}

void finaldraw(Flag p[], int n)
{
  for(int i = 0; i < n; i++)
  {
    p[i].flagdraw();
  }
}

void draw()
{
//Portugal
Rect p1 = new Rect((120*(x1 - 1)) + (x1*24), (400/2) - 40, 40, 80, pt_green);
Rect p2 = new Rect((120*(x1 - 1)) + (x1*24) + 30 + 10, (400/2) - 40, 80, 80, red);

//Inglaterra
Rect i1 = new Rect((120*(x2 - 1)) + (x2*24), (400/2) - 40, 120, 80, white);
Rect i2 = new Rect((120*(x2 - 1)) + (x2*24) + 50, (400/2) - 40, 20, 80, ag_red);
Rect i3 = new Rect((120*(x2 - 1)) + (x2*24), (400/2) - 10, 120, 20, ag_red);

//Angola
Rect a1 = new Rect((120*(x3 - 1)) + (x3*24), (400/2) - 40, 120, 80/2, ag_red);
Rect a2 = new Rect((120*(x3 - 1)) + (x3*24), (400/2), 120, 80/2, black);

//Emirados arabes unidos
Rect e1 = new Rect((120*(x4 - 1)) + (x4*24), (400/2) - 40, 25, 80, red);
Rect e2 = new Rect((120*(x4 - 1)) + (x4*24) + 25, (400/2) - 40, 120 - 25, 80/3, eau_green);
Rect e3 = new Rect((120*(x4 - 1)) + (x4*24) + 25, (400/2) - 40 + (80/3), 120 - 25, 80/3, white);
Rect e4 = new Rect((120*(x4 - 1)) + (x4*24) + 25, (400/2) - 40 + 2*(80/3), 120 - 25, 80/3, black);

//Array de retangulos
Rect portugal[] = {p1, p2}; 
Rect inglaterra[] = {i1, i2, i3}; 
Rect angola[] = {a1, a2}; 
Rect eau[] = {e1, e2, e3, e4}; 

//Flag inteira
Flag portugalf = new Flag(portugal, 2);
Flag inglaterraf = new Flag(inglaterra, 3);
Flag angolaf = new Flag(angola, 2);
Flag eauf = new Flag(eau, 4);
Flag b[] = {portugalf, inglaterraf, angolaf, eauf};


  background(200);
  finaldraw(b, 4);
  if(frameCount % 120 == 0)
  {
  x1 -= 1;
  x2 -= 1;
  x3 -= 1;
  x4 -= 1;
  }
  
  if(x1 < 1)
   x1 = 4;
  if(x2 < 1)
   x2 = 4;
  if(x3 < 1)
   x3 = 4;
  if(x4 < 1)
   x4 = 4;
  
}
