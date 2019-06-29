#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

int myval = 5;

typedef struct
{
    char *name;
    int age;
} person;

void arraytest();
char test(char a, char b);
float addf(float f);
int addi(int i);
void none();
char *makeString(char left, char right);
int mymod(int a, int b);
int branches(int i);

int main()
{
    // char test[12];
    // test[7] = 'a';

    // int i = 132;
    // int *j = &i;

    // int k = *j;

    // none();
    // int testInt = 42;

    // person john;
    // john.age = *j;
    // printf("%s ist %d Jahre alt. %c %d\n", john.name, john.age, test[7], myval);
    // char *mystr = "abcde\\\n\\";
    // arraytest();

    printf("%s", makeString('a', 'b'));
    printf("%i", true);
}

void arraytest()
{
    char first = 'x';
    char mystring[5] = {'a', 'b', 'c', 'd', 'e'};
    mystring[3] = first;

    int mylist[3] = {2, 4, 6};
    mylist[1] = 1000;

    int val = mylist[1];

    char *out[12][12];
    char *other = out[6][8];

    printf("%c", mystring[1]);
}

void pointertest()
{
    int i = 132;
    int *j = &i;
    int k = *j;
    int l = i;
    int **m = &j;
    k++;
}

void structtest()
{
    person john;
    john.age = 12;
    john.name = "john";
}

int mymod(int a, int b)
{
    return a % b;
}

void none()
{
    myval = 5;
}

char test(char a, char b)
{
    printf("abc %f", (float)a);
    return a + b;
}

float addf(float f)
{
    float r = 1.2 - myval;
    printf("%f", r);
    return r;
}

int addi(int i)
{
    int r = i - myval;
    return r;
}

char testCast(double i)
{
    return i;
}

int check(int i)
{
    bool myval = i || i + 3;
    // if (i && i + 1)
    // {
    //     return i;
    // }
    // else
    // {
    //     return 1;
    // }
    return 1;
}

void myprint(int s, float f)
{
    printf("%c%f", s, f);
}

char *makeString(char left, char right)
{
    char *str = (char *)malloc(3 * sizeof(char));
    str[0] = left;
    str[1] = right;
    str[2] = '\0';

    return str;
}

int branches(int i)
{
    if (i > 0)
    {
        if (i > 10)
        {
            i = 999;
        }
        else
        {
            i = 1;
        }

        i++;
    }
    else
    {
        if (i == 0)
        {
            i = 0;
        }
        else
        {
            i = -1;
        }
    }

    return i;
}

void loops(int length)
{
    int j = 0;
    // for(int i = 0; i < length; ++i){
    //     j--;
    // }

    while (j < 10){
        j++;
    }
}