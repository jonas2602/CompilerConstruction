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
    int mylist[5] = {0, 1, 2, 3, 4};
    int *ptr = &mylist[1];
    *ptr = 10;

    // printf("%d %d %d %d %d, %d", mylist[0], mylist[1], mylist[2], mylist[3], mylist[4], *ptr);
}

void structtest()
{
    person john;
    john.age = 12;
    john.name = "john";
}

char testCast(double i)
{
    return i;
}

void stringtest()
{
    // char mychar = 'x';
    // char mychararr[5] = {'a', 'b', 'c', 'd', 'e'};
    // char* mystr = "abcde";
    char mystrArray[5] = "test"; 
    // int myintarr[4] = {1, 2, 3, 4};

    // char *mystr = "abcde";
    printf("%s", mystrArray);
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
    while (j < 10)
    {
        j++;
    }
}

int main()
{
//     int i = 0;
// Endlos:
//     i++;
//     goto Endlos;

    stringtest();
}