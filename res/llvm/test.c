#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

int myval = 0;

typedef struct
{
    char *name;
    int age;
} person;

char test(char a, char b);
float addf(float f);
int addi(int i);
void none();
char* makeString(char left, char right);

int main()
{
    char test[12];
    test[0] = 'a';


    // none();
    // int testInt = 42;

    // person john;
    // printf("%s ist %d Jahre alt. %d\n", john.name, john.age, testInt);
    // printf("%s", makeString('a', 'b'));
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
    if (i > 0)
    {
        return i / 2;
    }
    else
    {
        return i / 2 + 1;
    }
}

void myprint(int s, float f)
{
    printf("%c%f", s, f);
}

char* makeString(char left, char right)
{
    char* str = malloc(3 * sizeof(char));
    str[0] = left;
    str[1] = right;
    str[2] = '\0';


    return str;
}