

define i32 @main() {
	begin:
	%0 = alloca [10 x i8]
	%1 = alloca i32
	store i32 0, i32* %1
	%2 = alloca i8*
	%3 = bitcast [10 x i8]* %0 to i8*
	store i8* %3, i8** %2
	%4 = add i32 5, 2
	store i32 %4, i32* %1
	ret i32 0
}

