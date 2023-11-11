final color red     = color(255, 0, 0);
final color green   = color(0, 256, 0);
final color blue    = color(0, 0, 255);

final color yellow  = color(255, 255, 0);
final color magenta = color(255, 0, 255);
final color cyan    = color(0, 255, 255);

final color gray = color(85, 85, 85);

float dx;

color co1;
color co2;
color co3;
color co4;
color co5;
color co6;
color co7;
color co8;
color co9;
color co10;
color co11;
color co12;

class Circle {
 float x; // x coordinate of center
 float y; // y coordinate of center
 color c; // color
 Circle (float x, float y, color c)
 {
 this.x = x;
 this.y = y;
 this.c = c;
 }

 void circledraw()
 {
 stroke(c);
 fill(c);
 circle(x, y, 80);
 }
} 

class TrafficLights {
  float x;
  float y;
  Circle a[] = new Circle[20];
  TrafficLights(float x, float y, Circle a[])
  {
    this.x = x;
    this.y = y;
    this.a = a;
  }
  void rectdraw()
  {
    stroke(#433938);
    fill(#433938);
    rect(x, y, 80, 240);
  }
  void allcircledraw()
  {
    for(int i = 0; i < 3; i++)
    {
     a[i].circledraw();
    }
  }
}

void roaddraw(TrafficLights a[], int n)
{
  for (int i = 0; i < n; i++)
  {
    a[i].rectdraw();
    a[i].allcircledraw();
  }
}

void setup()
{
  size(600, 600);
  dx = 1;
}

float time(float dx1)
{
  float result = 0;
  if(dx1 == 1)
   result = frameCount % 120;
  else if(dx1 == 2 || dx1 == 3)
    result = frameCount % 60;
    
    return result;
}

void draw()
{

  Circle c1 = new Circle(width/2, height/2 - 240/2 + 40, co1); Circle c2 = new Circle(width/2, height/2 - 240/2 + 120, co3); Circle c3 = new Circle(width/2, height/2 - 240/2 + 200, co2);
  Circle circles[] = {c1, c2, c3};
  TrafficLights sm1 = new TrafficLights(width/2 - 80/2, height/2 - 240/2, circles);
  TrafficLights sm[] = {sm1};
  background(200);
  roaddraw(sm, 1);
  
   if(dx > 3)
   dx = 1;
   if(time(dx) == 0)
   dx++;
  //----------------------------------------------------------------------------------------
  if(dx == 1 || dx > 3)
   co1 = red;
  else
  co1 = gray;

  if(dx == 2)
  co2 = green;
  else
  co2 = gray;

  if(dx == 3)
   co3 = yellow;
  else
  co3 = gray;
  //------------------------------------------------------------------------------------------
}
