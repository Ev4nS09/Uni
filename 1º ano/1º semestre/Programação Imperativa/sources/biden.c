#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

int biden(int x)
{
	int result;
	if (x < 100)
      result = x;
    else
     result = round(biden(x/10)) * 10;
 return result;

}

void test_biden(void)
{
	int x;
	while(scanf("%d", &x) != EOF)
	{
		int z = biden(x);
		printf("%d\n", z);
	}
}

int main(void)
{
	test_biden();
	return 0;
}