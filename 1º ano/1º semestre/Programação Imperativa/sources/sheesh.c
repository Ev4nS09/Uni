#include<stdio.h>
#include<math.h>

void test_sheesh(void)
{
	int x;
	while(scanf("%d", &x) != EOF)
	{
	double y = x % 10;
	printf("%lf\n", y);
	}
}

int main(void)
{
	test_sheesh();
	return 0;
}