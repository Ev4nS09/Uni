#include <stdio.h>
#include <assert.h>

int soma(int x, int y)
{
	return x + y;
}
void test_soma()
{
	int x;
	int y;
	while(scanf("%d%d", &x, &y) != EOF)
	{
		int z = soma(x, y);
		printf("%d\n", z);
		printf("bruh, nem sabe somar\n");
	}
}

int main()
{
	test_soma();
	return 0;
}