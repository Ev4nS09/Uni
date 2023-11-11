#include <stdio.h>
#include <math.h>

const char *author = "Afonso Rio";

double reforma(int a, int m)
{
	double result;
	double am = a + (m / 12);
	if(am + 40 > 70)
		result = 70;
	else if(am + 40 < 66 + (4/12))
		result = a + 40;
	else
		result = a + 40;
	return result;

}

void test_reforma(void)
{
	int a;
	int m;
	while(scanf("%d%d", &a, &m) != EOF)
	{
		int z = reforma(a, m);
		if(z < 70)
	 {		
		printf("%d ", z);
	    printf("%d\n", m);
	 }
	 else
	 {
		printf("%d ", 70);
	    printf("%d\n", 0);
	 }
	}
}

int main(void)
{
	test_reforma();
	return 0;
}