

template <typename T>
T addValues(T left, T right)
{
    return left + right;
}

int main()
{
    addValues<float>(1, 1);
    addValues(1.2, 1.5);
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