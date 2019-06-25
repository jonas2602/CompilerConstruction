#include <stdio.h>
#include <stdbool.h>

int myval = 0;

typedef struct
{
    char *name;
    int age;
} person;

int test(int a, int b);
float addf(float f);
int addi(int i);
void none();

int main()
{
    person john;

    printf("test = %d", test(1, 2));
    myval = addf(1.0);
    myval = addf(1.0);
    myval = addf(1.0);
    myval = addf(1.0);
    myval = addf(1.0);
    myval = addf(1.0);
    myval = addf(1.0);

    none();

    john.name = "John";
    john.age = 27;

    printf("%s ist %d Jahre alt.\n", john.name, john.age);
}

void none()
{
    myval = 5;
}

int test(int a, int b)
{
    printf("%d", a + b);
    return a + b;
}

float addf(float f)
{
    float r = f + myval;
    return r;
}

int addi(int i)
{
    int r = i + myval;
    return r;
}

float testCast(int i)
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