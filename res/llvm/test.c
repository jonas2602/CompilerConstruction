#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

int myval = 5;

typedef struct
{
    int age;
    char *name;
} person;

typedef struct
{
    person ps;
} group;

typedef struct
{
    group ps;
} world;

typedef struct
{
    int length;
    int typesize;
    float *start;
} myarr;

typedef struct
{
    int length;
    int typesize;
    myarr *start;
} myarrarr;


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
    // john.age = 12;
    // john.name = "john";

    group grp;
    grp.ps = john;
    // grp.ps.age = 23;
    // world w;

    // printf("%d", john.age);
}

char testCast(double i)
{
    return i;
}

void stringtest()
{
    // char mychar = 'x';
    // char mychararr[5] = {'a', 'b', 'c', 'd', 'e'};
    char mystr[15] = "abcde";
    char *otherstr = "xyz";
    // char mystrArray[5] = "test";

    strcat(mystr, otherstr);
    // int myintarr[4] = {1, 2, 3, 4};

    // char *mystr = "abcde";
    printf("%s %s %s", mystr, otherstr, "test123");
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

char *strbuilder(char left, char right)
{
    char *str = (char *)malloc(sizeof(char) * 2);
    str[0] = left;
    str[1] = right;

    return str;
}

double check(double x, int y, char *str)
{
    return x + y;
}

void cases(int i)
{
    switch (i)
    {
    case 0:
    {
        break;
    }
    case 2:
    {
        break;
    }
    default:
    {
        break;
    }
    }
}

void memtest()
{

    person *list = NULL;
    float *list2 = NULL;
    list = malloc(sizeof(person));
    free(list);
    if (list == NULL)
    {
    }
}

person returntest()
{
    person test;
    return test;
}

float myfloatadd(float left, float right)
{
    return left + right;
}

// void overload(int i)
// {
// }

// void overload(float i)
// {
// }
// void overload(char i)
// {
// }

// void setlength(myarr *arr, int size)
// {
//     void *newstart = malloc((*arr).typesize * size);
//     memcpy(newstart, (*arr).start, min((*arr).length, size));
//     (*arr).start = newstart;
// }

// int *getelement(myarr *arr, int index)
// {
//     return (*arr).start + (*arr).typesize * index;
// }

int main()
{
    //     int i = 0;
    // Endlos:
    //     i++;
    //     goto Endlos;

    // stringtest();

    // printf("%s", strbuilder('a', 'b'));
    // cases(5);

    // char str[100];
    // int i;

    // scanf(" %[^\n]",str);
    // printf("%s", str);

    // myarr arr;
    // myarrarr arrarr;
    // (*arrarr.start).start = malloc(sizeof(float) * 5);
    // arr.typesize = sizeof(float);
    // arr.start = malloc(sizeof(float) * 0);
    // arr.length = 5;
    // arr.start[0] = 1.5;
    // ((float *)arr.start)[1] = 0.12;
    // arr.start = realloc(arr.start, 5);
    float* test = malloc(sizeof(float) * 4);
    // test = realloc(test, 5);
    // ((float *)arr.start)[4] = 0.42;

    // float left = ((float *)arr.start)[0];
    // float right = ((float *)arr.start)[1];
    // myfloatadd(left, right);
    // float *elemptr = (float *)getelement(&arr, 1);
    // printf("%p %p, %p %i\n", elemptr, &((float *)arr.start)[1], &((float *)arr.start)[0], arr.typesize);
    // printf("%f", arr.start[2]);
    // printf("%f %f %f", ((float *)arr.start)[0], ((float *)arr.start)[1], ((float *)arr.start)[4]);
}