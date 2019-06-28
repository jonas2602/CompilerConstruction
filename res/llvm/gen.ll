

define i32 @main() {
	begin:
	%0 = alloca i1
	store i1 0, i1* %0
	%1 = alloca i32
	store i32 0, i32* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = alloca float
	store float 0.0, float* %3
	store i32 5, i32* %1
	store i32 5, i32* %2
	store float 5.0, float* %3
	%4 = load i32, i32* %1
	%5 = load i32, i32* %2
	%6 = icmp eq i32 %4, %5
	store i1 %6, i1* %0
	%7 = load i1, i1* %0
	%8 = and i1 %7, 1
	store i1 %8, i1* %0
	ret i32 0
}

