#include <stdio.h>
#include <unistd.h>

int main()
{
	char* afonso = "YOU GET NO BITCHES";
	int j = -1000;
	while (j < 1000)
	{
		for (int i = 0; i < 18; i++)
		{
			printf("%s",afonso[i]);
			usleep(10000);
		}
		printf("\n");
		j++;
	}
	return 0;
}