#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double pi(int n)

{
	double pc = 0;
	double pt = 0;
	for(int i; i < n; i++)

	{
      double x = rand() % 1;
      double y = rand() % 1;
      double d = pow(x, 2) + pow(y, 2);

      if(d <= 1)
         pc += 1;
      else
         pt +=1;
    }
	
	return 4 * (pc/pt);
}



int main()
{
int n;
scanf("%d", &n);
double z = pi(n);
printf("%.4lf", z);
return 0;
}


