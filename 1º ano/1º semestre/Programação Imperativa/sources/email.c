#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>

#define MAX_MAIL 10000

typedef struct{
const char *email;
const char *password;
const char *info;
}Mail;

const char *str_dup(const char *s)
{
  char *result = (char *) malloc(strlen(s) + 1);
  strcpy(result, s);
  return result;
}

Mail mail(const char *email, const char *password, const char *info)
{
	Mail result;
	result.email = email;
	result.password = password;
	result.info =  info;
	return result;
}

int emails_read(FILE *f, Mail *a)
{
 int result = 0;
 char email[50];
 char password[50];
 char info[100];
 while(fscanf(f, "%s%s%s", email, password, info) != EOF)
 	a[result++] = mail(str_dup(email), str_dup(password), str_dup(info));
 return result;
}

void emails_write(FILE *f, Mail *a, int n)
{
	for (int i = 0; i < n; i++)
		fprintf(f, "[%s]\n", a[i].email);
}

void test_email()
{
	FILE *f = fopen("email.txt", "r");
	assert(f != NULL);
	Mail mails[MAX_MAIL];
	int n_emails = emails_read(f, mails);
	emails_write(stdout, mails, n_emails);
}

int main(int argc, char **argv)
{
	int x = 'A';
	if (argc > 1)
		x = *argv[1];
	if (x == 'A')
		test_email();
	//else if (x == 'B')
		//test_flights_select_destination();
	else if (x == 'U')
		printf("All unit tests PASSED.\n");
	else
		printf("%s: Invalid option.\n", argv[1]);
	return 0;
}