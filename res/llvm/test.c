#include <stdio.h>

int myval = 0;

typedef struct
{
    char *name;
    int age;
} person;

int test(int a, int b);

int main()
{
    int test2;
    float test2;
    person john;

    printf("test = %d", test(1, 2));

    john.name = "John";
    john.age = 27;

    printf("%s ist %d Jahre alt.", john.name, john.age);
}

int test(int a, int b)
{
    return a + b;
}