#include <stdio.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h>

const char *author = "Alexandre  Rodrigues";

char password (char s, char t)
{
	int result = (s - 96) + (t - 96); 
	if (result > 26)
		result =- 26;
	result = result + 96;
	return result;	
}

char *password_creation (const char *s, const char *t, char *p)
{
	int result = 0;
	for (int i = 0; s[i] != '\0'; i++)
	{
		int c = 0;
		p[result++] = password (s[i], t[c]);
		c++;
		if (t[c + 1] == '\0')
			c = 0;
	}
	p[result++] ='\0';
	return p;
}

void test_password_creation(void)
{
    char s[1000];
    char t[1000];
    char p[1000];
    scanf("%s", s);
    while (scanf("%s", t) != EOF)
    {
        password_creation(s, t, p);
        printf("%s\n", p);
    }
}

int main()
{
	test_password_creation();
	return 0;
}
