#include <stdio.h>
#include <stdbool.h>

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

int main()
{
    none();
    int testInt = 42;

    person john;
    printf("%s ist %d Jahre alt. %d\n", john.name, john.age, testInt);
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
    float r = f + myval;
    printf("%f", r);
    return r;
}

int addi(int i)
{
    int r = i + myval;
    return r;
}

int testCast(float i)
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