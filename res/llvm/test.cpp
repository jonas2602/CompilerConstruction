
typedef struct
{
    int i;
    char *c;
} help;

template <typename T, typename U>
T addValues(T left, U right, int x, int y, int z)
{
    return left;
}

int main()
{
    addValues("test123", help(), 1, 1, 1);
    addValues(1.0, 2.0, 1, 1, 1);
}

// template <typename T>
// T getvalue()
// {
//     return 0;
// }

// int main()
// {
//     getvalue<float>();
//     getvalue<double>();
// }
